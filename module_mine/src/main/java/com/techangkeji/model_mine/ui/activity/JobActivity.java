package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityJobBinding;
import com.techangkeji.model_mine.ui.adapter.JobAdapter;
import com.techangkeji.model_mine.ui.viewModel.JobViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

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
        viewModel.srl.set(binding.srl);
        binding.title.setTitle("我的求职");
        viewModel.jobAdapter=new JobAdapter(R.layout.item_invite_information,viewModel.dataBeans);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(viewModel.jobAdapter);
        binding.srl.setEnableAutoLoadMore(true);
        binding.srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                viewModel.pageNum++;
                viewModel.recruitmentsList();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.pageNum=1;
                viewModel.recruitmentsList();
            }
        });
    }
}
