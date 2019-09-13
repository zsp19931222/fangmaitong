package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.techangkeji.model_mine.ui.activity.AgreementActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class AboutViewModel extends BaseViewModel {
    public AboutViewModel(@NonNull Application application) {
        super(application);
    }
    public BindingCommand agreementCommand=new BindingCommand(() -> startActivity(AgreementActivity.class));
}
