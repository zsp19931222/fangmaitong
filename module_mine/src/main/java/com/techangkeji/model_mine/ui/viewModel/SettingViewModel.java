package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.techangkeji.model_mine.ui.activity.AboutActivity;
import com.techangkeji.model_mine.ui.activity.OtherBindingAccountActivity;
import com.techangkeji.model_mine.ui.activity.ChangePasswordActivity;
import com.techangkeji.model_mine.ui.activity.ChangePhoneActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class SettingViewModel extends BaseViewModel {
    public SettingViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand clearCacheCommand = new BindingCommand(() -> {
    });
    public BindingCommand changePhoneCommand = new BindingCommand(() -> startActivity(ChangePhoneActivity.class));
    public BindingCommand changePasswordCommand = new BindingCommand(() -> {
        startActivity(ChangePasswordActivity.class);
    });
    public BindingCommand otherBindingCommand = new BindingCommand(() -> {
        startActivity(OtherBindingAccountActivity.class);
    });
    public BindingCommand aboutCommand = new BindingCommand(() -> {
        startActivity(AboutActivity.class);
    });
}
