package com.techangkeji.model_home.ui.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_home.BR;
import com.techangkeji.model_home.R;
import com.techangkeji.model_home.databinding.ActivityHousingInformationBinding;
import com.techangkeji.model_home.ui.view_midel.HousingInformationViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * description:房贷咨询
 * author:created by Andy on 2019/9/16 0016 17:44
 * email:zsp872126510@gmail.com
 */
@Route(path = ARouterPath.Home.HousingInformationActivity)
public class HousingInformationActivity extends BaseActivity<ActivityHousingInformationBinding, HousingInformationViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_housing_information;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
    binding.title.setTitle("房贷咨询");
    }
}
