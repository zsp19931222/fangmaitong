package com.techangkeji.model_home.ui.activity;

import android.os.Bundle;

import com.techangkeji.model_home.BR;
import com.techangkeji.model_home.R;
import com.techangkeji.model_home.databinding.ActivityCreditBinding;
import com.techangkeji.model_home.databinding.ActivityHouseMortgagePledgeBinding;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class CreditActivity extends BaseActivity<ActivityCreditBinding, BaseViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_credit;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("个人信用贷款");
    }
}
