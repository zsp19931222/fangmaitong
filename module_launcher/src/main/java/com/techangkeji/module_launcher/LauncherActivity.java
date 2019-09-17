package com.techangkeji.module_launcher;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.alibaba.android.arouter.launcher.ARouter;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.module_launcher.databinding.ActivityLauncherBinding;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.IsNullUtil;


public class LauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (IsNullUtil.getInstance().isEmpty(LocalDataHelper.getInstance().getUserInfo())) {
            ARouter.getInstance().build(ARouterPath.Login.LoginActivity).navigation();
        }else {
            ARouter.getInstance().build(ARouterPath.Main.PAGER_MAIN).navigation();
        }
        finish();
    }
}
