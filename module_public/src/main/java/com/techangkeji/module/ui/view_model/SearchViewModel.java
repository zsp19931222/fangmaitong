package com.techangkeji.module.ui.view_model;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.blankj.utilcode.util.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.techangkeji.module_hr.ui.adapter.HRAdapter;
import com.techangkeji.module_information.ui.adapter.InformationAdapter;

import java.util.HashMap;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.entity.BuildingListEntity;
import me.goldze.mvvmhabit.http.net.entity.information.NewsListEntity;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/29 0029 16:05
 * email:zsp872126510@gmail.com
 */
public class SearchViewModel extends BaseViewModel {
    public int pageNum = 1;
    public int from = 0;//从哪里跳转进来的0-搜索房源，1-搜索资讯
    public ObservableField<String> hint = new ObservableField<>("");

    public ObservableField<String> input = new ObservableField<>("");

    public ObservableList<BuildingListEntity.DataBean> buildingList = new ObservableArrayList<>();
    public HRAdapter hrAdapter;

    public ObservableList<NewsListEntity.DataBean> dataBeans = new ObservableArrayList<>();
    public InformationAdapter informationAdapter;

    public ObservableField<SmartRefreshLayout> srl = new ObservableField<>();

    public ObservableField<Integer> showHR = new ObservableField<>(View.GONE);
    public ObservableField<Integer> showInformation = new ObservableField<>(View.GONE);

    public SearchViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand finishCommand = new BindingCommand(() -> finish());

    public void searchData() {
        if (IsNullUtil.getInstance().isEmpty(input.get())) return;
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("areaId", SPUtils.getInstance().getString("areaId"));
        parameter.put("pageSize", 20);
        parameter.put("pageNum", pageNum);
        parameter.put("text", input.get());
        if (from == 0) {
            IdeaApi.getApiService()
                    .getBuildingList(parameter)
                    .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                    .compose(RxUtils.schedulersTransformer())
                    .subscribe(new DefaultObserver<BuildingListEntity>(srl.get(), this) {
                        @Override
                        public void onSuccess(BuildingListEntity response) {
                            ZLog.d(response);
                            buildingList.addAll(response.getData());
                            hrAdapter.notifyDataSetChanged();
                        }

                    });
        } else {
            IdeaApi.getApiService()
                    .getNewsList(parameter)
                    .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                    .compose(RxUtils.schedulersTransformer())
                    .subscribe(new DefaultObserver<NewsListEntity>(srl.get(), this) {
                        @Override
                        public void onSuccess(NewsListEntity response) {
                            dataBeans.addAll(response.getData());
                            informationAdapter.notifyDataSetChanged();
                        }
                    });
        }
    }
}
