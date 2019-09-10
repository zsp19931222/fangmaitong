package com.techangkeji.model_login.ui.view_model;

import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.alibaba.android.arouter.launcher.ARouter;
import com.goldze.base.router.ARouterPath;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.exceptions.HyphenateException;
import com.techangkeji.hyphenate.chatuidemo.DemoHelper;
import com.techangkeji.hyphenate.chatuidemo.db.DemoDBManager;
import com.techangkeji.model_login.R;
import com.techangkeji.model_login.ui.activity.LoginActivity;
import com.techangkeji.model_login.ui.activity.RegisterActivity;
import com.techangkeji.model_message.MessageModuleInit;

import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 13:48
 * email:zsp872126510@gmail.com
 */
public class LoginViewModel extends BaseViewModel {
    public ObservableField<String> phoneNum = new ObservableField<>("1993");
    public ObservableField<String> pwNum = new ObservableField<>("123");

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
    public BindingCommand intent2MainActivity = new BindingCommand(this::registerHX);


    /**
     * description: 注册环信
     * author: Andy
     * date: 2019/9/10 0010 15:56
     */
    private void registerHX() {
        String username = phoneNum.get();
        String pwd = pwNum.get();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd)) {
            new Thread(() -> {
                try {
                    // call method in SDK
                    EMClient.getInstance().createAccount(username, pwd);
                    // save current user
                    DemoHelper.getInstance().setCurrentUserName(username);
                    ZLog.d("注册成功");
                    loginHX(username, pwd);
                } catch (final HyphenateException e) {
                    int errorCode = e.getErrorCode();
                    ZLog.d(errorCode);
                    if (errorCode == EMError.USER_ALREADY_EXIST) {//已经注册
                        logoutHX(username, pwd);
                    }
                }
            }).start();

        }
    }

    /**
     * description: 登录环信
     * author: Andy
     * date: 2019/9/10 0010 16:09
     */
    public void loginHX(String currentUsername, String currentPassword) {
        if (!EaseCommonUtils.isNetWorkConnected(BaseApplication.getInstance().getBaseContext())) {
            Toast.makeText(BaseApplication.getInstance().getBaseContext(), com.techangkeji.model_message.R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(currentUsername)) {
            Toast.makeText(BaseApplication.getInstance().getBaseContext(), com.techangkeji.model_message.R.string.User_name_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(currentPassword)) {
            Toast.makeText(BaseApplication.getInstance().getBaseContext(), com.techangkeji.model_message.R.string.Password_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        DemoDBManager.getInstance().closeDB();
        DemoHelper.getInstance().setCurrentUserName(currentUsername);
        ZLog.d("EMClient.getInstance().login");
        EMClient.getInstance().login(currentUsername, currentPassword, new EMCallBack() {

            @Override
            public void onSuccess() {
                ZLog.d("login: onSuccess");
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();

                boolean updatenick = EMClient.getInstance().pushManager().updatePushNickname(
                        MessageModuleInit.currentUserNick.trim());
                if (!updatenick) {
                    Log.e("LoginActivity", "update current user nick fail");
                }
                DemoHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();
                ARouter.getInstance().build(ARouterPath.Main.PAGER_MAIN).navigation();
            }

            @Override
            public void onProgress(int progress, String status) {
                ZLog.d("login: onProgress");
            }

            @Override
            public void onError(final int code, final String message) {
                ZLog.d("login: onError: " + code);
                logoutHX(currentUsername,currentPassword);
            }
        });
    }

    /**
    * description:注销环信
    * author: Andy
    * date: 2019/9/10 0010 17:23
    */
    private void logoutHX(String currentUsername, String currentPassword){
        DemoHelper.getInstance().logout(true,new EMCallBack() {

            @Override
            public void onSuccess() {
                loginHX(currentUsername, currentPassword);
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                ZLog.d(message);
            }
        });
    }
}
