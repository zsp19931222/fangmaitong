package com.techangkeji.model_login.ui.view_model;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableField;

import com.goldze.base.utils.RandomUtil;
import com.goldze.base.utils.SHA1Util;
import com.techangkeji.model_login.R;
import com.techangkeji.model_login.ui.activity.BindingAccountActivity;
import com.techangkeji.model_login.ui.popupwindow.PerfectionUserInfoPopup;
import com.techangkeji.model_login.ui.util.LoginUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.CheckAuthCodeBody;
import me.goldze.mvvmhabit.http.net.body.LoginBody;
import me.goldze.mvvmhabit.http.net.body.RegisterBody;
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
 * author:created by Andy on 2019/9/9 0009 14:45
 * email:zsp872126510@gmail.com
 */
public class RegisterViewModel extends BaseViewModel {
    public ObservableField<String> pwField = new ObservableField<>("123456");
    public ObservableField<String> cpwField = new ObservableField<>("123456");
    public ObservableField<String> phoneField = new ObservableField<>("13983251013");
    public ObservableField<String> authCodeField = new ObservableField<>("获取验证码");
    public ObservableField<String> authCodeNumField = new ObservableField<>("123456");
    public ObservableField<String> registerBtnTv = new ObservableField<>("注册为经纪人");

    public ObservableField<Integer> showViewBg1 = new ObservableField<>(ContextCompat.getColor(BaseApplication.getInstance().getBaseContext(), R.color.color_f6));
    public ObservableField<Integer> showViewBg2 = new ObservableField<>(ContextCompat.getColor(BaseApplication.getInstance().getBaseContext(), R.color.color_f6));
    public ObservableField<Integer> showViewBg3 = new ObservableField<>(ContextCompat.getColor(BaseApplication.getInstance().getBaseContext(), R.color.color_f6));

    public ObservableField<Integer> JJRBG = new ObservableField<>(ContextCompat.getColor(BaseApplication.getInstance().getBaseContext(), R.color.color_FF8C00));
    public ObservableField<Integer> ZDRBG = new ObservableField<>(ContextCompat.getColor(BaseApplication.getInstance().getBaseContext(), R.color.color_dark_333333));
    public ObservableField<Integer> showJJR = new ObservableField<>(View.VISIBLE);
    public ObservableField<Integer> showZD = new ObservableField<>(View.INVISIBLE);
    public ObservableField<Context> context = new ObservableField<>();

    public ObservableField<Integer> identity = new ObservableField<>(4);


    public RegisterViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description: 点击注册经纪人
     * author: Andy
     * date: 2019/9/17 0017 9:59
     */
    public BindingCommand clickJJRCommand = new BindingCommand(() -> {
        registerBtnTv.set("注册为经纪人");
        JJRBG.set(ContextCompat.getColor(BaseApplication.getInstance().getBaseContext(), R.color.color_FF8C00));
        ZDRBG.set(ContextCompat.getColor(BaseApplication.getInstance().getBaseContext(), R.color.color_dark_333333));
        showJJR.set(View.VISIBLE);
        showZD.set(View.INVISIBLE);
        identity.set(4);
    });

    /**
     * description: 点击注册总代
     * author: Andy
     * date: 2019/9/17 0017 10:00
     */
    public BindingCommand clickZDCommand = new BindingCommand(() -> {
        registerBtnTv.set("注册为总代");
        ZDRBG.set(ContextCompat.getColor(BaseApplication.getInstance().getBaseContext(), R.color.color_FF8C00));
        JJRBG.set(ContextCompat.getColor(BaseApplication.getInstance().getBaseContext(), R.color.color_dark_333333));
        showZD.set(View.VISIBLE);
        showJJR.set(View.INVISIBLE);
        identity.set(1);
    });

    /**
     * description:跳转到绑定已有账号
     * author: Andy
     * date: 2019/9/9 0009 15:34
     */
    public BindingCommand intent2BindingAccountActivity = new BindingCommand(() -> startActivity(BindingAccountActivity.class));


    /**
     * description: 点击注册
     * author: Andy
     * date: 2019/9/16  23:33
     */
    public BindingCommand registerCommand = new BindingCommand(() -> checkCode());

    /**
     * description: 注册
     * author: Andy
     * date: 2019/9/16  22:13
     */

    private void register() {
        LoginBody registerBody = new LoginBody(cpwField.get(), phoneField.get(), 1);
        IdeaApi.getApiService()
                .login(registerBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable1 -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity<RegisterEntity>>(this) {
                    @Override
                    public void onSuccess(SuccessEntity<RegisterEntity> response) {
                        PerfectionUserInfoPopup perfectionUserInfoPopup = new PerfectionUserInfoPopup(context.get());
                        perfectionUserInfoPopup.setOnConfirmListener(() -> {
                            showDialog("正在登录请稍后");
//                            LoginUtil.getInstance().saveUserInfo(response);
                            LoginUtil.getInstance().requestPermissions(context.get(),response);
                        });
                        perfectionUserInfoPopup.showPopupWindow();
                    }
                });
    }

    @Override
    public void onPause() {
        super.onPause();
        dismissDialog();
    }

    /**
     * description: 获取验证码
     * author: Andy
     * date: 2019/9/16  22:23
     */

    public BindingCommand sendCodeCommand = new BindingCommand(this::sendCode);


    private void sendCode() {
        if (authCodeField.get().equals(SEND_AUTH_CODE)) {
            if (IsNullUtil.getInstance().isEmpty(phoneField.get())) {
                ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "请输入手机号");
            } else {
                String timestamp = System.currentTimeMillis() + "";
                String randomStr = RandomUtil.getInstance().generateRandomNumber(13) + "";
                String sign = phoneField.get() + timestamp + randomStr + "K1ko06SMaxbKtVbV";
                SendCodeBody sendCodeBody = new SendCodeBody(phoneField.get(), timestamp, randomStr, SHA1Util.SHA1Digest(sign), 1);
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
     * description: 验证验证码
     * author: Andy
     * date: 2019/9/16  23:02
     */

    private void checkCode() {
        if (IsNullUtil.getInstance().isEmpty(cpwField.get()) || IsNullUtil.getInstance().isEmpty(phoneField.get())) {
            ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "请输入相关信息");
        } else if (cpwField.get().length() < 6) {
            ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "请确保密码输入正确");
        } else if (pwField.get().equals(cpwField.get())) {
            CheckAuthCodeBody checkAuthCodeBody = new CheckAuthCodeBody(phoneField.get(), authCodeNumField.get());
            IdeaApi.getApiService()
                    .checkCode(checkAuthCodeBody)
                    .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                    .compose(RxUtils.schedulersTransformer())
                    .doOnSubscribe(disposable1 -> showDialog())
                    .subscribe(new DefaultObserver<SuccessEntity<SendCodeEntity>>(this) {
                        @Override
                        public void onSuccess(SuccessEntity<SendCodeEntity> response) {
                            register();
                        }
                    });
        } else {
            ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "请确保密码输入正确");
        }
    }

    /**
     * description:验证码倒计时
     * author: Andy
     * date: 2019/9/16  22:46
     */
    private Disposable sendAuthCodeCountDown() {
        //从1开始发射60个数字为：1-60依次输出，延时0s执行，每1s发射一次。
        return Observable.intervalRange(1, 60, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(aLong -> {
                    authCodeField.set((60 - aLong) + "s后重发");
                })
                .doOnComplete(() -> {
                    authCodeField.set(SEND_AUTH_CODE);
                })
                .subscribe();
    }
}
