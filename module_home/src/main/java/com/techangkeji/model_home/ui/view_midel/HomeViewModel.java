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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.blankj.utilcode.util.SPUtils;
import com.goldze.base.router.ARouterPath;
import com.goldze.base.utils.BaiduLocationBean;
import com.techangkeji.model_home.R;
import com.techangkeji.model_home.ui.adapter.FriendRecommendAdapter;
import com.techangkeji.model_home.ui.adapter.HomeResourceRecommendAdapter;
import com.techangkeji.model_home.ui.adapter.LabelAdapter;
import com.techangkeji.model_home.ui.adapter.NewInformationAdapter;
import com.techangkeji.model_home.ui.adapter.RecruitmentAdapter;
import com.techangkeji.model_home.ui.utils.BannerSetting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.AuthRealNameBody;
import me.goldze.mvvmhabit.http.net.body.BannerBody;
import me.goldze.mvvmhabit.http.net.body.LocationBody;
import me.goldze.mvvmhabit.http.net.entity.BannerEntity;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.http.net.entity.LocationEntity;
import me.goldze.mvvmhabit.http.net.entity.NewPlacardEntity;
import me.goldze.mvvmhabit.http.net.entity.RecommendBuildingEntity;
import me.goldze.mvvmhabit.http.net.entity.RecommendFriendEntity;
import me.goldze.mvvmhabit.http.net.entity.RecruitmentListEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.WordEntity;
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
    public ObservableField<Context> context = new ObservableField<>();
    public ObservableField<ViewFlipper> vf_hh_notice = new ObservableField<>();

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

    /**
     * description: 获取banner
     * author: Andy
     * date: 2019/9/29  23:22
     */
    private BannerBody bannerBody = new BannerBody();
    public ObservableList<BannerEntity.DataBean.EntityListBean> bannerList = new ObservableArrayList<>();
    public ObservableField<ConvenientBanner> banner = new ObservableField<>();

    public void bannerList() {
        bannerBody.setAreaId(Integer.parseInt(SPUtils.getInstance().getString("areaId")));
        IdeaApi.getApiService().bannerList(bannerBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<BannerEntity>() {
                    @Override
                    public void onSuccess(BannerEntity response) {
                        bannerList.addAll(response.getData().getEntityList());
                        initBanner();
                    }

                });
    }

    private void initBanner() {
        BannerSetting.getInstance().setBanner(context.get(), banner.get(), bannerList);
    }

    /**
     * description: 标签
     * author: Andy
     * date: 2019/9/30  0:51
     */
    public ObservableList<WordEntity.DataBean> label = new ObservableArrayList<>();
    public ObservableField<RecyclerView> rv_hh_label = new ObservableField<>();

    public void tcWords() {
        IdeaApi.getApiService().tcWords()
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<WordEntity>() {
                    @Override
                    public void onSuccess(WordEntity response) {
                        label.addAll(response.getData());
                        initLabel();
                    }

                });
    }

    private void initLabel() {
        LabelAdapter labelAdapter = new LabelAdapter(R.layout.item_label, label);
        rv_hh_label.get().setLayoutManager(new GridLayoutManager(context.get(), 5));
        rv_hh_label.get().setAdapter(labelAdapter);
    }

    /**
     * description: 推荐招聘
     * author: Andy
     * date: 2019/9/30  1:27
     */
    public ObservableList<RecruitmentListEntity.DataBean> recruitmentsRecommendList=new ObservableArrayList<>();
    public ObservableField<RecruitmentAdapter> RecruitmentAdapter=new ObservableField<>();
    public void RecruitmentsRecommend() {
        IdeaApi.getApiService().RecruitmentsRecommend(Integer.parseInt(SPUtils.getInstance().getString("areaId")))
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<RecruitmentListEntity>() {
                    @Override
                    public void onSuccess(RecruitmentListEntity response) {
                        recruitmentsRecommendList.addAll(response.getData());
                        RecruitmentAdapter.get();
                    }

                });
    }

}
