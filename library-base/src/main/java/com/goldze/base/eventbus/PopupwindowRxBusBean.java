package com.goldze.base.eventbus;

/**
 * description:
 * author:created by Andy on 2019/9/25 20:20
 * email:zsp872126510@gmail.com
 */
public class PopupwindowRxBusBean {
    private String str;
    private String num;

    public PopupwindowRxBusBean(String str, String num) {
        this.str = str;
        this.num = num;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
