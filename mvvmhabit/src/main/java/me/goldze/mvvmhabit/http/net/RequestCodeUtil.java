package me.goldze.mvvmhabit.http.net;

/**
 * author：Andy on 2019/3/14 0014 17:31
 * email：zsp872126510@gmail.com
 * 请求放回状态码
 */

public class RequestCodeUtil {
    public static final String USER_AUTHENTICATION_FAILURE_CODE = "30006";//token失效
    public static final String USER_CHANGE_EQUIPMENT_FAILURE_CODE = "30007";//其他地方登陆
    public static final String THE_ACCOUNT_HAS_BEEN_DISABLED= "20006";//账号已被停用
    public static final String SUCCESS = "200";
    public static final String HTTP_TAG = "HTTP_TAG";
}
