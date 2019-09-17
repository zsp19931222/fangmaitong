package com.techangkeji.model_login.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.core.content.ContextCompat;

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

    @Override
    public void initData() {
        viewModel.context.set(this);
        binding.etLPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() >= 6 && charSequence.length() < 10) {
                    viewModel.showViewBg1.set(ContextCompat.getColor(RegisterActivity.this, R.color.color_FF8C00));
                    viewModel.showViewBg2.set(ContextCompat.getColor(RegisterActivity.this, R.color.color_f6));
                    viewModel.showViewBg3.set(ContextCompat.getColor(RegisterActivity.this, R.color.color_f6));
                } else if (charSequence.length() >= 10 && charSequence.length() < 15) {
                    viewModel.showViewBg1.set(ContextCompat.getColor(RegisterActivity.this, R.color.color_FF8C00));
                    viewModel.showViewBg2.set(ContextCompat.getColor(RegisterActivity.this, R.color.color_FF8C00));
                    viewModel.showViewBg3.set(ContextCompat.getColor(RegisterActivity.this, R.color.color_f6));
                } else if (charSequence.length() >= 15) {
                    viewModel.showViewBg1.set(ContextCompat.getColor(RegisterActivity.this, R.color.color_FF8C00));
                    viewModel.showViewBg2.set(ContextCompat.getColor(RegisterActivity.this, R.color.color_FF8C00));
                    viewModel.showViewBg3.set(ContextCompat.getColor(RegisterActivity.this, R.color.color_FF8C00));
                } else {
                    viewModel.showViewBg1.set(ContextCompat.getColor(RegisterActivity.this, R.color.color_f6));
                    viewModel.showViewBg2.set(ContextCompat.getColor(RegisterActivity.this, R.color.color_f6));
                    viewModel.showViewBg3.set(ContextCompat.getColor(RegisterActivity.this, R.color.color_f6));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
