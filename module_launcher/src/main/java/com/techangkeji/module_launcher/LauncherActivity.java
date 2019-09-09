package com.techangkeji.module_launcher;

import android.os.Bundle;
import android.os.Handler;

import com.alibaba.android.arouter.launcher.ARouter;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.module_launcher.databinding.ActivityLauncherBinding;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;


public class LauncherActivity extends BaseActivity<ActivityLauncherBinding, BaseViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_launcher;
    }

    @Override
    public int initVariableId() {
        return com.techangkeji.module_launcher.BR.viewModel;
    }

    @Override
    public void initData() {
        //跳转到登录页面
        ARouter.getInstance().build(ARouterPath.Login.LoginActivity).navigation();
        new Handler().postDelayed(this::finish, 500);
    }
}
