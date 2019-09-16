package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/16 22:59
 * email:zsp872126510@gmail.com
 */
public class CheckAuthCodeBody {
    private String phone;//手机号
    private String code;//验证码

    public CheckAuthCodeBody(String phone, String code) {
        this.phone = phone;
        this.code = code;
    }
}
