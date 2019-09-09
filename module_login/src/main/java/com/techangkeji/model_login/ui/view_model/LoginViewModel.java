package com.techangkeji.model_login.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.alibaba.android.arouter.launcher.ARouter;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_login.ui.activity.RegisterActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 13:48
 * email:zsp872126510@gmail.com
 */
public class LoginViewModel extends BaseViewModel {
    public ObservableField<String> phoneNum = new ObservableField<>("");
    public ObservableField<String> pwNum = new ObservableField<>("");

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description: 跳转注册
     * author: Andy
     * date: 2019/9/9 0009 15:16
     */
    public BindingCommand intent2RegisterActivity = new BindingCommand(() -> startActivity(RegisterActivity.class));

    /**
     * description:登录
     * author: Andy
     * date: 2019/9/9 0009 17:12
     */
    public BindingCommand intent2MainActivity = new BindingCommand(() -> {ARouter.getInstance().build(ARouterPath.Main.PAGER_MAIN).navigation();
        ZLog.d("点击");
    });

}
