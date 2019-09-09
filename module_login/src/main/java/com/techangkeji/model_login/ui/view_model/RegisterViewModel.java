package com.techangkeji.model_login.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.techangkeji.model_login.ui.activity.BindingAccountActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 14:45
 * email:zsp872126510@gmail.com
 */
public class RegisterViewModel extends BaseViewModel {
    public RegisterViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description:跳转到绑定已有账号
     * author: Andy
     * date: 2019/9/9 0009 15:34
     */
    public BindingCommand intent2BindingAccountActivity = new BindingCommand(() -> startActivity(BindingAccountActivity.class));
}
