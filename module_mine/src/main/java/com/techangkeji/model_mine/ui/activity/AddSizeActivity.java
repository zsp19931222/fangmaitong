package com.techangkeji.model_mine.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityAddSizeBinding;
import com.techangkeji.model_mine.ui.viewModel.HouseResourceReleaseViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

public class AddSizeActivity extends BaseActivity<ActivityAddSizeBinding, HouseResourceReleaseViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_add_size;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        try {
            int position=getIntent().getExtras().getInt("position");//点击编辑

        }catch (Exception e){//点击添加

        }
    }
}
