package com.techangkeji.model_home.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_home.R;
import com.techangkeji.model_home.databinding.FragmentHomeBinding;
import com.techangkeji.model_home.ui.adapter.GridAdapter;
import com.techangkeji.model_home.ui.adapter.HomeAdapter;
import com.techangkeji.model_home.ui.adapter.LabelAdapter;
import com.techangkeji.model_home.ui.bean.HomeAdapterBean;
import com.techangkeji.model_home.ui.bean.HomeGridViewBean;
import com.techangkeji.model_home.ui.utils.BannerSetting;
import com.techangkeji.model_home.ui.view_midel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseLazyFragment;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.ZLog;

import static com.techangkeji.model_home.ui.bean.HomeAdapterBean.FriendRecommend;
import static com.techangkeji.model_home.ui.bean.HomeAdapterBean.HomeResourceRecommend;
import static com.techangkeji.model_home.ui.bean.HomeAdapterBean.NewInformation;
import static com.techangkeji.model_home.ui.bean.HomeAdapterBean.Recruitment;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 17:15
 * email:zsp872126510@gmail.com
 */
@Route(path = ARouterPath.Home.HomeFragment)
public class HomeFragment extends BaseLazyFragment<FragmentHomeBinding, HomeViewModel> {
    private RecyclerView rv_hh_label, rv_hh_gridview;
    private ConvenientBanner banner;
    private ViewFlipper vf_hh_notice;
    private List<HomeAdapterBean> homeAdapterBeans = new ArrayList<>();
    private HomeAdapter homeAdapter;

    @Override
    public void onResume() {
        super.onResume();
        viewModel.recommendBuildHome();
        viewModel.recommendNewsHome();
        viewModel.recommendFriend();
        viewModel.getNewPlacard();
        viewModel.RecruitmentsRecommend();
    }

    @Override
    public void fetchData() {
        homeAdapter = new HomeAdapter(homeAdapterBeans, viewModel);
        initHeader();
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(homeAdapter);
        initHomeResourceRecommend();
        initFriendRecommend();
        initRecruitment();
        initNewInformation();
        homeAdapter.notifyDataSetChanged();
        RxSubscriptions.add(RxBus.getDefault().toObservable(String.class).subscribe(s -> {
            ZLog.d(s);
            if ("获取区域ID成功".equals(s)) {
                viewModel.recommendBuildHome();
                viewModel.recommendNewsHome();
                viewModel.recommendFriend();
                viewModel.getNewPlacard();
            }
        }));
        viewModel.bannerList();
    }

    @Override
    public void initData() {
        viewModel.context.set(getContext());
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_home;
    }

    @Override
    public int initVariableId() {
        return com.techangkeji.model_home.BR.viewModel;
    }

    private void initHomeResourceRecommend() {
        HomeAdapterBean homeAdapterBean = new HomeAdapterBean();
        homeAdapterBean.setType(HomeResourceRecommend);
        homeAdapterBean.setTitle("房源推荐");
        homeAdapterBeans.add(homeAdapterBean);
    }

    private void initFriendRecommend() {
        HomeAdapterBean homeAdapterBean = new HomeAdapterBean();
        homeAdapterBean.setType(FriendRecommend);
        homeAdapterBean.setTitle("好友推荐");
        homeAdapterBeans.add(homeAdapterBean);
    }

    private void initRecruitment() {
        HomeAdapterBean homeAdapterBean = new HomeAdapterBean();
        homeAdapterBean.setType(Recruitment);
        homeAdapterBean.setTitle("经纪人招聘");
        homeAdapterBeans.add(homeAdapterBean);
    }

    private void initNewInformation() {
        HomeAdapterBean homeAdapterBean = new HomeAdapterBean();
        homeAdapterBean.setType(NewInformation);
        homeAdapterBean.setTitle("最新资讯");
        homeAdapterBeans.add(homeAdapterBean);
    }

    private void initHeader() {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.header_home, null);
        headerBindingID(header);
        homeAdapter.addHeaderView(header);
    }

    private void headerBindingID(View header) {
        rv_hh_label = header.findViewById(R.id.rv_hh_label);
        rv_hh_gridview = header.findViewById(R.id.rv_hh_gridview);
        banner = header.findViewById(R.id.banner);
        viewModel.banner.set(banner);
        vf_hh_notice = header.findViewById(R.id.vf_hh_notice);
        initLabel();
        initGrid();
        initViewFlipper();
    }

    private void initLabel() {
        viewModel.rv_hh_label.set(rv_hh_label);
        viewModel.tcWords();
    }


    private void initGrid() {
        List<HomeGridViewBean> homeGridViewBeans = new ArrayList<>();
        homeGridViewBeans.add(new HomeGridViewBean(R.mipmap.xf, "新房"));
        homeGridViewBeans.add(new HomeGridViewBean(R.mipmap.sydc, "商业地产"));
        homeGridViewBeans.add(new HomeGridViewBean(R.mipmap.dtzf, "地图找房"));
        homeGridViewBeans.add(new HomeGridViewBean(R.mipmap.zxzx, "最新资讯"));
        homeGridViewBeans.add(new HomeGridViewBean(R.mipmap.zpxx, "招聘信息"));
        homeGridViewBeans.add(new HomeGridViewBean(R.mipmap.xsrm, "新手入门"));
        homeGridViewBeans.add(new HomeGridViewBean(R.mipmap.rmjw, "人脉交友"));
        homeGridViewBeans.add(new HomeGridViewBean(R.mipmap.fdzx, "房贷资讯"));
        GridAdapter labelAdapter = new GridAdapter(R.layout.item_grid, homeGridViewBeans);
        rv_hh_gridview.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rv_hh_gridview.setAdapter(labelAdapter);
    }

    private void initViewFlipper() {
        viewModel.vf_hh_notice.set(vf_hh_notice);
    }
}
