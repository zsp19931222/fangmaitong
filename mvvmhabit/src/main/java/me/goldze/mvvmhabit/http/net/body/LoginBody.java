package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/17 0017 15:07
 * email:zsp872126510@gmail.com
 */
public class LoginBody {
    String password;
    String phone;
    int  type;
    public LoginBody(String password, String phone, int type) {
        this.password = password;
        this.phone = phone;
        this.type = type;
    }

//    qqOpenId	是	string	qqOpenId q
//    qqCity	是	string	qq市
//    qqNickname	是	string	qq昵称
//    qqAvatarUrl	是	string	qq头像
//    qqProvince	是	string	qq省
//    type	是	string	类型 1-手机号 2-邮箱 3-微信 4-qq
    private String qqOpenId;
    private String qqCity;
    private String qqNickname;
    private String qqAvatarUrl;
    private String qqProvince;

    public LoginBody(int type, String qqOpenId, String qqCity, String qqNickname, String qqAvatarUrl, String qqProvince) {
        this.type = type;
        this.qqOpenId = qqOpenId;
        this.qqCity = qqCity;
        this.qqNickname = qqNickname;
        this.qqAvatarUrl = qqAvatarUrl;
        this.qqProvince = qqProvince;
    }

//    wechatOpenId	是	string	微信OpenId
//    wechatCity	是	string	微信市
//    wechatNickname	是	string	微信昵称
//    wechatAvatarUrl	是	string	微信头像
//    wechatProvince	是	string	微信省
//    type	是	string	类型 1-手机号 2-邮箱 3-微信 4-qq
}
