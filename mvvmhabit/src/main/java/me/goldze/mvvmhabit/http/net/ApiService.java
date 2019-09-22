package me.goldze.mvvmhabit.http.net;


import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.net.body.AddFriendBody;
import me.goldze.mvvmhabit.http.net.body.AppReportBody;
import me.goldze.mvvmhabit.http.net.body.AuthBrokerBody;
import me.goldze.mvvmhabit.http.net.body.AuthQualificationBody;
import me.goldze.mvvmhabit.http.net.body.AuthRealNameBody;
import me.goldze.mvvmhabit.http.net.body.BindingThirdBody;
import me.goldze.mvvmhabit.http.net.body.CheckAuthCodeBody;
import me.goldze.mvvmhabit.http.net.body.CommentBody;
import me.goldze.mvvmhabit.http.net.body.LocationBody;
import me.goldze.mvvmhabit.http.net.body.LoginBody;
import me.goldze.mvvmhabit.http.net.body.MyMovingListBody;
import me.goldze.mvvmhabit.http.net.body.ReleaseMovingBody;
import me.goldze.mvvmhabit.http.net.body.SendCodeBody;
import me.goldze.mvvmhabit.http.net.body.UntiedThirdBody;
import me.goldze.mvvmhabit.http.net.body.UpdateBody;
import me.goldze.mvvmhabit.http.net.body.UpdatePasswordBody;
import me.goldze.mvvmhabit.http.net.body.VoteBody;
import me.goldze.mvvmhabit.http.net.entity.AreaListEntity;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.http.net.entity.BuildingListEntity;
import me.goldze.mvvmhabit.http.net.entity.FeaturedLabelEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.friend_circle.CommentBean;
import me.goldze.mvvmhabit.http.net.entity.friend_circle.MyStateEntity;
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
    int DEFAULT_TIMEOUT = 20 * 1000;
    String LOGIN_BASE_URL = "http://39.98.33.32:10006/";
    String IMAGE_BASE_URL = "http://39.98.33.32:10006/";
    String IMAGE_BASE_URL1 = "http://39.98.33.32:10002/";
    String API = "/api/v2/";

//    //注册
//    @POST(API + "register")
//    @Headers({"url_name:login"})
//    Observable<SuccessEntity<RegisterEntity>> register(@Body() RegisterBody registerBody);

    //登录
    @POST(API + "login")
    @Headers({"url_name:login"})
    Observable<SuccessEntity<RegisterEntity>> login(@Body() LoginBody registerBody);

    //获取验证码
    @POST(API + "sendCode")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> sendCode(@Body() SendCodeBody sendCodeBody);

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
    @Headers({"url_name:imageUpload"})
    Observable<SuccessEntity<String>> uploadpic(@Part List<MultipartBody.Part> partList);

    //退出登录
    @PUT(API + "auth/loginOut/{time}")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> loginOut(@Path("time") String time);

    //解绑
    @PUT(API + "auth/untiedThird/{type}")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> untiedThird(@Path("type") int type);


    //发布动态
    @POST(API + "auth/moving")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> releaseMoving(@Body() ReleaseMovingBody releaseMovingBody);


    //删除动态
    @DELETE(API + "auth/moving/{id}")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> delMoving(@Path("id") int id);


    //获取某个动态详情
    @GET(API + "auth/moving/{id}")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> getMoving(@Path("id") String id, @Body() UntiedThirdBody untiedThirdBody);


    //获取自己动态列表
    @POST(API + "auth/moving/list")
    @Headers({"url_name:login"})
    Observable<MyStateEntity> myMovingList(@Body() MyMovingListBody myMovingListBody);


    //获取朋友圈动态列表
    @GET(API + "auth/moving/friend/list/{page}/{max}")
    @Headers({"url_name:login"})
    Observable<MyStateEntity> friendMovingList(@Path("page") int page, @Path("max") long max);

    //修改密码
    @PUT(API + "auth/updatePassword")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> updatePassword(@Body() UpdatePasswordBody updatePasswordBody);

    //修改密码
    @PUT(API + "forgetPassword")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> forgetPassword(@Body() UpdatePasswordBody updatePasswordBody);

    //修改手机号
    @PUT(API + "auth/updatePhone")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> updatePhone(@Body() UpdatePasswordBody updatePasswordBody);

    //实名认证
    @POST(API + "auth/authenticate/realName")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> realName(@Body() AuthRealNameBody authRealName);

    //资质认证
    @POST(API + "auth/authenticate/qualification")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> qualification(@Body() AuthQualificationBody authRealName);

    //经纪人认证
    @POST(API + "auth/authenticate/broker")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> broker(@Body() AuthBrokerBody authBrokerBody);

    //账号绑定
    @POST(API + "auth/bindingThird")
    @Headers({"url_name:login"})
    Observable<SuccessEntity<RegisterEntity>> bindingThird(@Body() BindingThirdBody authBrokerBody);

    //点赞
    @POST(API + "auth/vote")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> vote(@Body() VoteBody voteBody);

    //取消点赞
    @DELETE(API + "auth/vote/{entityId}")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> unVote(@Path("entityId") long entityId);

    //评论
    @POST(API + "auth/comment")
    @Headers({"url_name:login"})
    Observable<SuccessEntity<CommentBean>> comment(@Body() CommentBody commentBody);

    //添加好友
    @POST(API + "auth/friend")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> addFriend(@Body() AddFriendBody commentBody);

    //新增举报
    @POST(API + "auth/appReport")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> appReport(@Body() AppReportBody appReportBody);

    //查询举报列表
    @POST(API + "auth/appReport/query")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> appReportQuery(@Body() MyMovingListBody myMovingListBody);

    //位置信息
    @POST(API + "auth/location")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> location(@Body() LocationBody myMovingListBody);

    //地区列表
    @GET(API + "auth/listAllArea")
    @Headers({"url_name:login"})
    Observable<AreaListEntity> listAllArea();

    //房源发布
    @POST(API + "auth/building/addBuildingInfo")
    @Headers({"url_name:user_info"})
    Observable<SuccessEntity> addBuildingInfo(@QueryMap() Map<String,Object> map);

    //特色标签
    @GET(API + "auth/label/getFeaturedLabel")
    @Headers({"url_name:user_info"})
    Observable<FeaturedLabelEntity> getFeaturedLabel();

    //我的房源列表
    @POST(API + "auth/building/getBuildingList")
    @Headers({"url_name:user_info"})
    Observable<BuildingListEntity> getBuildingList(@QueryMap() Map<String,Object> map);

    //删除房源
    @POST(API + "auth/building/deleteBuilding/{id}")
    @Headers({"url_name:user_info"})
    Observable<SuccessEntity> deleteBuilding(@Path("id") int id);

    //房源详情查询
    @GET(API + "auth/building/getBuildingInfoById")
    @Headers({"url_name:user_info"})
    Observable<SuccessEntity> getBuildingInfoById(@QueryMap() Map<String,Object> map);

}
