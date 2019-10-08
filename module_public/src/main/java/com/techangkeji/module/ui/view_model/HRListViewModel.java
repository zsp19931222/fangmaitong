package com.techangkeji.module.ui.view_model;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.goldze.base.utils.ParameterLogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.techangkeji.module_hr.ui.adapter.HRAdapter;

import java.util.HashMap;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.entity.BuildingListEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.friend_circle.UserDetailEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.RxUtils;

/**
 * description:
 * author:created by Andy on 2019/10/8 0008 16:32
 * email:zsp872126510@gmail.com
 */
public class HRListViewModel extends BaseViewModel {
    public int friendId;
    public int pageNum=1;
    public ObservableList<BuildingListEntity.DataBean> buildingList = new ObservableArrayList<>();
    public HRAdapter hrAdapter;

    public ObservableField<String> hrUrl = new ObservableField<>("");
    public ObservableField<String> hrName = new ObservableField<>("");
    public ObservableField<String> hrNum = new ObservableField<>("");
    public ObservableField<String> hrIdent = new ObservableField<>("");
    public ObservableField<Integer> hrAuthSM = new ObservableField<>(View.GONE);
    public ObservableField<Integer> hrAuthZZ = new ObservableField<>(View.GONE);
    public ObservableField<Integer> hrAuthJJR = new ObservableField<>(View.GONE);

    public ObservableField<SmartRefreshLayout> srl=new ObservableField<>();
    public HRListViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description: 获取用户详情
     * author: Andy
     * date: 2019/9/27  23:26
     */
    public void getUserDetailData() {
        IdeaApi.getApiService()
                .userDetailData(friendId)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<SuccessEntity<UserDetailEntity>>() {
                    @Override
                    public void onSuccess(SuccessEntity<UserDetailEntity> response) {
                        hrUrl.set(response.getContent().getHeadUrl());
                        hrName.set(response.getContent().getRealName());
                        hrNum.set("账号：" + response.getContent().getId());
                        switch (LocalDataHelper.getInstance().getUserInfo().getIdentity()) {
                            case 1:
                                hrIdent.set("总代");
                                break;
                            case 2:
                                hrIdent.set("渠道代理");
                                break;
                            case 3:
                                hrIdent.set("联合代理");
                                break;
                            case 4:
                                hrIdent.set("经纪人");
                                break;
                        }
                        if (response.getContent().getBrokerAuthenticate() == 1) {
                            hrAuthJJR.set(View.VISIBLE);
                        } else {
                            hrAuthJJR.set(View.GONE);
                        }
                        if (response.getContent().getQualificationAuthenticate() == 1) {
                            hrAuthZZ.set(View.VISIBLE);
                        } else {
                            hrAuthZZ.set(View.GONE);
                        }
                        if (response.getContent().getRealNameAuthenticate() == 1) {
                            hrAuthSM.set(View.VISIBLE);
                        } else {
                            hrAuthSM.set(View.GONE);
                        }
                    }

                });
    }

    /**
     * description:获取房源列表
     * author: Andy
     * date: 2019/9/22  14:43
     */
    public void getData() {
        if (pageNum == 1) {
            buildingList.clear();
        }
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("pageSize", "20");
        parameter.put("pageNum", pageNum + "");
        parameter.put("id", friendId);
        ParameterLogUtil.getInstance().parameterLog(parameter);
        IdeaApi.getApiService()
                .myBuildingList(parameter)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<BuildingListEntity>(srl.get()) {
                    @Override
                    public void onSuccess(BuildingListEntity response) {
                        buildingList.addAll(response.getData());
                        hrAdapter.notifyDataSetChanged();
                    }

                });
    }
}
