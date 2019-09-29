package com.techangkeji.model_home.ui.view_midel;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.goldze.base.router.ARouterPath;
import com.goldze.base.utils.BaiduLocationBean;
import com.techangkeji.model_home.R;
import com.techangkeji.model_home.ui.adapter.FriendRecommendAdapter;
import com.techangkeji.model_home.ui.adapter.HomeResourceRecommendAdapter;
import com.techangkeji.model_home.ui.adapter.NewInformationAdapter;

import java.util.HashMap;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.AuthRealNameBody;
import me.goldze.mvvmhabit.http.net.body.LocationBody;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.http.net.entity.LocationEntity;
import me.goldze.mvvmhabit.http.net.entity.NewPlacardEntity;
import me.goldze.mvvmhabit.http.net.entity.RecommendBuildingEntity;
import me.goldze.mvvmhabit.http.net.entity.RecommendFriendEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.information.NewsListEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtil;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 17:01
 * email:zsp872126510@gmail.com
 */
public class HomeViewModel extends BaseViewModel {

    public ObservableList<RecommendFriendEntity.DataBean> recommendFriedList = new ObservableArrayList<>();
    public ObservableField<FriendRecommendAdapter> friendRecommendAdapter = new ObservableField<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description: 推荐房源
     * author: Andy
     * date: 2019/9/28  14:43
     */
    public ObservableList<RecommendBuildingEntity.DataBean> buildingList = new ObservableArrayList<>();
    public ObservableField<HomeResourceRecommendAdapter> buildingAdapter = new ObservableField<>();

    public void recommendBuildHome() {
        buildingList.clear();
        Map<String, Object> map = new HashMap<>();
        map.put("areaId", SPUtils.getInstance().getString("areaId"));
        IdeaApi.getApiService().recommendBuildHome(map)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<RecommendBuildingEntity>() {
                    @Override
                    public void onSuccess(RecommendBuildingEntity response) {
                        buildingList.addAll(response.getData());
                        ZLog.d(buildingList);
                        buildingAdapter.get().notifyDataSetChanged();
                    }

                });
    }

    /**
     * description: 推荐资讯
     * author: Andy
     * date: 2019/9/28  14:44
     */
    public ObservableList<NewsListEntity.DataBean> newsList = new ObservableArrayList<>();
    public ObservableField<NewInformationAdapter> newsAdapter = new ObservableField<>();

    public void recommendNewsHome() {
        newsList.clear();
        Map<String, Object> map = new HashMap<>();
        map.put("areaId", SPUtils.getInstance().getString("areaId"));
        IdeaApi.getApiService().recommendNewsHome(map)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<NewsListEntity>() {
                    @Override
                    public void onSuccess(NewsListEntity response) {
                        newsList.addAll(response.getData());
                        newsAdapter.get().notifyDataSetChanged();
                    }
                });
    }

    /**
     * description: 推荐好友
     * author: Andy
     * date: 2019/9/28  23:39
     */

    public void recommendFriend() {
        recommendFriedList.clear();
        IdeaApi.getApiService().recommend(Integer.parseInt(SPUtils.getInstance().getString("areaId")))
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<RecommendFriendEntity>() {
                    @Override
                    public void onSuccess(RecommendFriendEntity response) {
                        recommendFriedList.addAll(response.getData());
                        friendRecommendAdapter.get().notifyDataSetChanged();
                    }
                });
    }

    /**
     * description: 首页滚动资讯
     * author: Andy
     * date: 2019/9/29 0029 14:30
     */
    public ObservableField<Context> context=new ObservableField<>();
    public ObservableField<ViewFlipper> vf_hh_notice=new ObservableField<>();
    public void getNewPlacard() {
        IdeaApi.getApiService()
                .getNewPlacard()
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<NewPlacardEntity>() {
                    @Override
                    public void onSuccess(NewPlacardEntity response) {
                        for (String datum : response.getData()) {
                            final View ll_content = View.inflate(context.get(), R.layout.item_flipper, null);
                            TextView tv_content = ll_content.findViewById(R.id.bus_notice_text);
                            tv_content.setText(datum);
                            vf_hh_notice.get().addView(ll_content);
                        }

                    }
                });
    }
}
