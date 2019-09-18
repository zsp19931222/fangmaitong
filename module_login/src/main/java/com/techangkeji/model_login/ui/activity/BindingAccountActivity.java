package com.techangkeji.model_login.ui.activity;

import android.os.Bundle;

import com.techangkeji.model_login.BR;
import com.techangkeji.model_login.R;
import com.techangkeji.model_login.databinding.ActivityBindingAccountBinding;
import com.techangkeji.model_login.ui.view_model.BindingAccountViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.http.net.entity.login.RegisterEntity;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 15:24
 * email:zsp872126510@gmail.com
 */
public class BindingAccountActivity extends BaseActivity<ActivityBindingAccountBinding, BindingAccountViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_binding_account;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        try {
            viewModel.id.set(getIntent().getExtras().getLong("id"));
            viewModel.type.set(getIntent().getExtras().getInt("type"));
            ZLog.d(viewModel.id.get());
            ZLog.d(viewModel.type.get());
        } catch (Exception e) {
            ZLog.d(e);
        }
    }
}
