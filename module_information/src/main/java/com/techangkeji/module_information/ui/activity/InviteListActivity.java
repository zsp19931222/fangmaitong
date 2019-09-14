package com.techangkeji.module_information.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.goldze.base.utils.SimulationData;
import com.techangkeji.model_information.BR;
import com.techangkeji.model_information.R;
import com.techangkeji.model_information.databinding.ActivityInviteListBinding;
import com.techangkeji.module_information.ui.adapter.InviteListAdapter;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class InviteListActivity extends BaseActivity<ActivityInviteListBinding, BaseViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_invite_list;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("招聘列表");
        InviteListAdapter inviteListAdapter = new InviteListAdapter(R.layout.item_i_invite, SimulationData.simulation());
        View header = LayoutInflater.from(this).inflate(R.layout.head_invite_list, null);
        inviteListAdapter.addHeaderView(header);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(inviteListAdapter);
    }
}
