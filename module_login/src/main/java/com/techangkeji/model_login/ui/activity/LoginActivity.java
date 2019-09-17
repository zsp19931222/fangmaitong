package com.techangkeji.model_login.ui.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.exceptions.HyphenateException;
import com.techangkeji.hyphenate.chatuidemo.DemoHelper;
import com.techangkeji.hyphenate.chatuidemo.db.DemoDBManager;
import com.techangkeji.hyphenate.chatuidemo.ui.MainActivity;
import com.techangkeji.model_login.BR;
import com.techangkeji.model_login.R;
import com.techangkeji.model_login.databinding.ActivityLoginBinding;
import com.techangkeji.model_login.ui.view_model.LoginViewModel;
import com.techangkeji.model_message.MessageModuleInit;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 14:14
 * email:zsp872126510@gmail.com
 */
@Route(path = ARouterPath.Login.LoginActivity)
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.context.set(this);
    }
}
