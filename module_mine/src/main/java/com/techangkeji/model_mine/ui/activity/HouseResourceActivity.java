package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.SPUtils;
import com.goldze.base.bean.FeaturedLabelBean;
import com.goldze.base.eventbus.FilterRxBusBean;
import com.goldze.base.eventbus.HouseTypeRxBusBean;
import com.goldze.base.eventbus.PriceRxBusBean;
import com.goldze.base.eventbus.SortRxBusBean;
import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityHouseResourceBinding;
import com.techangkeji.model_mine.ui.adapter.HouseResourceAdapter;
import com.techangkeji.model_mine.ui.viewModel.HouseResourceViewModel;
import com.techangkeji.module_hr.ui.bean.AreaPopupBean;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.ZLog;

public class HouseResourceActivity extends BaseActivity<ActivityHouseResourceBinding, HouseResourceViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_house_resource;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.getBuildingTypeLabel();
        viewModel.getFeaturedLabel();
        viewModel.context.set(this);
        viewModel.choiceView.set(binding.llFhChoice);
        binding.title.setTitle("我的房源");
        HouseResourceAdapter inviteInformationAdapter = new HouseResourceAdapter(R.layout.item_my_home_resource, viewModel.buildingList, viewModel);
        viewModel.adapter.set(inviteInformationAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(inviteInformationAdapter);
        RxSubscriptions.add(RxBus.getDefault().toObservable(AreaPopupBean.class).subscribe(areaPopupBean -> {
            ZLog.d(areaPopupBean.getId());
            viewModel.areaCode.set(areaPopupBean.getId());
            viewModel.getData();
        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(HouseTypeRxBusBean.class).subscribe(houseTypeRxBusBean -> {
            viewModel.houseType.set(houseTypeRxBusBean.getType());
            viewModel.getData();
        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(PriceRxBusBean.class).subscribe(priceRxBusBean -> {
            viewModel.priceMin.set(priceRxBusBean.getMin());
            viewModel.priceMax.set(priceRxBusBean.getMax());
            viewModel.getData();
        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(SortRxBusBean.class).subscribe(sortRxBusBean -> {
            viewModel.lat.set("");
            viewModel.lon.set("");
            viewModel.hotSort.set("");
            viewModel.priceSort.set("");
            viewModel.priceSort.set("");
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
                    viewModel.priceSort.set("1");
                    break;
                case "开盘时间从远到近":
                    viewModel.priceSort.set("0");
                    break;
            }
            viewModel.getData();
        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(FilterRxBusBean.class).subscribe(filterRxBusBean -> {
            viewModel.decoration.set(filterRxBusBean.getDecoration());
            viewModel.specialLabel.set(filterRxBusBean.getSpecialLabel());
            viewModel.propertyType.set(filterRxBusBean.getPropertyType());
            viewModel.areaMin.set(filterRxBusBean.getAreaMin());
            viewModel.areaMax.set(filterRxBusBean.getAreaMax());
            viewModel.openType.set(filterRxBusBean.getOpenType());
            viewModel.getData();
        }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getData();
    }
}
