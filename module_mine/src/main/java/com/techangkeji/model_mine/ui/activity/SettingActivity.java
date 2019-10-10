package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivitySettingBinding;
import com.techangkeji.model_mine.ui.viewModel.SettingViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * description:
 * author:created by Andy on 2019/9/11 0011 16:59
 * email:zsp872126510@gmail.com
 */
public class SettingActivity extends BaseActivity<ActivitySettingBinding, SettingViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_setting;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("设置");
        viewModel.fragmentManagerObservableField.set(getSupportFragmentManager());
    }
}
