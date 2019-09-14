package com.techangkeji.module_information.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.goldze.base.utils.SimulationData;
import com.techangkeji.model_information.BR;
import com.techangkeji.model_information.R;
import com.techangkeji.model_information.databinding.ActivityInfromationDetailBinding;
import com.techangkeji.module_information.ui.adapter.InformationDetailAdapter;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class InformationDetailActivity extends BaseActivity<ActivityInfromationDetailBinding, BaseViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_infromation_detail;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        View header= LayoutInflater.from(this).inflate(R.layout.head_i_information_detail,null);
        InformationDetailAdapter informationDetailAdapter = new InformationDetailAdapter(R.layout.item_i_information_detail, SimulationData.simulation());
        informationDetailAdapter.addHeaderView(header);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(informationDetailAdapter);
    }
}
