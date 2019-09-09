package com.techangkeji.model_login.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.techangkeji.model_login.ui.activity.RegisterActivity;
import com.techangkeji.model_login.ui.activity.ResetPasswordActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 15:24
 * email:zsp872126510@gmail.com
 */
public class BindingAccountViewModel extends BaseViewModel {
    public BindingAccountViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description: 跳转重置密码
     * author: Andy
     * date: 2019/9/9 0009 15:58
     */
    public BindingCommand intent2ResetPasswordActivity = new BindingCommand(() -> startActivity(ResetPasswordActivity.class));
    /**
     * description: 跳转注册
     * author: Andy
     * date: 2019/9/9 0009 15:16
     */
    public BindingCommand intent2RegisterActivity = new BindingCommand(() -> startActivity(RegisterActivity.class));
}
