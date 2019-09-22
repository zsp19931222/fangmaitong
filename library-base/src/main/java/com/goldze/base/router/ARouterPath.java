package com.goldze.base.router;

/**
 * 用于组件开发中，ARouter单Activity跳转的统一路径注册
 * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 * Created by Andy on 2018/6/21
 */

public class ARouterPath {
    /**
     * 主业务组件
     */
    public static class Main {
        private static final String MAIN = "/main";
        public static final String PAGER_MAIN = MAIN + "/MainActivity";
    }

    /**
     * 登录页面组件
     */
    public static class Login {
        private static final String Login = "/Login";
        public static final String LoginActivity = Login + "/LoginActivity";
    }

    /**
     * 首页组件
     */
    public static class Home {
        private static final String Home = "/Home";
        public static final String HomeFragment = Home + "/HomeFragment";
        public static final String InformActivity = Home + "/InformActivity";
        public static final String HousingInformationActivity = Home + "/HousingInformationActivity";
    }

    /**
     * 社交
     */
    public static class Message {
        private static final String Message = "/Message";
        public static final String MessageFragment = Message + "/MesssageFragment";
        public static final String MapActivity = Message + "/MapActivity";
        public static final String ReleaseInformationActivity = Message + "/ReleaseInformationActivity";
        public static final String AddContactActivity = Message + "/AddContactActivity";
    }

    /**
     * 房源
     */
    public static class HouseResource {
        private static final String HouseResource = "/HouseResource";
        public static final String HRFragment = HouseResource + "/HRFragment";
        public static final String HHPublicActivity = HouseResource + "/HHPublicActivity";
    }
    /**
     * 公共页面
     */
    public static class Public {
        private static final String Public = "/Public";
        public static final String HRDetailActivity = Public + "/HRFragment";
        public static final String AreaSelectActivity = Public + "/AreaSelectActivity";
        public static final String MoreAddressActivity = Public + "/MoreAddressActivity";
    }
    /**
     * 我的页面
     */
    public static class Mine {
        private static final String Mine = "/Mine";
        public static final String MinePage = Mine + "/HRFragment";
        public static final String HouseResourceReleaseActivity = Mine + "/HouseResourceReleaseActivity";
    }
    /**
     * 朋友圈
     */
    public static class FriendCircle {
        private static final String FriendCircle = "/FriendCircle";
        public static final String FriendCircleActivity = FriendCircle + "/FriendCircleActivity";
        public static final String MyStateActivity = FriendCircle + "/MyStateActivity";
        public static final String PersonCircleStateActivity = FriendCircle + "/PersonCircleStateActivity";
        public static final String FriendCircleFragment = FriendCircle + "/FriendCircleFragment";
        public static final String CardActivity = FriendCircle + "/CardActivity";
    }
    /**
     * 资讯
     */
    public static class Information {
        private static final String Information = "/Information";
        public static final String InviteDetailActivity = Information + "/InviteDetailActivity";
        public static final String InformationMainFragment = Information + "/InformationMainFragment";
        public static final String InformationDetailActivity = Information + "/InformationDetailActivity";
    }
}
