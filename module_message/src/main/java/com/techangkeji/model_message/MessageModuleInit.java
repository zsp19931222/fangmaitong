package com.techangkeji.model_message;

import android.app.Application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.goldze.base.base.IModuleInit;

/**
 * description:
 * author:created by Andy on 2019/9/10 0010 10:16
 * email:zsp872126510@gmail.com
 */
public class MessageModuleInit implements IModuleInit {
    public static String currentUserNick = "";

    @Override
    public boolean onInitAhead(Application application) {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(application);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        return false;
    }

    @Override
    public boolean onInitLow(Application application) {
        return false;
    }
}
