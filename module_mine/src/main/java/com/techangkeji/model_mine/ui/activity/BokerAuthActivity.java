package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityAutonymAuthBinding;
import com.techangkeji.model_mine.databinding.ActivityBokerAuthBinding;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class BokerAuthActivity extends BaseActivity<ActivityBokerAuthBinding, BaseViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_boker_auth;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
