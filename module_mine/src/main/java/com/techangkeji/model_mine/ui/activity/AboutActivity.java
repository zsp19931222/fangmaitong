package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityAboutBinding;
import com.techangkeji.model_mine.ui.viewModel.AboutViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

public class AboutActivity extends BaseActivity<ActivityAboutBinding, AboutViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_about;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("关于");
    }
}
