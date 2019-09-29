package com.techangkeji.module_information.ui.view_model;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.blankj.utilcode.util.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.techangkeji.module_information.ui.adapter.InformationAdapter;
import com.techangkeji.module_information.ui.popup.InformationLabelPopupwindow;
import com.techangkeji.module_information.ui.popup.InformationSortPopupwindow;

import java.util.HashMap;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.entity.FeaturedLabelEntity;
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
    public ObservableList<FeaturedLabelEntity.DataBean> labelList = new ObservableArrayList<>();
    public ObservableField<InformationAdapter> adapter = new ObservableField<>();
    public ObservableField<SmartRefreshLayout> srl = new ObservableField<>();
    public ObservableField<View> ll_view = new ObservableField<>();
    public ObservableField<Context> context = new ObservableField<>();
    public int pageNum = 1;


    public InformationViewModel(@NonNull Application application) {
        super(application);
    }

    public void getNewsList() {
        if (pageNum == 1) {
            dataBeans.clear();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("areaId", SPUtils.getInstance().getString("areaId"));
        map.put("labelId", labelId.get());
        map.put("sortType", sortType.get());
        map.put("pageSize", "20");
        map.put("pageNum", pageNum + "");
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

    public void getLabelList() {
        IdeaApi.getApiService()
                .getLabelList()
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<FeaturedLabelEntity>() {
                    @Override
                    public void onSuccess(FeaturedLabelEntity response) {
                        labelList.addAll(response.getData());
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        pageNum = 1;
        getNewsList();
    }

    public BindingCommand sortCommand = new BindingCommand(() -> {
        new InformationSortPopupwindow(context.get()).showPopupWindow(ll_view.get());
    });
    public BindingCommand labelCommand = new BindingCommand(() -> {
        new InformationLabelPopupwindow(context.get(), labelList).showPopupWindow(ll_view.get());
    });
}
