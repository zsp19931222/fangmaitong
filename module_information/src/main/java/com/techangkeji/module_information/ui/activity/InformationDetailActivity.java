package com.techangkeji.module_information.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.goldze.base.utils.SimulationData;
import com.techangkeji.model_information.BR;
import com.techangkeji.model_information.R;
import com.techangkeji.model_information.databinding.ActivityInfromationDetailBinding;
import com.techangkeji.module_information.ui.adapter.InformationDetailAdapter;
import com.techangkeji.module_information.ui.view_model.InformationDetailViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

@Route(path = ARouterPath.Information.InformationDetailActivity)
public class InformationDetailActivity extends BaseActivity<ActivityInfromationDetailBinding, InformationDetailViewModel> {
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
        InformationDetailAdapter informationDetailAdapter = new InformationDetailAdapter(R.layout.item_i_information_detail, SimulationData.simulation());
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(informationDetailAdapter);
        viewModel.getNewsInfo(getIntent().getExtras().getString("id"));
    }
}
