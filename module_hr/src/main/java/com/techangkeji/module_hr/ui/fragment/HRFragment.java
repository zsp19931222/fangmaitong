package com.techangkeji.module_hr.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.module_hr.BR;
import com.techangkeji.module_hr.R;
import com.techangkeji.module_hr.databinding.FragmentHrBinding;
import com.techangkeji.module_hr.ui.adapter.HRAdapter;
import com.techangkeji.module_hr.ui.view_model.HRViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseLazyFragment;
@Route(path = ARouterPath.HouseResource.HRFragment)
public class HRFragment extends BaseLazyFragment<FragmentHrBinding, HRViewModel> {
    @Override
    public void fetchData() {
        List<String> strings=new ArrayList<>();
        for (int i = 0; i <15 ; i++) {
            strings.add("");
        }
        HRAdapter hrAdapter=new HRAdapter(R.layout.item_home_resource,strings);
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(hrAdapter);
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_hr;
    }

    @Override
    public void initData() {
    viewModel.context.set(getContext());
    viewModel.choiceView.set(binding.llFhChoice);
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

}
