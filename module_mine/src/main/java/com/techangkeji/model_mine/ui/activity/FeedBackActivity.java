package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityFeedBackBinding;
import com.techangkeji.model_mine.ui.adapter.ReportAdapter;
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
        viewModel.srl.set(binding.srl);
        viewModel.reportAdapter=new ReportAdapter(R.layout.item_feedback,viewModel.dataBeans);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(viewModel.reportAdapter);
        binding.srl.setEnableAutoLoadMore(true);
        binding.srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                viewModel.page++;
                viewModel.appFeedbackList();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.page=1;
                viewModel.appFeedbackList();
            }
        });
    }
}
