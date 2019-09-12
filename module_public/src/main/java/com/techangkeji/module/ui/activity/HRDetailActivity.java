package com.techangkeji.module.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.module.R;
import com.techangkeji.module.databinding.ActivityHrDetailBinding;
import com.techangkeji.module.ui.adapter.HRDetailAdapter;
import com.techangkeji.module.ui.bean.HRDetailAdapterBean;
import com.techangkeji.module.ui.view_model.HRDetailViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;

@Route(path = ARouterPath.Public.HRDetailActivity)
public class HRDetailActivity extends BaseActivity<ActivityHrDetailBinding, HRDetailViewModel> {
    private List<HRDetailAdapterBean> hrDetailAdapterBeans = new ArrayList<>();
    private HRDetailAdapter hrDetailAdapter;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_hr_detail;
    }

    @Override
    public int initVariableId() {
        return com.techangkeji.module.BR.viewModel;
    }

    @Override
    public void initData() {
        hrDetailAdapter = new HRDetailAdapter(hrDetailAdapterBeans,this);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(hrDetailAdapter);
        for (int i = 1; i < 10; i++) {
            init(i);
        }
        hrDetailAdapter.notifyDataSetChanged();
    }

    private void init(int i) {

        HRDetailAdapterBean homeAdapterBean = new HRDetailAdapterBean();
        homeAdapterBean.setType(i);
        hrDetailAdapterBeans.add(homeAdapterBean);
    }
}