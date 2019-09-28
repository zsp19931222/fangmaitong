package com.techangkeji.model_home.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_home.BR;
import com.techangkeji.model_home.R;
import com.techangkeji.model_home.databinding.ActivityRecommendFriendBinding;
import com.techangkeji.model_home.ui.adapter.FriendRecommendAdapter;
import com.techangkeji.model_home.ui.view_midel.RecommendFriendViewModel;

import java.util.ArrayList;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * description:
 * author:created by Andy on 2019/9/28 23:47
 * email:zsp872126510@gmail.com
 */
public class RecommendFriendActivity extends BaseActivity<ActivityRecommendFriendBinding, RecommendFriendViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_recommend_friend;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
    binding.title.setTitle("找人");
    viewModel.choiceView.set(binding.choiceView);
    viewModel.context.set(this);
    viewModel.friendRecommendAdapter=new FriendRecommendAdapter(R.layout.item_friend_recommend1,viewModel.recommendFriedList);
    binding.rv.setLayoutManager(new GridLayoutManager(this,2));
    binding.rv.setAdapter(viewModel.friendRecommendAdapter);
    }
}
