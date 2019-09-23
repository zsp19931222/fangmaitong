package com.techangkeji.model_mine.ui.bean;

/**
 * description:
 * author:created by Andy on 2019/9/22 20:39
 * email:zsp872126510@gmail.com
 */
public class SelectFriendBean {
    private String head_url;
    private String phone;
    private String real_name;
    private int id;
    private boolean select;

    public SelectFriendBean(String head_url, String phone, String real_name, int id, boolean select) {
        this.head_url = head_url;
        this.phone = phone;
        this.real_name = real_name;
        this.id = id;
        this.select = select;
    }

    public String getHead_url() {
        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    @Override
    public String toString() {
        return "SelectFriendBean{" +
                "head_url='" + head_url + '\'' +
                ", phone='" + phone + '\'' +
                ", real_name='" + real_name + '\'' +
                ", id=" + id +
                ", select=" + select +
                '}';
    }
}
