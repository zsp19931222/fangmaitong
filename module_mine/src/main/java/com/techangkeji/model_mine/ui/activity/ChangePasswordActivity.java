package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.core.content.ContextCompat;

import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityChangePasswordBinding;
import com.techangkeji.model_mine.ui.viewModel.ChangePasswordViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

public class ChangePasswordActivity extends BaseActivity<ActivityChangePasswordBinding, ChangePasswordViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_change_password;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("修改密码");
        binding.etPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() >= 6 && charSequence.length() < 10) {
                    viewModel.showViewBg1.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_FF8C00));
                    viewModel.showViewBg2.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_f6));
                    viewModel.showViewBg3.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_f6));
                } else if (charSequence.length() >= 10 && charSequence.length() < 15) {
                    viewModel.showViewBg1.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_FF8C00));
                    viewModel.showViewBg2.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_FF8C00));
                    viewModel.showViewBg3.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_f6));
                } else if (charSequence.length() >= 15) {
                    viewModel.showViewBg1.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_FF8C00));
                    viewModel.showViewBg2.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_FF8C00));
                    viewModel.showViewBg3.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_FF8C00));
                } else {
                    viewModel.showViewBg1.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_f6));
                    viewModel.showViewBg2.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_f6));
                    viewModel.showViewBg3.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_f6));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() >= 6 && charSequence.length() < 10) {
                    viewModel.showViewBg11.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_FF8C00));
                    viewModel.showViewBg21.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_f6));
                    viewModel.showViewBg31.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_f6));
                } else if (charSequence.length() >= 10 && charSequence.length() < 15) {
                    viewModel.showViewBg11.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_FF8C00));
                    viewModel.showViewBg21.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_FF8C00));
                    viewModel.showViewBg31.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_f6));
                } else if (charSequence.length() >= 15) {
                    viewModel.showViewBg11.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_FF8C00));
                    viewModel.showViewBg21.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_FF8C00));
                    viewModel.showViewBg31.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_FF8C00));
                } else {
                    viewModel.showViewBg11.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_f6));
                    viewModel.showViewBg21.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_f6));
                    viewModel.showViewBg31.set(ContextCompat.getColor(ChangePasswordActivity.this, R.color.color_f6));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
