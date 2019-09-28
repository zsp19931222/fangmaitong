package com.techangkeji.model_home.ui.view_midel;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.techangkeji.model_home.ui.adapter.FriendRecommendAdapter;
import com.techangkeji.module_hr.ui.popup.AreaPopupwindow;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.entity.RecommendFriendEntity;

/**
 * description:
 * author:created by Andy on 2019/9/28 23:48
 * email:zsp872126510@gmail.com
 */
public class RecommendFriendViewModel extends BaseViewModel {
    public FriendRecommendAdapter friendRecommendAdapter;
    public ObservableField<Context> context=new ObservableField<>();
    public ObservableField<View> choiceView=new ObservableField<>();
    public ObservableList<RecommendFriendEntity.DataBean> recommendFriedList=new ObservableArrayList<>();

    public RecommendFriendViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand area = new BindingCommand(() -> {
        new AreaPopupwindow(context.get()).showPopupWindow(choiceView.get());
    });
}
