package com.techangkeji.model_login.ui.view_model;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.goldze.base.constant.TipsConstants;
import com.goldze.base.utils.PhoneUtil;
import com.goldze.base.utils.RandomUtil;
import com.goldze.base.utils.SHA1Util;
import com.techangkeji.model_login.ui.activity.BindingAccountActivity;
import com.techangkeji.model_login.ui.activity.RegisterActivity;
import com.techangkeji.model_login.ui.activity.ResetPasswordActivity;
import com.techangkeji.model_login.ui.util.LoginUtil;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import me.goldze.mvvmhabit.base.AppManager;
import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.BindingThirdBody;
import me.goldze.mvvmhabit.http.net.body.CheckAuthCodeBody;
import me.goldze.mvvmhabit.http.net.body.SendCodeBody;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.login.RegisterEntity;
import me.goldze.mvvmhabit.http.net.entity.login.SendCodeEntity;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtil;

import static com.goldze.base.constant.TipsConstants.SEND_AUTH_CODE;

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

    public ObservableField<Context> context=new ObservableField<>();

    public ObservableField<String> authCodeField = new ObservableField<>("获取验证码");
    public ObservableField<String> authCodeNumField = new ObservableField<>("");

    public BindingAccountViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description:绑定账号
     * author: Andy
     * date: 2019/9/18 0018 16:53
     */
    public BindingCommand bindPhoneCommand = new BindingCommand(this::checkCode);
    /**
     * description: 获取验证码
     * author: Andy
     * date: 2019/9/18 0018 9:44
     */
    public BindingCommand sendCodeCommand = new BindingCommand(this::sendCode);

    private void sendCode() {
        if (Objects.equals(authCodeField.get(), SEND_AUTH_CODE)) {
            if (!PhoneUtil.getInstance().checkPhone(phone.get())) {
                ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "请输入正确的手机号");
            } else {
                String timestamp = System.currentTimeMillis() + "";
                String randomStr = RandomUtil.getInstance().generateRandomNumber(13) + "";
                String sign = phone.get() + timestamp + randomStr + "K1ko06SMaxbKtVbV";
                SendCodeBody sendCodeBody = new SendCodeBody(phone.get(), timestamp, randomStr, SHA1Util.SHA1Digest(sign), 1);
                IdeaApi.getApiService()
                        .sendCode(sendCodeBody)
                        .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                        .compose(RxUtils.schedulersTransformer())
                        .doOnSubscribe(disposable1 -> showDialog())
                        .subscribe(new DefaultObserver<SuccessEntity>(this) {

                            @Override
                            public void onSuccess(SuccessEntity entity) {
                                sendAuthCodeCountDown();
                            }
                        });
            }
        }
    }

    /**
     * description:验证码倒计时
     * author: Andy
     * date: 2019/9/16  22:46
     */
    private void sendAuthCodeCountDown() {
        //从1开始发射60个数字为：1-60依次输出，延时0s执行，每1s发射一次。
        Observable.intervalRange(1, 60, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(aLong -> authCodeField.set((60 - aLong) + "s后重发"))
                .doOnComplete(() -> authCodeField.set(SEND_AUTH_CODE))
                .subscribe();
    }

    /**
     * description: 验证验证码
     * author: Andy
     * date: 2019/9/16  23:02
     */

    private void checkCode() {
        if (IsNullUtil.getInstance().isEmpty(phone.get()) || IsNullUtil.getInstance().isEmpty(authCodeNumField.get()) || IsNullUtil.getInstance().isEmpty(pw.get()) || pw.get().length() < 6) {
            ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), TipsConstants.PARAMETER_ERROR);
            return;
        }
        CheckAuthCodeBody checkAuthCodeBody = new CheckAuthCodeBody(phone.get(), authCodeNumField.get());
        IdeaApi.getApiService()
                .checkCode(checkAuthCodeBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable1 -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity<SendCodeEntity>>(this) {
                    @Override
                    public void onSuccess(SuccessEntity<SendCodeEntity> response) {
                        login();
                    }
                });
    }

    /**
     * description: 登录
     * author: Andy
     * date: 2019/9/18  20:18
     */
    private void login() {
        BindingThirdBody bindingThirdBody = new BindingThirdBody(id.get(), type.get(), phone.get(), pw.get());
        IdeaApi.getApiService()
                .bindingThird(bindingThirdBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity<RegisterEntity>>(this) {
                    @Override
                    public void onSuccess(SuccessEntity<RegisterEntity> response) {
                        showDialog("正在登录请稍后");
//                        LoginUtil.getInstance().saveUserInfo(response);
                        LoginUtil.getInstance().requestPermissions(context.get(),response);
                    }
                });
    }

    @Override
    public void onPause() {
        super.onPause();
        dismissDialog();
    }
}
