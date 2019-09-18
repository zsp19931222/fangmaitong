package com.techangkeji.model_login.ui.view_model;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.goldze.base.router.ARouterPath;
import com.goldze.base.utils.OtherLoginUtil;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.exceptions.HyphenateException;
import com.techangkeji.hyphenate.chatuidemo.DemoHelper;
import com.techangkeji.hyphenate.chatuidemo.db.DemoDBManager;
import com.techangkeji.model_login.R;
import com.techangkeji.model_login.ui.activity.BindingAccountActivity;
import com.techangkeji.model_login.ui.activity.LoginActivity;
import com.techangkeji.model_login.ui.activity.RegisterActivity;
import com.techangkeji.model_login.ui.activity.ResetPasswordActivity;
import com.techangkeji.model_login.ui.popupwindow.PerfectionUserInfoPopup;
import com.techangkeji.model_login.ui.util.LoginUtil;
import com.techangkeji.model_message.MessageModuleInit;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import me.goldze.mvvmhabit.base.AppManager;
import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.LoginBody;
import me.goldze.mvvmhabit.http.net.body.RegisterBody;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.login.RegisterEntity;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 13:48
 * email:zsp872126510@gmail.com
 */
public class LoginViewModel extends BaseViewModel {
    public ObservableField<String> phoneNum = new ObservableField<>("13983251013");
    public ObservableField<String> pwNum = new ObservableField<>("123456");
    public ObservableField<Context> context = new ObservableField<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description: 忘记密码
     * author: Andy
     * date: 2019/9/18 0018 17:05
     */
    public BindingCommand forgetPWCommand = new BindingCommand(() -> startActivity(ResetPasswordActivity.class));

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
    public BindingCommand intent2MainActivity = new BindingCommand(this::login
    );

    private void login() {
        LoginBody registerBody = new LoginBody(pwNum.get(), phoneNum.get(), 1);
        IdeaApi.getApiService()
                .login(registerBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable1 -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity<RegisterEntity>>(this) {
                    @Override
                    public void onSuccess(SuccessEntity<RegisterEntity> response) {
                        showDialog("正在登录请稍后");
                        LoginUtil.getInstance().saveUserInfo(response);
                    }
                });
    }

    /**
     * description: QQ登录
     * author: Andy
     * date: 2019/9/18 0018 10:37
     */
    public BindingCommand qqCommand = new BindingCommand(() -> {
        OtherLoginUtil.getInstance().login(context.get(), "QQ");

    });

    /**
     * description: 微信登录
     * author: Andy
     * date: 2019/9/18 0018 10:37
     */
    public BindingCommand WeChatCommand = new BindingCommand(() -> {
        OtherLoginUtil.getInstance().login(context.get(), "WECHAT");

    });


    /**
     * description:绑定第三方账号
     * author: Andy
     * date: 2019/8/8 0008 11:36
     */
    public void bindThird(OtherLoginUtil.OtherLoginBean otherLoginBean) {
        LoginBody loginBody;
        int type;
        if (SHARE_MEDIA.QQ.equals(otherLoginBean.getType())) {//qq登录
            type=4;
            loginBody = new LoginBody(type, otherLoginBean.getOpenid(), otherLoginBean.getCity(), otherLoginBean.getName(), otherLoginBean.getIconurl(), otherLoginBean.getProvince());
        } else {
            type=3;
            loginBody = new LoginBody(type, otherLoginBean.getOpenid(), otherLoginBean.getCity(), otherLoginBean.getName(), otherLoginBean.getIconurl(), otherLoginBean.getProvince());
        }
        IdeaApi.getApiService()
                .login(loginBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity<RegisterEntity>>(this) {
                    @Override
                    public void onSuccess(SuccessEntity<RegisterEntity> response) {
                        if (IsNullUtil.getInstance().isEmpty(response.getContent().getPhone())) {//走绑定流程
                            Bundle bundle = new Bundle();
                            bundle.putLong("id", response.getContent().getId());
                            bundle.putInt("type", type);
                            SPUtils.getInstance().put("token",response.getContent().getJwtToken().getToken());
                            startActivity(BindingAccountActivity.class, bundle);
                        } else {//已经绑定直接登录
                            showDialog("正在登录请稍后");
                            LoginUtil.getInstance().saveUserInfo(response);
                        }
                    }
                });

    }

    @Override
    public void onPause() {
        super.onPause();
        dismissDialog();
    }
}
