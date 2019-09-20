package com.techangkeji.module.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.module.BR;
import com.techangkeji.module.R;
import com.techangkeji.module.databinding.ActivityAreaSelectBinding;
import com.techangkeji.module.ui.adapter.AreaListAdapter;
import com.techangkeji.module.ui.view_model.AreaSelectViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * description:地区选择
 * author:created by Andy on 2019/9/20 23:26
 * email:zsp872126510@gmail.com
 */
@Route(path = ARouterPath.Public.AreaSelectActivity)
public class AreaSelectActivity extends BaseActivity<ActivityAreaSelectBinding, AreaSelectViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_area_select;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("切换城市");
        AreaListAdapter areaListAdapter = new AreaListAdapter(R.layout.area_content, R.layout.area_header, viewModel.areaListBeans);
        viewModel.adapter.set(areaListAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(areaListAdapter);
        viewModel.getData();
    }
}
