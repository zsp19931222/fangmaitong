package com.techangkeji.module.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.CommentListBody;
import me.goldze.mvvmhabit.http.net.entity.information.CommentListEntity;
import me.goldze.mvvmhabit.utils.RxUtils;

public class CommentViewModel extends BaseViewModel {

    public CommentViewModel(@NonNull Application application) {
        super(application);
    }

}
