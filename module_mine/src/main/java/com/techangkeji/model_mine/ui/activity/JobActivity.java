package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityJobBinding;
import com.techangkeji.model_mine.ui.viewModel.JobViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * description:
 * author:created by Andy on 2019/9/27 22:44
 * email:zsp872126510@gmail.com
 */
public class JobActivity extends BaseActivity<ActivityJobBinding, JobViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_job;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
    binding.title.setTitle("我的求职");
    }
}
