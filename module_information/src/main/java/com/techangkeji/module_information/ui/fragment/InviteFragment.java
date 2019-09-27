package com.techangkeji.module_information.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.goldze.base.eventbus.PriceRxBusBean;
import com.goldze.base.eventbus.SortRxBusBean;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.techangkeji.model_information.BR;
import com.techangkeji.model_information.R;
import com.techangkeji.model_information.databinding.FragmentIInviteBinding;
import com.techangkeji.module_hr.ui.bean.AreaPopupBean;
import com.techangkeji.module_information.ui.adapter.InviteAdapter;
import com.techangkeji.module_information.ui.view_model.InviteViewModel;

import me.goldze.mvvmhabit.base.BaseLazyFragment;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;

public class InviteFragment extends BaseLazyFragment<FragmentIInviteBinding, InviteViewModel> {
    @Override
    public void fetchData() {
        InviteAdapter inviteAdapter = new InviteAdapter(R.layout.item_i_invite, viewModel.dataBeans);
        viewModel.adapter.set(inviteAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(inviteAdapter);
        RxSubscriptions.add(RxBus.getDefault().toObservable(PriceRxBusBean.class).subscribe(priceRxBusBean -> {//薪资选择结果
            viewModel.recruitmentBody.setMoneyDown(Double.valueOf(priceRxBusBean.getMin()));
            viewModel.recruitmentBody.setMoneyUp(Double.valueOf(priceRxBusBean.getMax()));
            refreshData();
        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(SortRxBusBean.class).subscribe(sortRxBusBean -> {//工作性质/排序选择结果
            viewModel.recruitmentBody.setWorkNature(sortRxBusBean.getSort());
            refreshData();
        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(AreaPopupBean.class).subscribe(areaPopupBean -> {//地区选择结果
            viewModel.recruitmentBody.setAreaId(Integer.valueOf(areaPopupBean.getId()));
            refreshData();
        }));
        binding.srl.setEnableAutoLoadMore(true);
        binding.srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                viewModel.pageNum++;
                viewModel.recruitmentsList();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshData();
            }
        });
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_i_invite;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
    viewModel.context.set(getActivity());
    viewModel.choiceView.set(binding.choiceView);
    viewModel.srl.set(binding.srl);
    viewModel.recruitmentsList();
    }

    /**
    * description: 更新数据
    * author: Andy
    * date: 2019/9/27 0027 17:32
    */
    private void refreshData(){
        viewModel.pageNum=1;
        viewModel.recruitmentsList();
    }
}
