package com.techangkeji.module.ui.activity;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.goldze.base.view.TitleVIew;
import com.techangkeji.module.BR;
import com.techangkeji.module.R;
import com.techangkeji.module.databinding.ActivityHouseSizeBinding;
import com.techangkeji.module.databinding.ActivityHouseStateBinding;
import com.techangkeji.module.ui.adapter.HouseSizeAdapter;
import com.techangkeji.module.ui.adapter.HouseStateAdapter;
import com.techangkeji.module.ui.view_model.HouseSizeViewModel;
import com.techangkeji.module.ui.view_model.HouseStateViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.view.MyVerticalDecoration;

public class HouseStateActivity extends BaseActivity<ActivityHouseStateBinding, HouseStateViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_house_state;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        TitleVIew titleVIew= (TitleVIew) binding.title;
        titleVIew.setTitle("楼盘动态");
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("");
        }
        HouseStateAdapter houseSizeAdapter = new HouseStateAdapter(R.layout.item_house_state, strings);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.addItemDecoration(new MyVerticalDecoration(this, ContextCompat.getColor(this, R.color.color_f6), 1, 0, 0, true));
        binding.rv.setAdapter(houseSizeAdapter);
    }
}
