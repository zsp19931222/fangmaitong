package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityHouseResourceBinding;
import com.techangkeji.model_mine.ui.adapter.HouseResourceAdapter;
import com.techangkeji.model_mine.ui.viewModel.HouseResourceViewModel;
import com.techangkeji.module_hr.ui.bean.AreaPopupBean;

import java.util.ArrayList;
import java.util.List;

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
        viewModel.getAreaList();
        viewModel.context.set(this);
        viewModel.choiceView.set(binding.llFhChoice);
        binding.title.setTitle("我的房源");
        HouseResourceAdapter inviteInformationAdapter = new HouseResourceAdapter(R.layout.item_my_home_resource, viewModel.buildingList,viewModel);
        viewModel.adapter.set(inviteInformationAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(inviteInformationAdapter);
        RxSubscriptions.add(RxBus.getDefault().toObservable(AreaPopupBean.class).subscribe(areaPopupBean -> {
            ZLog.d(areaPopupBean.getId());
            viewModel.areaCode.set(areaPopupBean.getId());
            viewModel.getData();
        }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getData();
    }
}
