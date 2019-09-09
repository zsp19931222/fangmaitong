package com.techangkeji.model_home.ui.bean;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 17:01
 * email:zsp872126510@gmail.com
 */
public class HomeAdapterBean {
    public static final int HomeResourceRecommend = 1;//房源推荐
    public static final int FriendRecommend = 2;//好友推荐
    public static final int Recruitment = 3;//招聘
    public static final int NewInformation = 4;//最新资讯


    public int type;
    private String title;

    public HomeAdapterBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
