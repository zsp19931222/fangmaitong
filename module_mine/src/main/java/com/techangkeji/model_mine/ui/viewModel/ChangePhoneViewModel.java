package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class ChangePhoneViewModel extends BaseViewModel {
    public ObservableField<Integer> showPW = new ObservableField<>(View.VISIBLE);
    public ObservableField<Integer> showAuthCode = new ObservableField<>(View.GONE);
    public ObservableField<Boolean> selectPW = new ObservableField<>(true);
    public ObservableField<Boolean> selectAuthCode = new ObservableField<>(false);

    public ChangePhoneViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand pwCommand = new BindingCommand(() -> {
        showPW.set(View.VISIBLE);
        selectPW.set(true);
        showAuthCode.set(View.GONE);
        selectAuthCode.set(false);
    });

    public BindingCommand authCodeCommand = new BindingCommand(() -> {
        showPW.set(View.GONE);
        selectPW.set(false);
        showAuthCode.set(View.VISIBLE);
        selectAuthCode.set(true);
    });


}
