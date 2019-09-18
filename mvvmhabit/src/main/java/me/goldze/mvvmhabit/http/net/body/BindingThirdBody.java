package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/18 0018 16:34
 * email:zsp872126510@gmail.com
 */
public class BindingThirdBody {
    private Long id;
    private int type;
    private String phone;
    private String password;


    private String qqOpenId;
    private String qqCity;
    private String qqNickname;
    private String qqAvatarUrl;
    private String qqProvince;


    public BindingThirdBody(Long id, int type, String phone, String password) {
        this.id = id;
        this.type = type;
        this.phone = phone;
        this.password = password;
    }

    public BindingThirdBody(int type, String phone, String qqOpenId, String qqCity, String qqNickname, String qqAvatarUrl, String qqProvince) {
        this.type = type;
        this.phone = phone;
        this.qqOpenId = qqOpenId;
        this.qqCity = qqCity;
        this.qqNickname = qqNickname;
        this.qqAvatarUrl = qqAvatarUrl;
        this.qqProvince = qqProvince;
    }
}
