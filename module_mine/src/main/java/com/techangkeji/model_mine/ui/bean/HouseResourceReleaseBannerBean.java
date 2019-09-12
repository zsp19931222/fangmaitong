package com.techangkeji.model_mine.ui.bean;

/**
 * description:
 * author:created by Andy on 2019/9/12 0012 09:58
 * email:zsp872126510@gmail.com
 */
public class HouseResourceReleaseBannerBean {
    private String imagePath;
    private String describe;

    public HouseResourceReleaseBannerBean(String imagePath, String describe) {
        this.imagePath = imagePath;
        this.describe = describe;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "HouseResourceReleaseBannerBean{" +
                "imagePath='" + imagePath + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}
