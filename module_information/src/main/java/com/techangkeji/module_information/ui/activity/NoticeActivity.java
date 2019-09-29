package com.techangkeji.module_information.ui.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_information.BR;
import com.techangkeji.model_information.R;
import com.techangkeji.model_information.databinding.ActivityNoticeDetailBinding;
import com.techangkeji.module_information.ui.view_model.NoticeViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;
@Route(path = ARouterPath.Information.NoticeActivity)
public class NoticeActivity extends BaseActivity<ActivityNoticeDetailBinding, NoticeViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_notice_detail;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("公告详情");
        viewModel.getPlacardInfo(getIntent().getExtras().getString("id"));
    }
}
