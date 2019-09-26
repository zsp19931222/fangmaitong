package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityInviteInformationBinding;
import com.techangkeji.model_mine.ui.adapter.InviteInformationAdapter;
import com.techangkeji.model_mine.ui.viewModel.InviteInformationViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;

public class InviteInformationActivity extends BaseActivity<ActivityInviteInformationBinding, InviteInformationViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_invite_information;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("我的招聘");
        InviteInformationAdapter inviteInformationAdapter = new InviteInformationAdapter(R.layout.item_invite_information, viewModel.dataBeans);
        viewModel.adapter.set(inviteInformationAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(inviteInformationAdapter);
        viewModel.recruitmentsList();
    }
}
