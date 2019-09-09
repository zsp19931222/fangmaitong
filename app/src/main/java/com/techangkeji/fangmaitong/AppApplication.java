package com.techangkeji.fangmaitong;

import com.goldze.base.config.ModuleLifecycleConfig;

import me.goldze.mvvmhabit.base.BaseApplication;


/**
 * description:
 * author:created by Andy on 2019/9/9 0009 10:32
 * email:zsp872126510@gmail.com
 */
public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化组件(靠前)
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
        //....
        //初始化组件(靠后)
        ModuleLifecycleConfig.getInstance().initModuleLow(this);
    }
}