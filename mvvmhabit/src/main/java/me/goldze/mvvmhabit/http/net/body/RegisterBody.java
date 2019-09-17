package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/16 23:02
 * email:zsp872126510@gmail.com
 */
public class RegisterBody {
    private String remark; // 备注
    private String qqProvince; // QQ省
    private String createTime; // 创建时间
    private String qqAvatarUrl; // QQ头像url
    private int brokerAuthenticate = -1; // 经纪人认证 0-未认证 1-认证已通过 2-认证未通过
    private String wechatNickname; // 微信昵称
    private String headUrl; // 房脉通头像url
    private String password; // 密码
    private String wechatLanguage; // 微信语言
    private int qualificationAuthenticate = -1; // 资质认证 0-未认证 1-认证已通过 2-认证未通过
    private String qqNickname; // QQ昵称
    private String realName; // 真实姓名
    private String phone; // 手机号
    private String oldPassword; // 旧密码
    private String wechatAvatarUrl; // 微信头像url
    private String wechatProvince; // 微信省
    private int identity = -1; // 身份 1-总代 2-渠道代理 3-联合代理 4-经纪人
    private int realNameAuthenticate = -1; // 实名认证 0-未认证 1-认证已通过 2-认证未通过
    private String wechatCountry; // 微信国家
    private int sex = -1; // 1-男、2-女、0-未知
    private int del = -1; // 删除标志
    private String qqOpenId; // QQopenid
    private String wechatCity; // 微信城市
    private String qqCity; // QQ城市
    private String name; // 房脉通昵称
    private int state = -1; // 状态
    private String mail; // 邮箱
    private int age = -1; // 年龄
    private long cumulativeLoginTime = -1; // 累计登录时间
    private long id = -1; // 编号
    private String lastLogin; // 上次登录时间
    private int enable = -1; // 注销帐号 0-未注销 1-已注销
    private String wechatOpenId; // 微信openid
    private String qqLanguage; // QQ语言
    private String qqCountry; // QQ国家
    private int type = -1;//登录类型 1-手机号 2-邮箱 3-微信 4-QQ
    private String location; // 地点
    private String longitude; // 经度
    private String latitude; // 纬度
    private String imUsername; // 环信id
    private String imPassword; // 环信密码
    private String imNickname; // 环信昵称

    //注册时用
    public RegisterBody(String password, String phone, int identity) {
        this.password = password;
        this.phone = phone;
        this.identity = identity;
    }

}
