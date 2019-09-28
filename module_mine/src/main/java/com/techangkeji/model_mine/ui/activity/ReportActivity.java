package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityReportBinding;
import com.techangkeji.model_mine.ui.viewModel.ReportViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * description:
 * author:created by Andy on 2019/9/28 16:41
 * email:zsp872126510@gmail.com
 */
public class ReportActivity extends BaseActivity<ActivityReportBinding, ReportViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_report;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("我的举报");
    }
}
