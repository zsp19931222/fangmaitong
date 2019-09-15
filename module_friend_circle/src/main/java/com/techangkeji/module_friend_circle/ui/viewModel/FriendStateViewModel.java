package com.techangkeji.module_friend_circle.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.goldze.base.router.ARouterPath;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class FriendStateViewModel extends BaseViewModel {
    public FriendStateViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand releaseCircle=new BindingCommand(() -> ARouter.getInstance().build(ARouterPath.Message.ReleaseInformationActivity).navigation());
}
