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
        /*主业务界面*/
        public static final String PAGER_MAIN = MAIN + "/MainActivity";
    }

    /**
     * 登录页面组件
     */
    public static class Login {
        private static final String Login = "/Login";
        /*登录界面*/
        public static final String LoginActivity = Login + "/LoginActivity";
    }

    /**
     * 首页组件
     */
    public static class Home {
        private static final String Home = "/Home";
        /*登录界面*/
        public static final String HomeFragment = Home + "/HomeFragment";
    }

    /**
     * 社交
     */
    public static class Message {
        private static final String Message = "/Message";
        /*用户详情*/
        public static final String MessageFragment = Message + "/MesssageFragment";
        public static final String MapActivity = Message + "/MapActivity";
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
        /*用户详情*/
        public static final String HRDetailActivity = Public + "/HRFragment";
    }
}
