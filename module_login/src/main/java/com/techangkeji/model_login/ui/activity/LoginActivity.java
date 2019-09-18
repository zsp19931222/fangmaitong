package com.techangkeji.model_login.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.constant.TipsConstants;
import com.goldze.base.router.ARouterPath;
import com.goldze.base.utils.OtherLoginUtil;
import com.techangkeji.model_login.BR;
import com.techangkeji.model_login.R;
import com.techangkeji.model_login.databinding.ActivityLoginBinding;
import com.techangkeji.model_login.ui.view_model.LoginViewModel;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.ToastUtil;

import static com.goldze.base.utils.OtherLoginUtil.LOGIN_SUCCESS;

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
        RxSubscriptions.add(RxBus.getDefault().toObservable(OtherLoginUtil.OtherLoginBean.class).subscribe(otherLoginBean -> {
            if (LOGIN_SUCCESS.equals(otherLoginBean.getCode())) {
                viewModel.bindThird(otherLoginBean);
            } else {
                if (SHARE_MEDIA.QQ.equals(otherLoginBean.getType())) {
                    ToastUtil.normalToast(this,TipsConstants.OTHER_BIND_FAIL_QQ);
                } else {
                    ToastUtil.normalToast(this,TipsConstants.OTHER_BIND_FAIL_WE_CHAT);
                }
            }
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
