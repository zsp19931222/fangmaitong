package com.techangkeji.model_home.ui.view_midel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.goldze.base.router.ARouterPath;
import com.goldze.base.utils.BaiduLocationBean;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.AuthRealNameBody;
import me.goldze.mvvmhabit.http.net.body.LocationBody;
import me.goldze.mvvmhabit.http.net.entity.LocationEntity;
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
    public ObservableField<String> area = new ObservableField<>("");
    public ObservableField<String> city = new ObservableField<>("");

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand areaCommand = new BindingCommand(() -> {
        ARouter.getInstance().build(ARouterPath.Public.AreaSelectActivity).withString("city", city.get()).navigation();
    });

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
                .subscribe(new DefaultObserver<SuccessEntity<LocationEntity>>(this) {
                    @Override
                    public void onSuccess(SuccessEntity<LocationEntity> response) {
                        SPUtils.getInstance().put("areaId", response.getContent().getAreaId() + "");
                        SPUtils.getInstance().put("latitude", response.getContent().getLatitude());
                        SPUtils.getInstance().put("longitude", response.getContent().getLongitude());
                    }
                });
    }
}
