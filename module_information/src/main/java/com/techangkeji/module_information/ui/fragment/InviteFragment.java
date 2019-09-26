package com.techangkeji.module_information.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.goldze.base.eventbus.PriceRxBusBean;
import com.goldze.base.eventbus.SortRxBusBean;
import com.goldze.base.utils.SimulationData;
import com.techangkeji.model_information.BR;
import com.techangkeji.model_information.R;
import com.techangkeji.model_information.databinding.FragmentIInviteBinding;
import com.techangkeji.module_information.ui.adapter.InviteAdapter;
import com.techangkeji.module_information.ui.view_model.InviteViewModel;

import me.goldze.mvvmhabit.base.BaseLazyFragment;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;

public class InviteFragment extends BaseLazyFragment<FragmentIInviteBinding, InviteViewModel> {
    @Override
    public void fetchData() {
        InviteAdapter inviteAdapter = new InviteAdapter(R.layout.item_i_invite, SimulationData.simulation());
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(inviteAdapter);
        RxSubscriptions.add(RxBus.getDefault().toObservable(PriceRxBusBean.class).subscribe(priceRxBusBean -> {
            viewModel.moneyDown.set(priceRxBusBean.getMin());
            viewModel.moneyUp.set(priceRxBusBean.getMax());
        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(SortRxBusBean.class).subscribe(sortRxBusBean -> {
            viewModel.workNature.set(sortRxBusBean.getSort());
        }));
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
    viewModel.recruitmentsList();
    }
}
