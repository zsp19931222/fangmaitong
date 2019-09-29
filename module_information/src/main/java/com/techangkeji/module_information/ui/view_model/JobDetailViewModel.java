package com.techangkeji.module_information.ui.view_model;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.fragment.app.FragmentActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.goldze.base.constant.TipsConstants;
import com.goldze.base.router.ARouterPath;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.observers.DefaultObserver;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtil;

/**
 * description:
 * author:created by Andy on 2019/9/29 22:58
 * email:zsp872126510@gmail.com
 */
public class JobDetailViewModel extends BaseViewModel {
    public int id;
    public ObservableField<String> hrUrl = new ObservableField<>("");
    public ObservableField<String> hrName = new ObservableField<>("");
    public ObservableField<String> hrNum = new ObservableField<>("");
    public ObservableField<String> hrIdent = new ObservableField<>("");
    public ObservableField<Integer> hrAuthSM = new ObservableField<>(View.GONE);
    public ObservableField<Integer> hrAuthZZ = new ObservableField<>(View.GONE);
    public ObservableField<Integer> hrAuthJJR = new ObservableField<>(View.GONE);
    public ObservableField<String> inviteContent = new ObservableField<>("");
    public ObservableField<String> inviteYear = new ObservableField<>("");
    public ObservableField<String> inviteNature = new ObservableField<>("");
    public ObservableField<String> inviteAddress = new ObservableField<>("");

    public ObservableField<String> phone = new ObservableField<>("");
    public Context context;

    public JobDetailViewModel(@NonNull Application application) {
        super(application);
    }

    //跳转到动态页面
    public BindingCommand circleCommand = new BindingCommand(() -> ARouter.getInstance().build(ARouterPath.FriendCircle.CardActivity).withInt("id", id).navigation());

    public BindingCommand phoneCommand = new BindingCommand(() -> {
        callPhone(context, phone.get());
    });

    public BindingCommand sendMessage = new BindingCommand(() -> {
        Intent intent = new Intent(Intent.ACTION_SENDTO);//android.intent.action.SENDTO
        //2). 携带数据(号码/内容)
        intent.setData(Uri.parse("smsto:" + phone.get()));
        //携带额外数据
        intent.putExtra("sms_body", "");
        //3). startActivity(intent)
        context.startActivity(intent);
    });

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(Context context, String phoneNum) {
        new RxPermissions((FragmentActivity) context)
                .request(Manifest.permission.CALL_PHONE)
                .subscribe(new DefaultObserver<Boolean>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            Uri data = Uri.parse("tel:" + phoneNum);
                            intent.setData(data);
                            context.startActivity(intent);
                        } else {
                            ToastUtil.normalToast(context, TipsConstants.GET_PERMISSIONS_FAILED);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.normalToast(context, TipsConstants.GET_PERMISSIONS_FAILED);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
