package com.techangkeji.model_home.ui.activity;

import android.os.Bundle;

import com.techangkeji.model_home.BR;
import com.techangkeji.model_home.R;
import com.techangkeji.model_home.databinding.ActivityAuctionBinding;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class AuctionActivity extends BaseActivity<ActivityAuctionBinding, BaseViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_auction;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("拍卖房按揭贷款");
    }
}
