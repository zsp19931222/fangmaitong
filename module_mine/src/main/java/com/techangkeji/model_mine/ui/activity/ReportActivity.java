package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityReportBinding;
import com.techangkeji.model_mine.ui.adapter.ReportAdapter;
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
        viewModel.srl.set(binding.srl);
        viewModel.reportAdapter=new ReportAdapter(R.layout.item_feedback,viewModel.dataBeans);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(viewModel.reportAdapter);
        binding.title.setTitle("我的举报");
        binding.srl.setEnableAutoLoadMore(true);
        binding.srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                viewModel.page++;
                viewModel.appReportList();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.page=1;
                viewModel.appReportList();
            }
        });
    }
}
