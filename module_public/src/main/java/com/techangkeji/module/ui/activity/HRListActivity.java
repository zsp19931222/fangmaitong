package com.techangkeji.module.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.techangkeji.module.BR;
import com.techangkeji.module.R;
import com.techangkeji.module.databinding.ActivityHrListBinding;
import com.techangkeji.module.ui.view_model.HRListViewModel;
import com.techangkeji.module_hr.ui.adapter.HRAdapter;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * description:
 * author:created by Andy on 2019/10/8 0008 16:32
 * email:zsp872126510@gmail.com
 */
@Route(path = ARouterPath.Public.HRListActivity)
public class HRListActivity extends BaseActivity<ActivityHrListBinding, HRListViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_hr_list;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("房源列表");
        viewModel.srl.set(binding.srl);
        viewModel.friendId = getIntent().getExtras().getInt("id");
        viewModel.getUserDetailData();
        viewModel.getData();
        viewModel.hrAdapter = new HRAdapter(com.techangkeji.module_hr.R.layout.item_home_resource, viewModel.buildingList);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(viewModel.hrAdapter);
        binding.srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                viewModel.pageNum++;
                viewModel.getData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.pageNum=1;
                viewModel.getData();
            }
        });
    }
}
