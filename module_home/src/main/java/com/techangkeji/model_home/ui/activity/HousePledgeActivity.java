package com.techangkeji.model_home.ui.activity;

import android.os.Bundle;

import com.techangkeji.model_home.BR;
import com.techangkeji.model_home.R;
import com.techangkeji.model_home.databinding.ActivityHousePledgeBinding;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * description:房贷抵押贷款
 * author:created by Andy on 2019/9/16 0016 18:10
 * email:zsp872126510@gmail.com
 */
public class HousePledgeActivity extends BaseActivity<ActivityHousePledgeBinding, BaseViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_house_pledge;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
