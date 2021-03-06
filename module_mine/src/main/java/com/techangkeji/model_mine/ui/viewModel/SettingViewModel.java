package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.fragment.app.FragmentManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.goldze.base.router.ARouterPath;
import com.goldze.base.utils.glide.GlideCacheUtil;
import com.techangkeji.model_mine.ui.activity.AboutActivity;
import com.techangkeji.model_mine.ui.activity.ChangePasswordActivity;
import com.techangkeji.model_mine.ui.activity.ChangePhoneActivity;
import com.techangkeji.model_mine.ui.activity.OtherBindingAccountActivity;

import java.io.File;

import me.goldze.mvvmhabit.base.AppManager;
import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.login.RegisterEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.RxUtils;
import top.limuyang2.customldialog.IOSMsgDialog;

public class SettingViewModel extends BaseViewModel {
    public ObservableField<String> sizeText = new ObservableField<>("");

    public SettingViewModel(@NonNull Application application) {
        super(application);
        File cacheDir = Glide.getPhotoCacheDir(BaseApplication.getInstance().getBaseContext());
        long size = GlideCacheUtil.getDirSize(cacheDir);
        sizeText.set("清除缓存" + GlideCacheUtil.byteConversionGBMBKB(size));
    }

    public ObservableField<FragmentManager> fragmentManagerObservableField = new ObservableField<>();

    public BindingCommand clearCacheCommand = new BindingCommand(() ->
            IOSMsgDialog.Companion.init(fragmentManagerObservableField.get())
                    .setTitle("清除缓存")
                    .setMessage("确定清除缓存数据？")
                    .setNegativeButton("取消", v -> dismissDialog())
                    .setPositiveButton("确定", v -> {
                        GlideCacheUtil.clearCache(BaseApplication.getInstance().getBaseContext());
                        sizeText.set("清除缓存" + GlideCacheUtil.byteConversionGBMBKB(0));
                    })
                    .show());
    public BindingCommand changePhoneCommand = new BindingCommand(() -> startActivity(ChangePhoneActivity.class));
    public BindingCommand changePasswordCommand = new BindingCommand(() -> startActivity(ChangePasswordActivity.class));
    public BindingCommand otherBindingCommand = new BindingCommand(() -> startActivity(OtherBindingAccountActivity.class));
    public BindingCommand aboutCommand = new BindingCommand(() -> startActivity(AboutActivity.class));

    /**
     * description: 退出登录
     * author: Andy
     * date: 2019/9/17 0017 14:06
     */
    public BindingCommand loginoutCommand = new BindingCommand(() ->
            IOSMsgDialog.Companion.init(fragmentManagerObservableField.get())
                    .setTitle("退出登录")
                    .setMessage("确定退出登录？")
                    .setNegativeButton("取消", v -> dismissDialog())
                    .setPositiveButton("确定", v -> {
                        long loginTime = SPUtils.getInstance().getLong("loginTime");

                        IdeaApi.getApiService()
                                .loginOut((System.currentTimeMillis() - loginTime) + "")
                                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                                .compose(RxUtils.schedulersTransformer())
                                .doOnSubscribe(disposable1 -> showDialog())
                                .subscribe(new DefaultObserver<SuccessEntity<RegisterEntity>>(this) {
                                    @Override
                                    public void onSuccess(SuccessEntity<RegisterEntity> response) {
                                        LocalDataHelper.getInstance().deleteData();
                                        AppManager.getAppManager().finishAllActivity();
                                        ARouter.getInstance().build(ARouterPath.Login.LoginActivity).navigation();
                                    }
                                });
                    })
                    .show());
}
