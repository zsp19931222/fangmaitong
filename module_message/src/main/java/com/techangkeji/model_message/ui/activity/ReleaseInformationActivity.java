package com.techangkeji.model_message.ui.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_message.BR;
import com.techangkeji.model_message.R;
import com.techangkeji.model_message.databinding.ActivityReleaseInformationBinding;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
@Route(path = ARouterPath.Message.ReleaseInformationActivity)
public class ReleaseInformationActivity extends BaseActivity<ActivityReleaseInformationBinding, BaseViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_release_information;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
