package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.goldze.base.utils.RandomUtil;
import com.goldze.base.utils.SHA1Util;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.SendCodeBody;
import me.goldze.mvvmhabit.http.net.body.UpdatePasswordBody;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtil;

import static com.goldze.base.constant.TipsConstants.SEND_AUTH_CODE;

public class ChangePhoneViewModel extends BaseViewModel {
    public ObservableField<Integer> showPW = new ObservableField<>(View.VISIBLE);
    public ObservableField<Integer> showAuthCode = new ObservableField<>(View.GONE);
    public ObservableField<Boolean> selectPW = new ObservableField<>(true);
    public ObservableField<Boolean> selectAuthCode = new ObservableField<>(false);
    public ObservableField<String> authCodeField = new ObservableField<>(SEND_AUTH_CODE);
    public ObservableField<String> newPhone = new ObservableField<>("");
    public ObservableField<String> newPhone1 = new ObservableField<>("");
    public ObservableField<String> oldPhone = new ObservableField<>("");
    public ObservableField<String> code = new ObservableField<>("");
    public ObservableField<String> oldPW = new ObservableField<>("");

    public ChangePhoneViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand pwCommand = new BindingCommand(() -> {
        showPW.set(View.VISIBLE);
        selectPW.set(true);
        showAuthCode.set(View.GONE);
        selectAuthCode.set(false);
    });

    public BindingCommand authCodeCommand = new BindingCommand(() -> {
        showPW.set(View.GONE);
        selectPW.set(false);
        showAuthCode.set(View.VISIBLE);
        selectAuthCode.set(true);
    });


    public BindingCommand btnCommand = new BindingCommand(this::changePW);

    /**
     * description:
     * author: Andy
     * date: 2019/9/18 0018 9:27
     */
    private void changePW() {
        UpdatePasswordBody updatePasswordBody;
        if (selectPW.get()) {//用密码修改
            if (Objects.requireNonNull(oldPW.get()).length() < 6 || IsNullUtil.getInstance().isEmpty(newPhone.get())) {
                ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "请确保信息输入正确");
                return;
            }
            updatePasswordBody = new UpdatePasswordBody(newPhone.get(), oldPW.get(), null, null, 1);
        } else {//用手机号修改
            if (IsNullUtil.getInstance().isEmpty(newPhone1.get()) || IsNullUtil.getInstance().isEmpty(code.get()) || IsNullUtil.getInstance().isEmpty(oldPhone.get())) {
                ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "请确保信息输入正确");
                return;
            }
            updatePasswordBody = new UpdatePasswordBody(newPhone1.get(), null, null, code.get(), 2);
        }
        IdeaApi.getApiService()
                .updatePhone(updatePasswordBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity>(this) {
                    @Override
                    public void onSuccess(SuccessEntity response) {
                        ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), response.getMsg());
                        finish();
                    }
                });
    }

    /**
     * description: 获取验证码
     * author: Andy
     * date: 2019/9/18 0018 9:44
     */
    public BindingCommand sendCodeCommand = new BindingCommand(this::sendCode);

    private void sendCode() {
        if (Objects.equals(authCodeField.get(), SEND_AUTH_CODE)) {
            if (IsNullUtil.getInstance().isEmpty(oldPhone.get())) {
                ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "请输入手机号");
            } else {
                String timestamp = System.currentTimeMillis() + "";
                String randomStr = RandomUtil.getInstance().generateRandomNumber(13) + "";
                String sign = oldPhone.get() + timestamp + randomStr + "K1ko06SMaxbKtVbV";
                SendCodeBody sendCodeBody = new SendCodeBody(oldPhone.get(), timestamp, randomStr, SHA1Util.SHA1Digest(sign), 1);
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
}
