package com.techangkeji.model_login.ui.view_model;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.goldze.base.constant.TipsConstants;
import com.techangkeji.model_login.ui.activity.BindingAccountActivity;
import com.techangkeji.model_login.ui.activity.RegisterActivity;
import com.techangkeji.model_login.ui.activity.ResetPasswordActivity;
import com.techangkeji.model_login.ui.util.LoginUtil;

import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.BindingThirdBody;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.login.RegisterEntity;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtil;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 15:24
 * email:zsp872126510@gmail.com
 */
public class BindingAccountViewModel extends BaseViewModel {
    public ObservableField<String> phone = new ObservableField<>("");
    public ObservableField<String> pw = new ObservableField<>("");

    public ObservableField<Long> id = new ObservableField<>();
    public ObservableField<Integer> type = new ObservableField<>();

    public BindingAccountViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description:绑定账号
     * author: Andy
     * date: 2019/9/18 0018 16:53
     */
    public BindingCommand bindPhoneCommand = new BindingCommand(() -> {
        if (IsNullUtil.getInstance().isEmpty(phone.get()) || IsNullUtil.getInstance().isEmpty(pw.get())||pw.get().length()<6) {
            ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), TipsConstants.PARAMETER_ERROR);
            return;
        }
        BindingThirdBody bindingThirdBody = new BindingThirdBody(id.get(), type.get());
        IdeaApi.getApiService()
                .bindingThird(bindingThirdBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity<RegisterEntity>>(this) {
                    @Override
                    public void onSuccess(SuccessEntity<RegisterEntity> response) {
                        showDialog("正在登录请稍后");
                        LoginUtil.getInstance().saveUserInfo(response);
                    }
                });

    });

    @Override
    public void onPause() {
        super.onPause();
        dismissDialog();
    }
}
