package com.techangkeji.module_information.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.goldze.base.utils.SimulationData;
import com.techangkeji.model_information.BR;
import com.techangkeji.model_information.R;
import com.techangkeji.model_information.databinding.ActivityJobDetailBinding;
import com.techangkeji.module_information.ui.adapter.InviteDetailAdapter;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class JobDetailActivity extends BaseActivity<ActivityJobDetailBinding, BaseViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_job_detail;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("求职详情");
//        InviteDetailAdapter inviteDetailAdapter = new InviteDetailAdapter(R.layout.item_linkman, SimulationData.simulation());
//        binding.rv.setLayoutManager(new LinearLayoutManager(this));
//        binding.rv.setAdapter(inviteDetailAdapter);
    }
}
