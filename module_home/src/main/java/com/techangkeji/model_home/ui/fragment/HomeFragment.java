package com.techangkeji.model_home.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_home.R;
import com.techangkeji.model_home.databinding.FragmentHomeBinding;
import com.techangkeji.model_home.ui.view_midel.HomeViewModel;

import me.goldze.mvvmhabit.base.BaseLazyFragment;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 17:15
 * email:zsp872126510@gmail.com
 */
@Route(path = ARouterPath.Home.HomeFragment)
public class HomeFragment extends BaseLazyFragment<FragmentHomeBinding, HomeViewModel> {
    @Override
    public void fetchData() {

    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_home;
    }

    @Override
    public int initVariableId() {
        return com.techangkeji.model_home.BR.viewModel;
    }
}
