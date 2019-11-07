package me.goldze.mvvmhabit.http.net;


import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.net.body.AddFriendBody;
import me.goldze.mvvmhabit.http.net.body.AppReportBody;
import me.goldze.mvvmhabit.http.net.body.AppReportListBody;
import me.goldze.mvvmhabit.http.net.body.AuthBrokerBody;
import me.goldze.mvvmhabit.http.net.body.AuthQualificationBody;
import me.goldze.mvvmhabit.http.net.body.AuthRealNameBody;
import me.goldze.mvvmhabit.http.net.body.BannerBody;
import me.goldze.mvvmhabit.http.net.body.BindingThirdBody;
import me.goldze.mvvmhabit.http.net.body.CheckAuthCodeBody;
import me.goldze.mvvmhabit.http.net.body.CommentBody;
import me.goldze.mvvmhabit.http.net.body.CommentListBody;
import me.goldze.mvvmhabit.http.net.body.FeedBackBody;
import me.goldze.mvvmhabit.http.net.body.LocationBody;
import me.goldze.mvvmhabit.http.net.body.LoginBody;
import me.goldze.mvvmhabit.http.net.body.MyMovingListBody;
import me.goldze.mvvmhabit.http.net.body.RecommendFriendBody;
import me.goldze.mvvmhabit.http.net.body.RecruitmentBody;
import me.goldze.mvvmhabit.http.net.body.RecruitmentListBody;
import me.goldze.mvvmhabit.http.net.body.ReleaseMovingBody;
import me.goldze.mvvmhabit.http.net.body.SendCodeBody;
import me.goldze.mvvmhabit.http.net.body.TcJobHuntingBody;
import me.goldze.mvvmhabit.http.net.body.TcJobHuntingListBody;
import me.goldze.mvvmhabit.http.net.body.TcReviewBody;
import me.goldze.mvvmhabit.http.net.body.TcReviewsListBody;
import me.goldze.mvvmhabit.http.net.body.UntiedThirdBody;
import me.goldze.mvvmhabit.http.net.body.UpdateBody;
import me.goldze.mvvmhabit.http.net.body.UpdatePasswordBody;
import me.goldze.mvvmhabit.http.net.body.VoteBody;
import me.goldze.mvvmhabit.http.net.entity.AppReportListEntity;
import me.goldze.mvvmhabit.http.net.entity.AreaListEntity;
import me.goldze.mvvmhabit.http.net.entity.BannerEntity;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.http.net.entity.BuildingListEntity;
import me.goldze.mvvmhabit.http.net.entity.FeaturedLabelEntity;
import me.goldze.mvvmhabit.http.net.entity.HouseResourceDetailEntity;
import me.goldze.mvvmhabit.http.net.entity.JobHuntingEntity;
import me.goldze.mvvmhabit.http.net.entity.LocationEntity;
import me.goldze.mvvmhabit.http.net.entity.MapBuildingEntity;
import me.goldze.mvvmhabit.http.net.entity.NewPlacardEntity;
import me.goldze.mvvmhabit.http.net.entity.RecommendBuildingEntity;
import me.goldze.mvvmhabit.http.net.entity.RecommendFriendEntity;
import me.goldze.mvvmhabit.http.net.entity.RecruitmentListEntity;
import me.goldze.mvvmhabit.http.net.entity.ReviewListEntity;
import me.goldze.mvvmhabit.http.net.entity.SelectFriendEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.UpLoadImageEntity;
import me.goldze.mvvmhabit.http.net.entity.WordEntity;
import me.goldze.mvvmhabit.http.net.entity.friend_circle.CommentBean;
import me.goldze.mvvmhabit.http.net.entity.friend_circle.MyStateEntity;
import me.goldze.mvvmhabit.http.net.entity.friend_circle.UserDetailEntity;
import me.goldze.mvvmhabit.http.net.entity.information.CommentListEntity;
import me.goldze.mvvmhabit.http.net.entity.information.NewsListEntity;
import me.goldze.mvvmhabit.http.net.entity.information.PlacardListEntity;
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
    String LOGIN_BASE_URL = "https://admin.fangmaitong.cn/api/";
    String IMAGE_BASE_URL = "https://admin.fangmaitong.cn/api/";
    String IMAGE_BASE_URL1 = "https://admin.fangmaitong.cn/api/";
    String IMAGE_BASE_URL2 = "https://admin.fangmaitong.cn/api/";
    String IMAGE_BASE_URL3 = "https://admin.fangmaitong.cn/api/";
    String IMAGE_BASE_URL4 = "http://api.map.baidu.com/";
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
    Observable<UpLoadImageEntity> uploadpic(@Part List<MultipartBody.Part> partList);

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

    //好友的动态列表
    @GET(API + "auth/moving/friendId/{friendId}/{page}/20")
    @Headers({"url_name:login"})
    Observable<MyStateEntity> friendStateList(@Path("page") int page, @Path("friendId") int friendId);

    //获取个人详情
    @GET(API + "auth/user/{id}")
    @Headers({"url_name:login"})
    Observable<SuccessEntity<UserDetailEntity>> userDetailData(@Path("id") long id);

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
    Observable<SuccessEntity<LocationEntity>> location(@Body() LocationBody myMovingListBody);

    //地区列表
    @GET(API + "auth/listAllArea")
    @Headers({"url_name:login"})
    Observable<AreaListEntity> listAllArea();

    //举报列表
    @POST(API + "auth/appReport/query")
    @Headers({"url_name:login"})
    Observable<AppReportListEntity> appReportList(@Body() AppReportListBody appReportListBody);

    //反馈
    @POST(API + "auth/appFeedback")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> appFeedback(@Body() FeedBackBody appReportListBody);


    //反馈
    @POST(API + "auth/appFeedback/query")
    @Headers({"url_name:login"})
    Observable<AppReportListEntity> appFeedbackList(@Body() AppReportListBody appReportListBody);

    //推荐好友
    @GET(API + "recommend/{areaId}")
    @Headers({"url_name:login"})
    Observable<RecommendFriendEntity> recommend(@Path("areaId") int entityId);

    //房源发布
    @POST(API + "auth/building/addBuildingInfo")
    @Headers({"url_name:user_info"})
    Observable<SuccessEntity> addBuildingInfo(@QueryMap() Map<String, Object> map);

    //房源列表
    @POST(API + "auth/building/getBuildingList")
    @Headers({"url_name:user_info"})
    Observable<BuildingListEntity> getBuildingList(@QueryMap() Map<String, Object> map);

    //特色标签
    @GET(API + "auth/label/getFeaturedLabel")
    @Headers({"url_name:user_info"})
    Observable<FeaturedLabelEntity> getFeaturedLabel();

    //建筑类型标签
    @GET(API + "auth/label/getTypeLabel")
    @Headers({"url_name:user_info"})
    Observable<SuccessEntity> getTypeLabel();

    //建筑类型标签
    @GET(API + "auth/label/getBuildLabel")
    @Headers({"url_name:user_info"})
    Observable<FeaturedLabelEntity> getBuildLabel();

    //我的房源列表
    @POST(API + "auth/building/myBuildingList")
    @Headers({"url_name:user_info"})
    Observable<BuildingListEntity> myBuildingList(@QueryMap() Map<String, Object> map);

    //删除房源
    @POST(API + "auth/building/deleteBuilding/{id}")
    @Headers({"url_name:user_info"})
    Observable<SuccessEntity> deleteBuilding(@Path("id") int id);

    //房源详情查询
    @GET(API + "auth/building/getBuildingInfoById")
    @Headers({"url_name:user_info"})
    Observable<SuccessEntity<HouseResourceDetailEntity>> getBuildingInfoById(@QueryMap() Map<String, Object> map);

    //好友列表
    @GET(API + "auth/friend/getFriendList")
    @Headers({"url_name:user_info"})
    Observable<SelectFriendEntity> getFriendList(@QueryMap() Map<String, Object> map);

    //地图找房
    @POST(API + "auth/building/getMapBuilding")
    @Headers({"url_name:user_info"})
    Observable<MapBuildingEntity> getMapBuilding(@QueryMap() Map<String, Object> map);

    //公告列表
    @POST(API + "auth/placard/getPlacardList")
    @Headers({"url_name:message"})
    Observable<PlacardListEntity> getPlacardList(@QueryMap() Map<String, Object> map);

    //公告详情
    @POST(API + "auth/placard/getPlacardInfo")
    @Headers({"url_name:message"})
    Observable<SuccessEntity<PlacardListEntity.DataBean>> getPlacardInfo(@QueryMap() Map<String, Object> map);

    //资讯列表
    @POST(API + "auth/news/getNewsList")
    @Headers({"url_name:message"})
    Observable<NewsListEntity> getNewsList(@QueryMap() Map<String, Object> map);

    //资讯详情
    @POST(API + "auth/news/getNewsInfo")
    @Headers({"url_name:message"})
    Observable<SuccessEntity<NewsListEntity.DataBean>> getNewsInfo(@QueryMap() Map<String, Object> map);

    //资讯标签
    @GET(API + "auth/label/getLabelList")
    @Headers({"url_name:message"})
    Observable<FeaturedLabelEntity> getLabelList();

    //评论列表
    @POST(API + "auth/comment/query")
    @Headers({"url_name:login"})
    Observable<CommentListEntity> getCommentList(@Body() CommentListBody myMovingListBody);

    //新增招聘
    @POST(API + "tcRecruitments")
    @Headers({"url_name:search"})
    Observable<SuccessEntity> addRecruitments(@Body() RecruitmentBody myMovingListBody);

    //修改招聘
    @PUT(API + "tcRecruitments")
    @Headers({"url_name:search"})
    Observable<SuccessEntity> changeRecruitments(@Body() RecruitmentBody myMovingListBody);

    //删除招聘
    @DELETE(API + "tcRecruitments/{id}")
    @Headers({"url_name:search"})
    Observable<SuccessEntity> deleteRecruitments(@Path("id") int id);

    //招聘列表
    @POST(API + "tcRecruitments/list")
    @Headers({"url_name:search"})
    Observable<RecruitmentListEntity> recruitmentsList(@Body() RecruitmentListBody myMovingListBody);


    //发布求职
    @POST(API + "auth/tcJobHuntings")
    @Headers({"url_name:search"})
    Observable<SuccessEntity> tcJobHuntings(@Body() TcJobHuntingBody myMovingListBody);

    //修改求职
    @PUT(API + "auth/tcJobHuntings")
    @Headers({"url_name:search"})
    Observable<SuccessEntity> changeTcJobHuntings(@Body() TcJobHuntingBody myMovingListBody);

    //删除求职
    @DELETE(API + "auth/tcJobHuntings/{id}")
    @Headers({"url_name:search"})
    Observable<SuccessEntity> deleteTcJobHuntings(@Path("id") int id);

    //我的求职列表
    @POST(API + "auth/tcJobHuntings/list")
    @Headers({"url_name:search"})
    Observable<JobHuntingEntity> getTcJobHuntingList(@Body() RecruitmentListBody myMovingListBody);


    //求职列表
    @POST(API + "auth/tcJobHuntings/list")
    @Headers({"url_name:search"})
    Observable<JobHuntingEntity> tcJobHuntingsList(@Body() RecruitmentListBody myMovingListBody);


    //推荐房源房源详情
    @POST(API + "auth/building/recommendBuilding")
    @Headers({"url_name:user_info"})
    Observable<RecommendBuildingEntity> recommendBuilding(@QueryMap() Map<String, Object> map);

    //推荐房源首页
    @GET(API + "auth/building/recommend")
    @Headers({"url_name:user_info"})
    Observable<RecommendBuildingEntity> recommendBuildHome(@QueryMap() Map<String, Object> map);

    //推荐资讯
    @GET(API + "auth/news/recommend")
    @Headers({"url_name:message"})
    Observable<NewsListEntity> recommendNewsHome(@QueryMap() Map<String, Object> map);

    //我的收藏
    @POST(API + "auth/building/myCollectionList")
    @Headers({"url_name:user_info"})
    Observable<RecommendBuildingEntity> myCollectionList(@QueryMap() Map<String, Object> map);

    //添加收藏
    @POST(API + "auth/collection/addCollection")
    @Headers({"url_name:user_info"})
    Observable<SuccessEntity> addCollection(@QueryMap() Map<String, Object> map);

    //取消收藏
    @POST(API + "auth/collection/deleteCollection")
    @Headers({"url_name:user_info"})
    Observable<SuccessEntity> deleteCollection(@QueryMap() Map<String, Object> map);

    //是否收藏
    @POST(API + "auth/collection/isCollection")
    @Headers({"url_name:user_info"})
    Observable<SuccessEntity<Integer>> isCollection(@QueryMap() Map<String, Object> map);

    //首页公告
    @GET(API + "auth/placard/getNewPlacard")
    @Headers({"url_name:message"})
    Observable<NewPlacardEntity> getNewPlacard();

    //banner
    @POST(API + "auth/tcBanners/list")
    @Headers({"url_name:user_info"})
    Observable<BannerEntity> bannerList(@Body BannerBody bannerBody);

    //找人
    @POST(API + "user/list")
    @Headers({"url_name:login"})
    Observable<RecommendFriendEntity> userList(@Body RecommendFriendBody recommendFriendBody);

    //首页标签
    @GET(API + "auth/tcWords")
    @Headers({"url_name:user_info"})
    Observable<WordEntity> tcWords();

    //推荐招聘
    @GET(API + "tcRecruitments/recommend/{areaId}")
    @Headers({"url_name:search"})
    Observable<RecruitmentListEntity> RecruitmentsRecommend(@Path("areaId") int entityId);

    //点评列表
    @POST(API + "tcReviews/list")
    @Headers({"url_name:login"})
    Observable<ReviewListEntity> tcReviewsList(@Body TcReviewsListBody tcReviewBody);

    //新增点评
    @POST(API + "tcReviews")
    @Headers({"url_name:login"})
    Observable<SuccessEntity> tcReviews(@Body TcReviewBody tcReviewBody);

}
