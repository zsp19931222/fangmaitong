package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityPersonBinding;
import com.techangkeji.model_mine.ui.viewModel.PersonViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

public class PersonActivity extends BaseActivity<ActivityPersonBinding, PersonViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_person;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
    }
}
