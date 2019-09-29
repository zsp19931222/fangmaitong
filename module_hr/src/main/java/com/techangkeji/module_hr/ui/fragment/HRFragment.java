package com.techangkeji.module_hr.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.SPUtils;
import com.goldze.base.eventbus.FilterRxBusBean;
import com.goldze.base.eventbus.HouseTypeRxBusBean;
import com.goldze.base.eventbus.PriceRxBusBean;
import com.goldze.base.eventbus.SortRxBusBean;
import com.goldze.base.router.ARouterPath;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.techangkeji.module_hr.BR;
import com.techangkeji.module_hr.R;
import com.techangkeji.module_hr.databinding.FragmentHrBinding;
import com.techangkeji.module_hr.ui.adapter.HRAdapter;
import com.techangkeji.module_hr.ui.bean.AreaPopupBean;
import com.techangkeji.module_hr.ui.popup.AreaPopupwindow;
import com.techangkeji.module_hr.ui.view_model.HRViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseLazyFragment;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.ZLog;

@Route(path = ARouterPath.HouseResource.HRFragment)
public class HRFragment extends BaseLazyFragment<FragmentHrBinding, HRViewModel> {
    @Override
    public void fetchData() {
        viewModel.srl.set(binding.srl);
        viewModel.getBuildingTypeLabel();
        viewModel.getFeaturedLabel();
        viewModel.context.set(getContext());
        viewModel.choiceView.set(binding.llFhChoice);
        HRAdapter hrAdapter = new HRAdapter(R.layout.item_home_resource, viewModel.buildingList);
        viewModel.adapter.set(hrAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(hrAdapter);
        RxSubscriptions.add(RxBus.getDefault().toObservable(String.class).subscribe(s -> {
            switch (s){
                case "新房":
                    viewModel.isBusiness=false;
                    break;
                case "商业地产":
                    viewModel.isBusiness=true;
                    break;
            }
        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(AreaPopupBean.class).subscribe(areaPopupBean -> {
            ZLog.d(areaPopupBean.getId());
            viewModel.areaCode.set(areaPopupBean.getId());
            viewModel.pageNum = 1;
            viewModel.getData();
        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(HouseTypeRxBusBean.class).subscribe(houseTypeRxBusBean -> {
            viewModel.houseType.set(houseTypeRxBusBean.getType());
            viewModel.pageNum = 1;
            viewModel.getData();
        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(PriceRxBusBean.class).subscribe(priceRxBusBean -> {
            viewModel.priceMin.set(priceRxBusBean.getMin());
            viewModel.priceMax.set(priceRxBusBean.getMax());
            viewModel.pageNum = 1;
            viewModel.getData();
        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(SortRxBusBean.class).subscribe(sortRxBusBean -> {
            viewModel.lat.set("");
            viewModel.lon.set("");
            viewModel.hotSort.set("");
            viewModel.priceSort.set("");
            viewModel.openingTimeSort.set("");
            switch (sortRxBusBean.getSort()) {
                case "距离最近":
                    viewModel.lat.set(SPUtils.getInstance().getString("latitude"));
                    viewModel.lon.set(SPUtils.getInstance().getString("longitude"));
                    break;
                case "人气最高":
                    viewModel.hotSort.set("1");
                    break;
                case "均价从高到低":
                    viewModel.priceSort.set("1");
                    break;
                case "均价从低到高":
                    viewModel.priceSort.set("0");
                    break;
                case "开盘时间从近到远":
                    viewModel.openingTimeSort.set("1");
                    break;
                case "开盘时间从远到近":
                    viewModel.openingTimeSort.set("0");
                    break;
            }
            viewModel.pageNum = 1;
            viewModel.getData();
        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(FilterRxBusBean.class).subscribe(filterRxBusBean -> {
            viewModel.decoration.set(filterRxBusBean.getDecoration());
            viewModel.specialLabel.set(filterRxBusBean.getSpecialLabel());
            viewModel.propertyType.set(filterRxBusBean.getPropertyType());
            viewModel.areaMin.set(filterRxBusBean.getAreaMin());
            viewModel.areaMax.set(filterRxBusBean.getAreaMax());
            viewModel.openType.set(filterRxBusBean.getOpenType());
            viewModel.pageNum = 1;
            viewModel.getData();
        }));
        binding.srl.setEnableAutoLoadMore(true);
        binding.srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                viewModel.pageNum++;
                viewModel.getData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.pageNum = 1;
                viewModel.getData();
            }
        });

    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_hr;
    }

    @Override
    public void initData() {
        viewModel.context.set(getContext());
        viewModel.choiceView.set(binding.llFhChoice);
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.pageNum=1;
        viewModel.getData();
    }
}
