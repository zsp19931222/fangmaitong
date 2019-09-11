package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityHouseResourceBinding;
import com.techangkeji.model_mine.ui.adapter.HouseResourceAdapter;
import com.techangkeji.model_mine.ui.viewModel.HouseResourceViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;

public class HouseResourceActivity extends BaseActivity<ActivityHouseResourceBinding, HouseResourceViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_house_resource;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            strings.add("");
        }
        HouseResourceAdapter inviteInformationAdapter = new HouseResourceAdapter(R.layout.item_my_home_resource, strings);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(inviteInformationAdapter);
    }
}
