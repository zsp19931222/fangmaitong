package com.techangkeji.module_information.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.goldze.base.utils.SimulationData;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.techangkeji.model_information.BR;
import com.techangkeji.model_information.R;
import com.techangkeji.model_information.databinding.FragmentIInformationBinding;
import com.techangkeji.module_information.ui.adapter.InformationAdapter;
import com.techangkeji.module_information.ui.popup.InformationSortPopupwindow;
import com.techangkeji.module_information.ui.view_model.InformationViewModel;

import me.goldze.mvvmhabit.base.BaseLazyFragment;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.http.net.entity.FeaturedLabelEntity;

public class InformationFragment extends BaseLazyFragment<FragmentIInformationBinding, InformationViewModel> {
    @Override
    public void fetchData() {
        InformationAdapter informationAdapter = new InformationAdapter(R.layout.item_i_information, viewModel.dataBeans);
        viewModel.adapter.set(informationAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(informationAdapter);
        viewModel.getLabelList();
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_i_information;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.srl.set(binding.srl);
        viewModel.ll_view.set(binding.llView);
        viewModel.context.set(getActivity());
        binding.srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                viewModel.pageNum++;
                viewModel.getNewsList();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.pageNum=1;
                viewModel.getNewsList();
            }
        });
        RxSubscriptions.add(RxBus.getDefault().toObservable(InformationSortPopupwindow.InformationSortRxBean.class).subscribe(informationSortRxBean -> {
            viewModel.sortType.set(informationSortRxBean.getSelectPosition());
            viewModel.pageNum=1;
            viewModel.getNewsList();
        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(FeaturedLabelEntity.DataBean.class).subscribe(dataBean -> {
            viewModel.labelId.set(dataBean.getId() + "");
            viewModel.pageNum=1;
            viewModel.getNewsList();
        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(String.class).subscribe(s -> {
            if ("获取区域ID成功".equals(s)){
                viewModel.pageNum=1;
                viewModel.getNewsList();
            }
        }));
    }
}
