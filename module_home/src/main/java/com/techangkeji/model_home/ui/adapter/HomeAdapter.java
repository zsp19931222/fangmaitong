package com.techangkeji.model_home.ui.adapter;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.goldze.base.constant.RxBusMessageEventConstants;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_home.R;
import com.techangkeji.model_home.ui.activity.RecommendFriendActivity;
import com.techangkeji.model_home.ui.bean.HomeAdapterBean;
import com.techangkeji.model_home.ui.view_midel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.view.MyVerticalDecoration;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 17:01
 * email:zsp872126510@gmail.com
 */
public class HomeAdapter extends BaseQuickAdapter<HomeAdapterBean, BaseViewHolder> {
    private HomeViewModel viewModel;
    public HomeAdapter(@Nullable List<HomeAdapterBean> data,HomeViewModel viewModel) {
        super(data);
        this.viewModel=viewModel;
        setMultiTypeDelegate(new MultiTypeDelegate<HomeAdapterBean>() {
            @Override
            protected int getItemType(HomeAdapterBean homeAdapterBean) {
                //根据你的实体类来判断布局类型
                return homeAdapterBean.getType();
            }
        });
        getMultiTypeDelegate()
                .registerItemType(HomeAdapterBean.HomeResourceRecommend, R.layout.item_home)
                .registerItemType(HomeAdapterBean.FriendRecommend, R.layout.item_home)
                .registerItemType(HomeAdapterBean.Recruitment, R.layout.item_home)
                .registerItemType(HomeAdapterBean.NewInformation, R.layout.item_home)
        ;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeAdapterBean item) {
        switch (helper.getItemViewType()) {
            case HomeAdapterBean.HomeResourceRecommend:
                helper.setText(R.id.tv_lh_header, item.getTitle());
                initHomeResourceRecommend(helper);
                break;
            case HomeAdapterBean.FriendRecommend:
                helper.setText(R.id.tv_lh_header, item.getTitle());
                initFriendRecommend(helper);
                break;
            case HomeAdapterBean.Recruitment:
                helper.setText(R.id.tv_lh_header, item.getTitle());
                initRecruitment(helper);
                break;
            case HomeAdapterBean.NewInformation:
                helper.setText(R.id.tv_lh_header, item.getTitle());
                initNewInformation(helper);
                break;
        }
    }


    private void initHomeResourceRecommend(BaseViewHolder helper) {
        RecyclerView recyclerView = helper.getView(R.id.rv_lh);
        HomeResourceRecommendAdapter HomeResourceRecommendAdapter = new HomeResourceRecommendAdapter(R.layout.item_home_resource, viewModel.buildingList);
        viewModel.buildingAdapter.set(HomeResourceRecommendAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(helper.itemView.getContext()));
        recyclerView.addItemDecoration(new MyVerticalDecoration(helper.itemView.getContext(), ContextCompat.getColor(helper.itemView.getContext(), R.color.color_f6), 1, 0, 0, true));
        recyclerView.setAdapter(HomeResourceRecommendAdapter);
        helper.getView(R.id.tv_lh_more).setOnClickListener(v -> {
            RxBus.getDefault().post(RxBusMessageEventConstants.XF);
        });
    }

    private void initFriendRecommend(BaseViewHolder helper) {
        RecyclerView recyclerView = helper.getView(R.id.rv_lh);
        FriendRecommendAdapter friendRecommendAdapter = new FriendRecommendAdapter(R.layout.item_friend_recommend, viewModel.recommendFriedList);
        viewModel.friendRecommendAdapter.set(friendRecommendAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(helper.itemView.getContext(), 3));
        recyclerView.setAdapter(friendRecommendAdapter);
        helper.getView(R.id.tv_lh_more).setOnClickListener(v -> {
            viewModel.startActivity(RecommendFriendActivity.class);
        });
    }

    private void initRecruitment(BaseViewHolder helper) {
        RecyclerView recyclerView = helper.getView(R.id.rv_lh);
        RecruitmentAdapter recruitmentAdapter = new RecruitmentAdapter(R.layout.item_recruitment, viewModel.recruitmentsRecommendList);
        viewModel.RecruitmentAdapter.set(recruitmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(helper.itemView.getContext()));
        recyclerView.addItemDecoration(new MyVerticalDecoration(helper.itemView.getContext(), ContextCompat.getColor(helper.itemView.getContext(), R.color.color_f6), 1, 0, 0, true));
        recyclerView.setAdapter(recruitmentAdapter);
        helper.getView(R.id.tv_lh_more).setOnClickListener(v -> {
            RxBus.getDefault().post(RxBusMessageEventConstants.ZPXX);
        });
    }

    private void initNewInformation(BaseViewHolder helper) {
        RecyclerView recyclerView = helper.getView(R.id.rv_lh);
        NewInformationAdapter informationAdapter = new NewInformationAdapter(R.layout.item_information, viewModel.newsList);
        viewModel.newsAdapter.set(informationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(helper.itemView.getContext()));
        recyclerView.addItemDecoration(new MyVerticalDecoration(helper.itemView.getContext(), ContextCompat.getColor(helper.itemView.getContext(), R.color.color_f6), 1, 0, 0, true));
        recyclerView.setAdapter(informationAdapter);
        helper.getView(R.id.tv_lh_more).setOnClickListener(v -> {
            RxBus.getDefault().post(RxBusMessageEventConstants.ZXZX);
        });
    }
}