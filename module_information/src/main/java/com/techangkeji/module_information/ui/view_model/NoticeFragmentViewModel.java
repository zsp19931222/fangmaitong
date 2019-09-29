package com.techangkeji.module_information.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.blankj.utilcode.util.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.techangkeji.module_information.ui.adapter.NoticeAdapter;

import java.util.HashMap;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.entity.information.PlacardListEntity;
import me.goldze.mvvmhabit.utils.RxUtils;

/**
 * description:
 * author:created by Andy on 2019/9/25 0025 14:54
 * email:zsp872126510@gmail.com
 */
public class NoticeFragmentViewModel extends BaseViewModel {
    public ObservableList<PlacardListEntity.DataBean> beanObservableList = new ObservableArrayList<>();
    public ObservableField<NoticeAdapter> adapter = new ObservableField<>();
    public ObservableField<SmartRefreshLayout> srl = new ObservableField<>();
    public int pageNum = 1;

    public NoticeFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description: 获取公告列表
     * author: Andy
     * date: 2019/9/25 0025 14:35
     */
    public void getPlacardList() {
        if (pageNum==1){
            beanObservableList.clear();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("areaId", SPUtils.getInstance().getString("areaId"));
        map.put("pageSize", "20");
        map.put("pageNum", pageNum + "");
        IdeaApi.getApiService()
                .getPlacardList(map)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<PlacardListEntity>(srl.get(), this) {
                    @Override
                    public void onSuccess(PlacardListEntity response) {
                        beanObservableList.addAll(response.getData());
                        adapter.get().notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        pageNum = 1;
        getPlacardList();
    }
}
