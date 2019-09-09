package com.techangkeji.model_login.ui.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_login.BR;
import com.techangkeji.model_login.R;
import com.techangkeji.model_login.databinding.ActivityLoginBinding;
import com.techangkeji.model_login.ui.view_model.LoginViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 14:14
 * email:zsp872126510@gmail.com
 */
@Route(path = ARouterPath.Login.LoginActivity)
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
    }
}
