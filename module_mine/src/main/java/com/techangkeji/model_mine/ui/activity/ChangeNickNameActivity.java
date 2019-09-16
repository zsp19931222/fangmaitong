package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityChangeNickNameBinding;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class ChangeNickNameActivity extends BaseActivity<ActivityChangeNickNameBinding, BaseViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_change_nick_name;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
    binding.title.setTitle("修改昵称");
    }
}
