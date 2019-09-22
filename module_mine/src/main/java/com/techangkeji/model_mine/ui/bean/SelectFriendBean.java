package com.techangkeji.model_mine.ui.bean;

/**
 * description:
 * author:created by Andy on 2019/9/22 20:39
 * email:zsp872126510@gmail.com
 */
public class SelectFriendBean {
    private String phone;
    private boolean select;

    public SelectFriendBean(String phone, boolean select) {
        this.phone = phone;
        this.select = select;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
