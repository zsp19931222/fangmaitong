package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityCollectBinding;
import com.techangkeji.model_mine.ui.adapter.CollectAdapter;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class CollectActivity extends BaseActivity<ActivityCollectBinding, BaseViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_collect;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("我的收藏");
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("");
        }
        CollectAdapter collectAdapter = new CollectAdapter(R.layout.item_collect, strings);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(collectAdapter);
    }
}
