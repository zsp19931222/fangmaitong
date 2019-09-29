package com.techangkeji.model_home.ui.view_midel;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.techangkeji.model_home.ui.adapter.FriendRecommendAdapter;
import com.techangkeji.module_hr.ui.popup.AreaPopupwindow;
import com.techangkeji.module_hr.ui.popup.TypePopupwindow;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.RecommendFriendBody;
import me.goldze.mvvmhabit.http.net.body.RecruitmentListBody;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.http.net.entity.RecommendFriendEntity;
import me.goldze.mvvmhabit.utils.RxUtils;

/**
 * description:
 * author:created by Andy on 2019/9/28 23:48
 * email:zsp872126510@gmail.com
 */
public class RecommendFriendViewModel extends BaseViewModel {
    public FriendRecommendAdapter friendRecommendAdapter;
    public ObservableField<Context> context = new ObservableField<>();
    public ObservableField<View> choiceView = new ObservableField<>();
    public ObservableList<RecommendFriendEntity.DataBean> recommendFriedList = new ObservableArrayList<>();
    public ObservableField<SmartRefreshLayout> srl = new ObservableField<>();
    private List<String> livenessList = new ArrayList<>();//活跃度
    private List<String> classifyList = new ArrayList<>();//分类
    private List<String> sexList = new ArrayList<>();//分类
    public ObservableField<String>  input=new ObservableField<>("");
public boolean isCheckClassify=true;

    public RecommendFriendViewModel(@NonNull Application application) {
        super(application);
        livenessList.add("全部");
        livenessList.add("非常活跃");
        livenessList.add("一般活跃");
        livenessList.add("不活跃");

        classifyList.add("不限");
        classifyList.add("经纪人");
        classifyList.add("总代");
        classifyList.add("分销商");

        sexList.add("不限");
        sexList.add("男");
        sexList.add("女");

    }

    public BindingCommand area = new BindingCommand(() -> {
        new AreaPopupwindow(context.get()).showPopupWindow(choiceView.get());
    });

    public BindingCommand livenessCommand = new BindingCommand(() -> {
        new TypePopupwindow(context.get(), livenessList).showPopupWindow(choiceView.get());
    });
    public BindingCommand classifyCommand = new BindingCommand(() -> {
        isCheckClassify=true;
        new TypePopupwindow(context.get(), classifyList).showPopupWindow(choiceView.get());
    });
    public BindingCommand sexCommand = new BindingCommand(() -> {
        isCheckClassify=false;
        new TypePopupwindow(context.get(), sexList).showPopupWindow(choiceView.get());
    });


    public RecommendFriendBody recommendFriendBody = new RecommendFriendBody();
    public int pageNum = 1;

    public void userList() {
        if (pageNum == 1) {
            recommendFriedList.clear();
        }
        recommendFriendBody.setMax(20);
        recommendFriendBody.setPage(pageNum);
        recommendFriendBody.setPhone(input.get());
        recommendFriendBody.setName(input.get());
        IdeaApi.getApiService().userList(recommendFriendBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<RecommendFriendEntity>(srl.get()) {
                    @Override
                    public void onSuccess(RecommendFriendEntity response) {
                        recommendFriedList.addAll(response.getData());
                        friendRecommendAdapter.notifyDataSetChanged();
                    }
                });
    }
}
