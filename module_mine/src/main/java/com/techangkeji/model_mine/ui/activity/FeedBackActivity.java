package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityFeedBackBinding;
import com.techangkeji.model_mine.ui.viewModel.FeedBackViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

public class FeedBackActivity extends BaseActivity<ActivityFeedBackBinding, FeedBackViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_feed_back;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("APP反馈");
    }
}
