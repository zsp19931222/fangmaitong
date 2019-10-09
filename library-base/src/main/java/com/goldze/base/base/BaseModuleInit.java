package com.goldze.base.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.stetho.Stetho;
import com.goldze.base.R;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.tencent.bugly.Bugly;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import me.goldze.mvvmhabit.BuildConfig;
import me.goldze.mvvmhabit.utils.Utils;
import me.goldze.mvvmhabit.utils.ZLog;


/**
 * Created by Andy on 2018/6/21 0021.
 * 基础库自身初始化操作
 */

public class BaseModuleInit implements IModuleInit {
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((Context context, RefreshLayout layout) -> {
            layout.setPrimaryColorsId(R.color.color_0A82E6, android.R.color.white);//全局设置主题颜色
            return new WaterDropHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }
    @Override
    public boolean onInitAhead(Application application) {
        //开启打印日志
        //dex分包
//        MultiDex.install(application);
        //常用工具类
        Utils.init(application);
        com.blankj.utilcode.util.Utils.init(application);
        //开启打印日志
        ZLog.init(true);
        //初始化阿里路由框架
        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application); // 尽可能早，推荐在Application中初始化
        ZLog.e("基础层初始化 -- onInitAhead");
        initUM(application);
        Stetho.initializeWithDefaults(application);
        Bugly.init(application, BuildConfig.BUGLY_APPID, BuildConfig.DEBUG);

        return false;
    }

    @Override
    public boolean onInitLow(Application application) {
        ZLog.e("基础层初始化 -- onInitLow");
        return false;
    }
    /**
     * description: 注册友盟
     * author: Andy
     * date: 2019/8/2 0002 11:27
     */
    private void initUM(Application application) {
        // 在此处调用基础组件包提供的初始化函数 相应信息可在应用管理 -> 应用信息 中找到 http://message.umeng.com/list/apps
        // 参数一：当前上下文context；
        // 参数二：应用申请的Appkey（需替换）；
        // 参数三：渠道名称；
        // 参数四：设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机；
        // 参数五：Push推送业务的secret 填充Umeng Message Secret对应信息（需替换）
        UMConfigure.init(application, BuildConfig.UMENG_APP_KEY, "Umeng", UMConfigure.DEVICE_TYPE_PHONE, null);
        PlatformConfig.setWeixin(BuildConfig.WECHAT_APP_ID, BuildConfig.WECHAT_APP_SECRET);
        PlatformConfig.setSinaWeibo(BuildConfig.WEB_APP_KEY, BuildConfig.WEB_APP_SECRET, "http://sns.whalecloud.com");
        PlatformConfig.setQQZone(BuildConfig.QQ_APP_ID, BuildConfig.QQ_APP_KEY);
    }
}
