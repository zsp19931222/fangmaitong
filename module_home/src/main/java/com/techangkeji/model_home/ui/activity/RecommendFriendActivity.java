package com.techangkeji.model_home.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.recyclerview.widget.GridLayoutManager;

import com.techangkeji.model_home.BR;
import com.techangkeji.model_home.R;
import com.techangkeji.model_home.databinding.ActivityRecommendFriendBinding;
import com.techangkeji.model_home.ui.adapter.FriendRecommendAdapter;
import com.techangkeji.model_home.ui.view_midel.RecommendFriendViewModel;
import com.techangkeji.module_hr.ui.popup.TypePopupwindow;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;

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
        viewModel.srl.set(binding.srl);
        binding.title.setTitle("找人");
        viewModel.choiceView.set(binding.choiceView);
        viewModel.context.set(this);
        viewModel.friendRecommendAdapter = new FriendRecommendAdapter(R.layout.item_friend_recommend1, viewModel.recommendFriedList);
        binding.rv.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rv.setAdapter(viewModel.friendRecommendAdapter);
        viewModel.userList();

        RxSubscriptions.add(RxBus.getDefault().toObservable(TypePopupwindow.TypeRxBean.class).subscribe(typeRxBean -> {
            switch (typeRxBean.getString()) {
                case "不限":
                    if (viewModel.isCheckClassify) {
                        viewModel.recommendFriendBody.setIdentity(-1);
                    } else {
                        viewModel.recommendFriendBody.setSex(-1);
                    }
                    break;
                case "经纪人":
                    viewModel.recommendFriendBody.setIdentity(4);
                    break;
                case "总代":
                    viewModel.recommendFriendBody.setIdentity(1);
                    break;
                case "分销商":
                    viewModel.recommendFriendBody.setIdentity(-1);
                    break;
                case "男":
                    viewModel.recommendFriendBody.setSex(1);
                    break;
                case "女":
                    viewModel.recommendFriendBody.setSex(0);
                    break;
                case "全部":
                    viewModel.recommendFriendBody.setRate(0);
                    break;
                case "非常活跃":
                    viewModel.recommendFriendBody.setRate(50);
                    break;
                case "一般活跃":
                    viewModel.recommendFriendBody.setRate(40);
                    break;
                case "不活跃":
                    viewModel.recommendFriendBody.setRate(5);
                    break;

            }
            viewModel.pageNum = 1;
            viewModel.userList();
        }));
        binding.et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.pageNum = 1;
                viewModel.userList();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
