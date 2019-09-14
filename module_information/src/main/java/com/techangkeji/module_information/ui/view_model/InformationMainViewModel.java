package com.techangkeji.module_information.ui.view_model;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class InformationMainViewModel extends BaseViewModel {
    public ObservableField<Integer> showInformation = new ObservableField<>(View.VISIBLE);
    public ObservableField<Integer> showInvite = new ObservableField<>(View.GONE);
    public ObservableField<Integer> showNotice = new ObservableField<>(View.GONE);

    public InformationMainViewModel(@NonNull Application application) {
        super(application);
    }

    private void initViewShow() {
        showInformation.set(View.GONE);
        showInvite.set(View.GONE);
        showNotice.set(View.GONE);
    }

    public BindingCommand informationCommand = new BindingCommand(() -> {
        initViewShow();
        showInformation.set(View.VISIBLE);
    });
    public BindingCommand inviteCommand = new BindingCommand(() -> {
        initViewShow();
        showInvite.set(View.VISIBLE);
    });
    public BindingCommand noticeCommand = new BindingCommand(() -> {
        initViewShow();
        showNotice.set(View.VISIBLE);
    });
}
