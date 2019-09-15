package com.techangkeji.module_information.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_information.BR;
import com.techangkeji.model_information.R;
import com.techangkeji.model_information.databinding.ActivityInviteDetailBinding;
import com.techangkeji.module_information.ui.adapter.InviteDetailAdapter;
import com.techangkeji.module_information.ui.view_model.InviteDetailViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;

@Route(path = ARouterPath.Information.InviteDetailActivity)
public class InviteDetailActivity extends BaseActivity<ActivityInviteDetailBinding, InviteDetailViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_invite_detail;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("招聘详情");
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            strings.add("");
        }
        InviteDetailAdapter inviteDetailAdapter = new InviteDetailAdapter(R.layout.item_linkman, strings);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(inviteDetailAdapter);
    }
}
