package com.techangkeji.module.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.KeyboardUtils;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.module.BR;
import com.techangkeji.module.R;
import com.techangkeji.module.databinding.ActivitySearchBinding;
import com.techangkeji.module.ui.view_model.SearchViewModel;
import com.techangkeji.module_hr.ui.adapter.HRAdapter;
import com.techangkeji.module_information.ui.adapter.InformationAdapter;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * description:
 * author:created by Andy on 2019/9/29 0029 16:04
 * email:zsp872126510@gmail.com
 */
@Route(path = ARouterPath.Public.SearchActivity)
public class SearchActivity extends BaseActivity<ActivitySearchBinding, SearchViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_search;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.from = getIntent().getExtras().getInt("from");
        if (viewModel.from == 0) {
            viewModel.hint.set("搜索本区（开发商名/楼盘名/商圈/标签）");
            viewModel.showHR.set(View.VISIBLE);
            viewModel.showInformation.set(View.GONE);
        } else {
            viewModel.hint.set("搜索本区资讯");
            viewModel.showHR.set(View.GONE);
            viewModel.showInformation.set(View.VISIBLE);
        }
        viewModel.srl.set(binding.srl);
        viewModel.hrAdapter = new HRAdapter(com.techangkeji.module_hr.R.layout.item_home_resource, viewModel.buildingList);
        binding.rvHr.setLayoutManager(new LinearLayoutManager(this));
        binding.rvHr.setAdapter(viewModel.hrAdapter);

        viewModel.informationAdapter = new InformationAdapter(com.techangkeji.model_information.R.layout.item_i_information, viewModel.dataBeans);
        binding.rvInformation.setLayoutManager(new LinearLayoutManager(this));
        binding.rvInformation.setAdapter(viewModel.informationAdapter);

        viewModel.input.set(getIntent().getExtras().getString("input"));
        viewModel.searchData();
        binding.et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.searchData();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        new Handler().postDelayed(() -> KeyboardUtils.showSoftInput(binding.et),500);
    }
}
