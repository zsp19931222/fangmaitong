package com.goldze.base.utils;

import android.app.Activity;
import android.content.Context;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.utils.ZLog;


/**
 * description:第三方登录
 * author:created by Andy on 2019/8/2 0002 10:43
 * email:zsp872126510@gmail.com
 */
public class OtherLoginUtil {
    public static final String LOGIN_SUCCESS = "200";
    public static final String LOGIN_FAIL = "201";
    public static final String LOGIN_CANCEL = "202";
    private UMShareAPI mShareAPI;
    private UMAuthListener mAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            ZLog.e("openid: " + map.get("openid"));
            ZLog.e("昵称: " + map.get("name"));
            ZLog.e("头像: " + map.get("iconurl"));
            ZLog.e("性别: " + map.get("gender"));
            RxBus.getDefault().post(new OtherLoginBean(share_media, LOGIN_SUCCESS, map.get("openid"), map.get("name"), map.get("iconurl"), map.get("gender"), map.get("city"), map.get("province")));
            ZLog.d(share_media);
            ZLog.d(map);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            RxBus.getDefault().post(new OtherLoginBean(share_media, LOGIN_FAIL, "", "", "", "", "", ""));
            ZLog.d(throwable.toString());
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            ZLog.d(i);
            RxBus.getDefault().post(new OtherLoginBean(share_media, LOGIN_CANCEL, "", "", "", "", "", ""));
        }
    };

    private OtherLoginUtil() {
    }

    private static class OtherLoginUtilHolder {
        private static final OtherLoginUtil INSTANCE = new OtherLoginUtil();
    }

    public static OtherLoginUtil getInstance() {
        return OtherLoginUtilHolder.INSTANCE;
    }

    public void login(Context context, String loginWayEnum) {
        switch (loginWayEnum) {
            case "QQ":
                loginByQQ(context);
                break;
            case "WECHAT":
                loginByWeChat(context);
                break;
        }
    }

    /**
     * description: QQ登录
     * author: Andy
     * date: 2019/8/2 0002 10:46
     */
    private void loginByQQ(Context context) {
        mShareAPI = UMShareAPI.get(context);
        mShareAPI.getPlatformInfo((Activity) context, SHARE_MEDIA.QQ, mAuthListener);//QQ登录
//        mShareAPI.deleteOauth((Activity) context, SHARE_MEDIA.QQ, mAuthListener);//撤销QQ授权
    }

    /**
     * description: 微信登录
     * author: Andy
     * date: 2019/8/2 0002 12:02
     */
    private void loginByWeChat(Context context) {
        mShareAPI = UMShareAPI.get(context);
        mShareAPI.getPlatformInfo((Activity) context, SHARE_MEDIA.WEIXIN, mAuthListener);
    }

    /**
     * description: 获取到的用户数据
     * author: Andy
     * date: 2019/8/2 0002 12:06
     */
    public class OtherLoginBean {
        private SHARE_MEDIA type;
        private String code;
        private String openid;
        private String name;
        private String iconurl;
        private String gender;
        private String city;
        private String province;


        public OtherLoginBean(SHARE_MEDIA type, String code, String openid, String name, String iconurl, String gender, String city, String province) {
            this.type = type;
            this.code = code;
            this.openid = openid;
            this.name = name;
            this.iconurl = iconurl;
            this.gender = gender;
            this.city = city;
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }


        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public SHARE_MEDIA getType() {
            return type;
        }

        public void setType(SHARE_MEDIA type) {
            this.type = type;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIconurl() {
            return iconurl;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }
}
