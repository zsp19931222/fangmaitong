package com.techangkeji.module_information.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.goldze.base.utils.SimulationData;
import com.techangkeji.model_information.BR;
import com.techangkeji.model_information.R;
import com.techangkeji.model_information.databinding.FragmentIInformationBinding;
import com.techangkeji.module_information.ui.adapter.InformationAdapter;

import me.goldze.mvvmhabit.base.BaseLazyFragment;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class InformationFragment extends BaseLazyFragment<FragmentIInformationBinding, BaseViewModel> {
    @Override
    public void fetchData() {
        InformationAdapter informationAdapter = new InformationAdapter(R.layout.item_i_information, SimulationData.simulation());
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(informationAdapter);
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

    }
}
