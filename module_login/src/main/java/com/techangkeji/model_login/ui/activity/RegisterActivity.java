package com.techangkeji.model_login.ui.activity;

import android.os.Bundle;

import com.techangkeji.model_login.BR;
import com.techangkeji.model_login.R;
import com.techangkeji.model_login.databinding.ActivityRegisterBinding;
import com.techangkeji.model_login.ui.view_model.RegisterViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * description:注册页面
 * author:created by Andy on 2019/9/9 0009 14:45
 * email:zsp872126510@gmail.com
 */
public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_register;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
