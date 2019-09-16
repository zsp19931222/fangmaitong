package me.goldze.mvvmhabit.http.net.body;

/**
 * description:获取验证码body
 * author:created by Andy on 2019/9/16 22:49
 * email:zsp872126510@gmail.com
 */
public class SendCodeBody {
    private String phone;//手机号
    private String timestamp;//时间戳
    private String randomStr;//随机字符串
    private String sign;//签名
    private int type;//手机传1 邮箱传2

    public SendCodeBody(String phone, String timestamp, String randomStr, String sign,int type) {
        this.phone = phone;
        this.timestamp = timestamp;
        this.randomStr = randomStr;
        this.sign = sign;
        this.type=type;
    }
}
