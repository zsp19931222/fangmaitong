package com.techangkeji.model_mine.ui.bean;

/**
 * description:
 * author:created by Andy on 2019/9/12 0012 09:13
 * email:zsp872126510@gmail.com
 */
public class HouseResourceReleaseBean {
    public static final int Banner = 1;
    public static final int Detail = 2;
    public static final int Information = 3;
    public static final int Linkman = 4;
    public static final int Size = 5;

    private int type;

    public HouseResourceReleaseBean() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
