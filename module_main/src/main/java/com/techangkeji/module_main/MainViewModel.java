package com.techangkeji.module_main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.goldze.base.router.ARouterPath;
import com.goldze.base.utils.BaiduLocationBean;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.LocationBody;
import me.goldze.mvvmhabit.http.net.entity.AreaListEntity;
import me.goldze.mvvmhabit.http.net.entity.LocationEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.litepal.util.SaveAreaListUtil;
import me.goldze.mvvmhabit.utils.RxUtils;

/**
 * description:
 * author:created by Andy on 2019/9/25 20:50
 * email:zsp872126510@gmail.com
 */
public class MainViewModel extends BaseViewModel {
    public ObservableField<String> area = new ObservableField<>("");
    public ObservableField<String> city = new ObservableField<>("");
    public MainViewModel(@NonNull Application application) {
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
                .subscribe(new DefaultObserver<SuccessEntity<LocationEntity>>(this) {
                    @Override
                    public void onSuccess(SuccessEntity<LocationEntity> response) {
                        SPUtils.getInstance().put("areaId", response.getContent().getAreaId() + "");
                        SPUtils.getInstance().put("latitude", response.getContent().getLatitude());
                        SPUtils.getInstance().put("longitude", response.getContent().getLongitude());
                        RxBus.getDefault().post("获取区域ID成功");
                    }
                });
    }

    /**
     * description: 获取地区
     * author: Andy
     * date: 2019/9/22  14:55
     */
    public void getAreaList() {
        IdeaApi.getApiService()
                .listAllArea()
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<AreaListEntity>(this) {
                    @Override
                    public void onSuccess(AreaListEntity response) {
                        SaveAreaListUtil.getInstance().save(response);
                    }
                });
    }

    /**
    * description: 跳转搜索
    * author: Andy
    * date: 2019/9/29 0029 16:13
    */
    public BindingCommand searchCommand=new BindingCommand(() -> ARouter.getInstance().build(ARouterPath.Public.SearchActivity).navigation());

}
