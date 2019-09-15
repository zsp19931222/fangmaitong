package com.techangkeji.module_information.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.goldze.base.router.ARouterPath;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class InviteDetailViewModel extends BaseViewModel {
    public InviteDetailViewModel(@NonNull Application application) {
        super(application);
    }

    //跳转到动态页面
    public BindingCommand circleCommand = new BindingCommand(() -> ARouter.getInstance().build(ARouterPath.FriendCircle.PersonCircleStateActivity).navigation());
}
