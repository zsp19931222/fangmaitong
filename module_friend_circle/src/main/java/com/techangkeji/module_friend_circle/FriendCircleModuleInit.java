package com.techangkeji.module_friend_circle;

import android.app.Application;

import com.goldze.base.base.IModuleInit;
import com.kcrason.highperformancefriendscircle.others.DataCenter;

import me.goldze.mvvmhabit.utils.ZLog;


public class FriendCircleModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {
        ZLog.d("FriendCircleModuleInit");
        DataCenter.init();
        return false;
    }

    @Override
    public boolean onInitLow(Application application) {
        return false;
    }
}
