package me.goldze.mvvmhabit.http.net;

import android.annotation.SuppressLint;

import com.blankj.utilcode.util.SPUtils;

import java.io.IOException;
import java.util.List;

import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.ZLog;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static me.goldze.mvvmhabit.http.net.ApiService.IMAGE_BASE_URL;
import static me.goldze.mvvmhabit.http.net.ApiService.IMAGE_BASE_URL1;
import static me.goldze.mvvmhabit.http.net.ApiService.IMAGE_BASE_URL2;
import static me.goldze.mvvmhabit.http.net.ApiService.IMAGE_BASE_URL3;
import static me.goldze.mvvmhabit.http.net.ApiService.IMAGE_BASE_URL4;
import static me.goldze.mvvmhabit.http.net.ApiService.LOGIN_BASE_URL;

/**
 * description: 网络拦截器动态改变baseURL等
 * author: Andy
 * date: 2019/9/9 0009 16:13
 */
public class MyLoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();
        //从request中获取原有的HttpUrl实例oldHttpUrl
        HttpUrl oldHttpUrl = request.url();
        String token, userId;
        try {
            token = SPUtils.getInstance().getString("token");
        } catch (Exception e) {
            ZLog.d(e.toString());
            token = "";
        }
        try {
            userId = LocalDataHelper.getInstance().getUserInfo().getUserId() + "";
        } catch (Exception e) {
            ZLog.d(e.toString());
            userId = "";
        }
        ZLog.d("token:" + token);
        ZLog.d("userId:" + userId);
        Request.Builder builder = request.newBuilder().addHeader("Authorization", token).addHeader("userId", userId).addHeader("contentType", "application/json");
        //从request中获取headers，通过给定的键url_name
        List<String> headerValues = request.headers("url_name");
        if (headerValues != null && headerValues.size() > 0) {
            //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
            builder.removeHeader("url_name");
            //匹配获得新的BaseUrl
            String headerValue = headerValues.get(0);
            HttpUrl newBaseUrl;
            switch (headerValue) {//动态替换基地址
                case "imageUpload":
                    newBaseUrl = HttpUrl.parse(IMAGE_BASE_URL);
                    break;
                case "login":
                    newBaseUrl = HttpUrl.parse(LOGIN_BASE_URL);
                    break;
                case "user_info":
                    newBaseUrl = HttpUrl.parse(IMAGE_BASE_URL1);
                    break;
                case "home":
                    newBaseUrl = HttpUrl.parse(IMAGE_BASE_URL2);
                case "message":
                    newBaseUrl = HttpUrl.parse(IMAGE_BASE_URL2);
                    break;
                case "search":
                    newBaseUrl = HttpUrl.parse(IMAGE_BASE_URL3);
                    break;
                case "push":
                    newBaseUrl = HttpUrl.parse(LOGIN_BASE_URL);
                    break;
                case "micro_application":
                    newBaseUrl = HttpUrl.parse(IMAGE_BASE_URL4);
                    break;
                default:
                    newBaseUrl = oldHttpUrl;
                    break;
            }
            //重建新的HttpUrl，修改需要修改的url部分
            HttpUrl newFullUrl = oldHttpUrl
                    .newBuilder()
                    .host(newBaseUrl.host())//更换主机名
                    .port(newBaseUrl.port())//更换端口
                    .build();
            //重建这个request，通过builder.url(newFullUrl).build()；
            // 然后返回一个response至此结束修改
//            Log.e(LogcatTagUtil.NETWORK_TAG, "intercept: " + newFullUrl.toString());
            request = builder.url(newFullUrl).build();
            return getResponse(request, chain);
        }
        return getResponse(request, chain);
    }

    /**
     * 拦截请求并打印log
     */
    @SuppressLint("DefaultLocale")
    private Response getResponse(Request request, Chain chain) throws IOException {
        long t1 = System.nanoTime();//请求发起的时间
        Response response = chain.proceed(request);

        long t2 = System.nanoTime();//收到响应的时间

        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        ZLog.d(String.format("请求连接: [%s] %n返回json:【%s】 %.1fms%n%s",
                response.request().url(),
                responseBody.string(),
                (t2 - t1) / 1e6d,
                response.headers()));
        return response;
    }

}