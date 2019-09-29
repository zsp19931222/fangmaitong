package com.techangkeji.module_information.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.techangkeji.model_information.BR;
import com.techangkeji.model_information.R;
import com.techangkeji.model_information.databinding.FragmentINoticeBinding;
import com.techangkeji.module_information.ui.adapter.NoticeAdapter;
import com.techangkeji.module_information.ui.view_model.NoticeFragmentViewModel;

import me.goldze.mvvmhabit.base.BaseLazyFragment;

public class NoticeFragment extends BaseLazyFragment<FragmentINoticeBinding, NoticeFragmentViewModel> {
    @Override
    public void fetchData() {
        NoticeAdapter noticeAdapter = new NoticeAdapter(R.layout.item_i_notice, viewModel.beanObservableList);
        viewModel.adapter.set(noticeAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(noticeAdapter);
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_i_notice;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.srl.set(binding.srl);
        binding.srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                viewModel.pageNum++;
                viewModel.getPlacardList();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.pageNum=1;
                viewModel.getPlacardList();
            }
        });
    }
}
