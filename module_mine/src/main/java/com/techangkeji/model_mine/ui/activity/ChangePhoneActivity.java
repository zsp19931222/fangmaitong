package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityChangePhoneBinding;
import com.techangkeji.model_mine.ui.viewModel.ChangePhoneViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

public class ChangePhoneActivity extends BaseActivity<ActivityChangePhoneBinding, ChangePhoneViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_change_phone;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("修改手机号");
    }
}
