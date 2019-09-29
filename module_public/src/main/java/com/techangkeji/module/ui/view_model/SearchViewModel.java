package com.techangkeji.module.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * description:
 * author:created by Andy on 2019/9/29 0029 16:05
 * email:zsp872126510@gmail.com
 */
public class SearchViewModel extends BaseViewModel {
    public ObservableField<String> input=new ObservableField<>("");
    public SearchViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand finishCommand = new BindingCommand(() -> finish());
}
