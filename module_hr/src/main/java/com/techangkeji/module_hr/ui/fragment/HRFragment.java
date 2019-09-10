package com.techangkeji.module_hr.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.base.BaseTabAdapter;
import com.goldze.base.router.ARouterPath;
import com.google.android.material.tabs.TabLayout;
import com.techangkeji.module_hr.BR;
import com.techangkeji.module_hr.R;
import com.techangkeji.module_hr.databinding.FragmentHrBinding;
import com.techangkeji.module_hr.ui.view_model.HRViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseLazyFragment;
@Route(path = ARouterPath.HouseResource.HRFragment)
public class HRFragment extends BaseLazyFragment<FragmentHrBinding, HRViewModel> {
    @Override
    public void fetchData() {
        initTabTitle();
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

    private void initTabTitle(){
        List<String> tabStrings=new ArrayList<>();
        tabStrings.add("区域");
        tabStrings.add("户型");
        tabStrings.add("价格");
        tabStrings.add("筛选");
        tabStrings.add("排序");
        List<Fragment> fragments = new ArrayList<>();

    }
}
