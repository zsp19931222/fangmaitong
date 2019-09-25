package com.techangkeji.module_information.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.blankj.utilcode.util.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.techangkeji.module_information.ui.adapter.InformationAdapter;

import java.util.HashMap;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.entity.information.NewsListEntity;
import me.goldze.mvvmhabit.utils.RxUtils;

/**
 * description:
 * author:created by Andy on 2019/9/25 0025 15:58
 * email:zsp872126510@gmail.com
 */
public class InformationViewModel extends BaseViewModel {
    public ObservableField<String> labelId = new ObservableField<>("");
    public ObservableField<String> sortType = new ObservableField<>("");
    public ObservableList<NewsListEntity.DataBean> dataBeans = new ObservableArrayList<>();
    public ObservableField<InformationAdapter> adapter = new ObservableField<>();
    public ObservableField<SmartRefreshLayout> srl = new ObservableField<>();


    public InformationViewModel(@NonNull Application application) {
        super(application);
    }

    public void getNewsList() {
        Map<String, Object> map = new HashMap<>();
        map.put("areaId", SPUtils.getInstance().getString("areaId"));
        map.put("labelId", labelId.get());
        map.put("sortType", sortType.get());
        IdeaApi.getApiService()
                .getNewsList(map)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<NewsListEntity>(srl.get(), this) {
                    @Override
                    public void onSuccess(NewsListEntity response) {
                        dataBeans.addAll(response.getData());
                        adapter.get().notifyDataSetChanged();
                    }
                });
    }
}
