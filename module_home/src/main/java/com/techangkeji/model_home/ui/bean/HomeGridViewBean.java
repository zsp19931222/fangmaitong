package com.techangkeji.model_home.ui.bean;

/**
 * description:
 * author:created by Andy on 2019/9/16 0016 15:37
 * email:zsp872126510@gmail.com
 */
public class HomeGridViewBean {
    private Object image;
    private String name;

    public HomeGridViewBean(Object image, String name) {
        this.image = image;
        this.name = name;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
