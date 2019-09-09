package com.techangkeji.model_login.ui.activity;

import android.os.Bundle;

import com.techangkeji.model_login.BR;
import com.techangkeji.model_login.R;
import com.techangkeji.model_login.databinding.ActivityResetPasswordBinding;
import com.techangkeji.model_login.ui.view_model.ResetPasswordViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 15:38
 * email:zsp872126510@gmail.com
 */
public class ResetPasswordActivity extends BaseActivity<ActivityResetPasswordBinding, ResetPasswordViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_reset_password;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
