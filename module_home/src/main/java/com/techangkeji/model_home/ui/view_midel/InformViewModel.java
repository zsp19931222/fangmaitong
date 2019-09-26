package com.techangkeji.model_home.ui.view_midel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.goldze.base.constant.TipsConstants;

import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.AppReportBody;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtil;

/**
 * description:
 * author:created by Andy on 2019/9/20 15:20
 * email:zsp872126510@gmail.com
 */
public class InformViewModel extends BaseViewModel {
    public ObservableField<String> reason = new ObservableField<>("");
    public ObservableField<String> explain = new ObservableField<>("");
    public ObservableField<String> listingName=new ObservableField<>("");
    public ObservableField<String> listingId=new ObservableField<>("");

    public InformViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand btnCommand = new BindingCommand(() -> {
        if (IsNullUtil.getInstance().isEmpty(reason.get()) || IsNullUtil.getInstance().isEmpty(explain.get())) {
            ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), TipsConstants.PARAMETER_ERROR);
            return;
        }
        AppReportBody appReportBody = new AppReportBody(0, explain.get(), reason.get(), listingName.get(), null, 0, LocalDataHelper.getInstance().getUserInfo().getUserId(), Long.valueOf(listingId.get()), 0);
        IdeaApi.getApiService().appReport(appReportBody)
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
    });
}
