package com.techangkeji.model_mine.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.FragmentMineBinding;
import com.techangkeji.model_mine.ui.viewModel.MineViewModel;

import me.goldze.mvvmhabit.base.BaseLazyFragment;
@Route(path = ARouterPath.Mine.MinePage)
public class MineFragment extends BaseLazyFragment<FragmentMineBinding, MineViewModel> {
    @Override
    public void fetchData() {

    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_mine;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
