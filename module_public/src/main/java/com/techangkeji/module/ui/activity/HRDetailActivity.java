package com.techangkeji.module.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.goldze.base.utils.ShareUtil;
import com.techangkeji.module.R;
import com.techangkeji.module.databinding.ActivityHrDetailBinding;
import com.techangkeji.module.ui.adapter.HRDetailAdapter;
import com.techangkeji.module.ui.bean.HRDetailAdapterBean;
import com.techangkeji.module.ui.view_model.HRDetailViewModel;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.ZLog;

@Route(path = ARouterPath.Public.HRDetailActivity)
public class HRDetailActivity extends BaseActivity<ActivityHrDetailBinding, HRDetailViewModel> {
    private List<HRDetailAdapterBean> hrDetailAdapterBeans = new ArrayList<>();
    private HRDetailAdapter hrDetailAdapter;

    private int id;

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
        hrDetailAdapter = new HRDetailAdapter(hrDetailAdapterBeans, this, viewModel);
        viewModel.adapterObservableField.set(hrDetailAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(hrDetailAdapter);
        for (int i = 1; i < 10; i++) {
            init(i);
        }
        hrDetailAdapter.notifyDataSetChanged();
        try {
            id = getIntent().getExtras().getInt("id");
            viewModel.buildingInfo(id);
            viewModel.recommendBuilding(id);
        } catch (Exception e) {

        }

    }

    private void init(int i) {
        HRDetailAdapterBean homeAdapterBean = new HRDetailAdapterBean();
        homeAdapterBean.setType(i);
        hrDetailAdapterBeans.add(homeAdapterBean);
        binding.tvHrShare.setOnClickListener(view -> ShareUtil.getInstance().share(this, binding.rv));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ZLog.d("onActivityResult", "onActivityResult");
        ZLog.d("onActivityResult");
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
