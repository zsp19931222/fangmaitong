package com.techangkeji.module.ui.bean;

public class HRDetailAdapterBean {
    public static final int Banner = 1;//房源推荐
    public static final int Notice = 2;//好友推荐
    public static final int Attache = 3;//招聘
    public static final int Detail = 4;//最新资讯
    public static final int Size = 5;//最新资讯
    public static final int Site = 6;//最新资讯
    public static final int State = 7;//最新资讯
    public static final int Comment = 8;//最新资讯
    public static final int Recommend = 9;//最新资讯


    public int type;
    private String title;

    public HRDetailAdapterBean() {
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
