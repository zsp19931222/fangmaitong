package com.techangkeji.model_home.ui.activity;

import android.os.Bundle;

import com.techangkeji.model_home.BR;
import com.techangkeji.model_home.R;
import com.techangkeji.model_home.databinding.ActivityHouseMortgagePledgeBinding;
import com.techangkeji.model_home.databinding.ActivityMortgagePledgeBinding;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class HouseMortgagePledgeActivity extends BaseActivity<ActivityHouseMortgagePledgeBinding, BaseViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_house_mortgage_pledge;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("住房按揭贷");
    }
}
