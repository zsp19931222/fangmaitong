package me.goldze.mvvmhabit.litepal.util;


import org.litepal.LitePal;

import me.goldze.mvvmhabit.http.net.entity.login.RegisterEntity;
import me.goldze.mvvmhabit.litepal.UserInfoLitePal;

/**
 * description:获取缓存数据
 * author:created by Andy on 2019/9/5 0005 16:43
 * email:zsp872126510@gmail.com
 */
public class LocalDataHelper {
    private static final LocalDataHelper ourInstance = new LocalDataHelper();

    public static LocalDataHelper getInstance() {
        return ourInstance;
    }

    private LocalDataHelper() {
    }

    /**
     * description:获取用户信息
     * author: Andy
     * date: 2019/9/5 0005 16:44
     */
    public UserInfoLitePal getUserInfo() {
        return LitePal.findFirst(UserInfoLitePal.class);
    }

    public void deleteData(){
        LitePal.deleteAll(UserInfoLitePal.class);
    }
}
