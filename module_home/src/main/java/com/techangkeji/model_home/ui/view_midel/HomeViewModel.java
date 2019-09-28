package com.techangkeji.model_home.ui.view_midel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.goldze.base.router.ARouterPath;
import com.goldze.base.utils.BaiduLocationBean;

import java.util.HashMap;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.AuthRealNameBody;
import me.goldze.mvvmhabit.http.net.body.LocationBody;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
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

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description: 推荐房源
     * author: Andy
     * date: 2019/9/28  14:43
     */
    public void recommendBuildHome() {
        Map<String, Object> map = new HashMap<>();
        map.put("areaId", SPUtils.getInstance().getString("areaId"));
        IdeaApi.getApiService().recommendBuildHome(map)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver() {
                    @Override
                    public void onSuccess(BaseEntity response) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }

    /**
     * description: 推荐资讯
     * author: Andy
     * date: 2019/9/28  14:44
     */

    public void recommendNewsHome() {
        Map<String, Object> map = new HashMap<>();
        map.put("areaId", SPUtils.getInstance().getString("areaId"));
        IdeaApi.getApiService().recommendNewsHome(map)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver() {
                    @Override
                    public void onSuccess(BaseEntity response) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }
}
