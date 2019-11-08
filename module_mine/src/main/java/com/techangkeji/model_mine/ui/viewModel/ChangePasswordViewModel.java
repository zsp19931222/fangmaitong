package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableField;

import com.goldze.base.utils.PhoneUtil;
import com.goldze.base.utils.RandomUtil;
import com.goldze.base.utils.SHA1Util;
import com.techangkeji.model_mine.R;

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
import me.goldze.mvvmhabit.http.net.entity.login.SendCodeEntity;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtil;

import static com.goldze.base.constant.TipsConstants.SEND_AUTH_CODE;

public class ChangePasswordViewModel extends BaseViewModel {
    public ObservableField<Integer> showPW = new ObservableField<>(View.VISIBLE);
    public ObservableField<Integer> showAuthCode = new ObservableField<>(View.GONE);
    public ObservableField<Boolean> selectPW = new ObservableField<>(true);
    public ObservableField<Boolean> selectAuthCode = new ObservableField<>(false);
    public ObservableField<String> authCodeField = new ObservableField<>(SEND_AUTH_CODE);


    public ObservableField<String> oldPW = new ObservableField<>("");
    public ObservableField<String> PW = new ObservableField<>("");
    public ObservableField<String> cPW = new ObservableField<>("");
    public ObservableField<Integer> showViewBg1 = new ObservableField<>(ContextCompat.getColor(BaseApplication.getInstance().getBaseContext(), R.color.color_f6));
    public ObservableField<Integer> showViewBg2 = new ObservableField<>(ContextCompat.getColor(BaseApplication.getInstance().getBaseContext(), R.color.color_f6));
    public ObservableField<Integer> showViewBg3 = new ObservableField<>(ContextCompat.getColor(BaseApplication.getInstance().getBaseContext(), R.color.color_f6));

    public ObservableField<String> phone = new ObservableField<>("");
    public ObservableField<String> code = new ObservableField<>("");
    public ObservableField<String> PW1 = new ObservableField<>("");
    public ObservableField<String> cPW1 = new ObservableField<>("");
    public ObservableField<Integer> showViewBg11 = new ObservableField<>(ContextCompat.getColor(BaseApplication.getInstance().getBaseContext(), R.color.color_f6));
    public ObservableField<Integer> showViewBg21 = new ObservableField<>(ContextCompat.getColor(BaseApplication.getInstance().getBaseContext(), R.color.color_f6));
    public ObservableField<Integer> showViewBg31 = new ObservableField<>(ContextCompat.getColor(BaseApplication.getInstance().getBaseContext(), R.color.color_f6));

    public ChangePasswordViewModel(@NonNull Application application) {
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
            if (Objects.requireNonNull(cPW.get()).length()<6||!Objects.equals(cPW.get(), PW.get())){
                ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "请确保信息输入正确");
                return;
            }
            updatePasswordBody = new UpdatePasswordBody(null, oldPW.get(), cPW.get(), null, 1);
        } else {//用手机号修改
            if (Objects.requireNonNull(cPW1.get()).length()<6||!Objects.equals(cPW1.get(), PW1.get())){
                ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "请确保信息输入正确");
                return;
            }
            updatePasswordBody = new UpdatePasswordBody(phone.get(), null, cPW1.get(), code.get(), 2);
        }
        IdeaApi.getApiService()
                .updatePassword(updatePasswordBody)
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
}
