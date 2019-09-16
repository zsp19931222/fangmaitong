package com.techangkeji.model_login.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.goldze.base.utils.RandomUtil;
import com.goldze.base.utils.SHA1Util;
import com.techangkeji.model_login.R;
import com.techangkeji.model_login.ui.activity.BindingAccountActivity;

import java.util.HashMap;
import java.util.Map;
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
import me.goldze.mvvmhabit.http.net.body.RegisterBody;
import me.goldze.mvvmhabit.http.net.body.SendCodeBody;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtil;

import static com.goldze.base.constant.TipsConstants.SEND_AUTH_CODE;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 14:45
 * email:zsp872126510@gmail.com
 */
public class RegisterViewModel extends BaseViewModel {
    public RegisterViewModel(@NonNull Application application) {
        super(application);
    }

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
    public BindingCommand registerCommand=new BindingCommand(() ->checkCode() );

    /**
     * description: 注册
     * author: Andy
     * date: 2019/9/16  22:13
     */
    public ObservableField<String> pwField = new ObservableField<>("");
    public ObservableField<String> cpwField = new ObservableField<>("");

    public void register() {
        if (cpwField.get().equals("")) {
            ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "请输入密码");
        }
        if (cpwField.get().length() < 6) {
            ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "请确保密码输入正确");
        }
        if (pwField.get().equals(cpwField.get())) {
            RegisterBody registerBody = new RegisterBody(cpwField.get(), phoneField.get(), 4);
            IdeaApi.getApiService()
                    .register(registerBody)
                    .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                    .compose(RxUtils.schedulersTransformer())
                    .doOnSubscribe(disposable1 -> showDialog())
                    .subscribe(new DefaultObserver(this) {
                        @Override
                        public void onSuccess(BaseEntity response) {

                        }
                    });
        } else {
            ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "请确保密码输入正确");
        }
    }


    /**
     * description: 获取验证码
     * author: Andy
     * date: 2019/9/16  22:23
     */
    public ObservableField<String> phoneField = new ObservableField<>("");
    public BindingCommand sendCodeCommand = new BindingCommand(this::sendCode);
    public ObservableField<String> authCodeField = new ObservableField<>("获取验证码");


    public void sendCode() {
        String timestamp = System.currentTimeMillis() + "";
        String randomStr = RandomUtil.getInstance().generateRandomNumber(13) + "";
        String sign = phoneField.get() + timestamp + randomStr + "K1ko06SMaxbKtVbV";
        SendCodeBody sendCodeBody = new SendCodeBody(phoneField.get(), timestamp, randomStr, SHA1Util.SHA1Digest(sign),1);
        IdeaApi.getApiService()
                .sendCode(sendCodeBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable1 -> showDialog())
                .subscribe(new DefaultObserver(this) {
                    @Override
                    public void onSuccess(BaseEntity response) {
                        sendAuthCodeCountDown();
                    }
                });
    }

    /**
     * description: 验证验证码
     * author: Andy
     * date: 2019/9/16  23:02
     */
    public ObservableField<String> authCodeNumField = new ObservableField<>("");

    public void checkCode() {
        CheckAuthCodeBody checkAuthCodeBody = new CheckAuthCodeBody(phoneField.get(), authCodeNumField.get());
        IdeaApi.getApiService()
                .checkCode(checkAuthCodeBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable1 -> showDialog())
                .subscribe(new DefaultObserver(this) {
                    @Override
                    public void onSuccess(BaseEntity response) {
                        register();
                    }
                });
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
