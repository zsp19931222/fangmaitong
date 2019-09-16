package me.goldze.mvvmhabit.http.net;


import java.util.Map;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.net.body.CheckAuthCodeBody;
import me.goldze.mvvmhabit.http.net.body.RegisterBody;
import me.goldze.mvvmhabit.http.net.body.SendCodeBody;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * description:
 * author:created by Andy on 2019/7/9 0009 15:06
 * email:zsp872126510@gmail.com
 */
public interface ApiService<T extends BaseEntity> {
    //20122838  123456 测试账号
    int DEFAULT_TIMEOUT = 8 * 1000;
    String LOGIN_BASE_URL = "http://39.98.33.32:10006/";
    String API = "/api/v2/";

    //注册
    @POST(API + "register")
    Observable<BaseEntity> register(@Body() RegisterBody registerBody);

    //获取验证码
    @POST(API + "sendCode")
    Observable<BaseEntity> sendCode(@Body() SendCodeBody sendCodeBody);

    //验证验证码
    @POST(API + "checkCode")
    Observable<BaseEntity> checkCode(@Body() CheckAuthCodeBody checkAuthCodeBody);

}
