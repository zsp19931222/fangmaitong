package me.goldze.mvvmhabit.utils;

import com.alibaba.android.arouter.launcher.ARouter;

import me.goldze.mvvmhabit.base.AppManager;
import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;

/**
 * description:其他设备登录退出登录
 * author:created by Andy on 2019/9/19 0019 16:25
 * email:zsp872126510@gmail.com
 */
public class OtherDeviceLoginUtil {
    private static final OtherDeviceLoginUtil ourInstance = new OtherDeviceLoginUtil();

    public static OtherDeviceLoginUtil getInstance() {
        return ourInstance;
    }

    private OtherDeviceLoginUtil() {
    }

    public void otherDeviceLogin(){
        LocalDataHelper.getInstance().deleteData();
        AppManager.getAppManager().finishAllActivity();
        ARouter.getInstance().build("/Login/LoginActivity").navigation();
        ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(),"你的账户在其他设备登录，请重新登录");
    }
}
