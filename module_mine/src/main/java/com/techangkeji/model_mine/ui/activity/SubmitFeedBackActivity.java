package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivitySubmitFeedbackBinding;
import com.techangkeji.model_mine.ui.viewModel.SubmitFeedBackViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * description:
 * author:created by Andy on 2019/9/28 16:35
 * email:zsp872126510@gmail.com
 */
public class SubmitFeedBackActivity extends BaseActivity<ActivitySubmitFeedbackBinding, SubmitFeedBackViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_submit_feedback;
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
