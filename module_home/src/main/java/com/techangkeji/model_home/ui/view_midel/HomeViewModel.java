package com.techangkeji.model_home.ui.view_midel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.goldze.base.utils.BaiduLocationBean;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.AuthRealNameBody;
import me.goldze.mvvmhabit.http.net.body.LocationBody;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtil;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 17:01
 * email:zsp872126510@gmail.com
 */
public class HomeViewModel extends BaseViewModel {
    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description: 发送位置信息给后台
     * author: Andy
     * date: 2019/9/20  21:53
     */
    public void sendLocation(BaiduLocationBean baiduLocationBean) {
        LocationBody locationBody = new LocationBody(
                baiduLocationBean.getProvince(),
                baiduLocationBean.getCity(),
                baiduLocationBean.getDistrict(),
                baiduLocationBean.getProvince() + baiduLocationBean.getCity() + baiduLocationBean.getDistrict(),
                baiduLocationBean.getLongitude() + "",
                baiduLocationBean.getLatitude() + "");
        IdeaApi.getApiService()
                .location(locationBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable1 -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity>(this) {
                    @Override
                    public void onSuccess(SuccessEntity response) {
                    }
                });
    }
}
