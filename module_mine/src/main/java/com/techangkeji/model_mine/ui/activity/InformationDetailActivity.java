package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityInformationDetailBinding;
import com.techangkeji.model_mine.ui.adapter.InformationDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class InformationDetailActivity extends BaseActivity<ActivityInformationDetailBinding, BaseViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_information_detail;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            strings.add("");
        }
        InformationDetailAdapter adapter = new InformationDetailAdapter(R.layout.item_infromation_detail, strings);
        View header = LayoutInflater.from(this).inflate(R.layout.head_information_detail, null);
        adapter.addHeaderView(header);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(adapter);
    }

    private void initHeader() {
    }
}
