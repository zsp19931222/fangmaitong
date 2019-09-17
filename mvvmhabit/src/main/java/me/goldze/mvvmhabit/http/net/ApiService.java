package me.goldze.mvvmhabit.http.net;


import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.net.body.CheckAuthCodeBody;
import me.goldze.mvvmhabit.http.net.body.LoginBody;
import me.goldze.mvvmhabit.http.net.body.LogoutBody;
import me.goldze.mvvmhabit.http.net.body.RegisterBody;
import me.goldze.mvvmhabit.http.net.body.ReleaseMovingBody;
import me.goldze.mvvmhabit.http.net.body.SendCodeBody;
import me.goldze.mvvmhabit.http.net.body.UntiedThirdBody;
import me.goldze.mvvmhabit.http.net.body.UpdateBody;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.login.RegisterEntity;
import me.goldze.mvvmhabit.http.net.entity.login.SendCodeEntity;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
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
    @Headers({"url_name:login"})
    Observable<SuccessEntity<RegisterEntity>> register(@Body() RegisterBody registerBody);

    //登录
    @POST(API + "login")
    @Headers({"url_name:login"})
    Observable<SuccessEntity<RegisterEntity>> login(@Body() LoginBody registerBody);

    //获取验证码
    @POST(API + "sendCode")
    @Headers({"url_name:login"})
    Observable<SuccessEntity<SendCodeEntity>> sendCode(@Body() SendCodeBody sendCodeBody);

    //验证验证码
    @POST(API + "checkCode")
    @Headers({"url_name:login"})
    Observable<SuccessEntity<SendCodeEntity>> checkCode(@Body() CheckAuthCodeBody checkAuthCodeBody);

    //修改信息
    @POST(API + "auth/update")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> update(@Body() UpdateBody updateBody);

    //图片上传
    @Multipart
    @POST(API + "auth/uploadpic")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> uploadpic(@Part List<MultipartBody.Part> partList);

    //退出登录
    @PUT(API + "auth/loginOut/{time}")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> loginOut(@Path("time") String time);

    //解绑
    @PUT(API + "auth/untiedThird/{type}")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> untiedThird(@Path("type") String time, @Body() UntiedThirdBody untiedThirdBody);


    //发布动态
    @POST(API + "auth/moving")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> releaseMoving(@Body() ReleaseMovingBody releaseMovingBody);


    //删除动态
    @DELETE(API + "auth/moving/{id}")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> delMoving(@Path("id") String id, @Body() UntiedThirdBody untiedThirdBody);


    //获取某个动态详情
    @GET(API + "auth/moving/{id}")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> getMoving(@Path("id") String id, @Body() UntiedThirdBody untiedThirdBody);


    //获取自己动态列表
    @POST(API + "auth/moving/list")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> myMovingList(@Body() UntiedThirdBody untiedThirdBody);


    //获取朋友圈动态列表
    @GET(API + "auth/moving/firend/list/{page}/{max} ")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> friendMovingList(@Path("page") String page, @Path("max") String max, @Body() UntiedThirdBody untiedThirdBody);


}
