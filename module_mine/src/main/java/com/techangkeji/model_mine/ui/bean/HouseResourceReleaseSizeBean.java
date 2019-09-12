package com.techangkeji.model_mine.ui.bean;

import java.io.Serializable;

public class HouseResourceReleaseSizeBean implements Serializable {
    private int imagePath;
    private String roomNum;
    private String hallNum;
    private String cookNum;
    private String toiletNum;
    private String size;
    private String price;

    public HouseResourceReleaseSizeBean(int imagePath, String roomNum, String hallNum, String cookNum, String toiletNum, String size, String price) {
        this.imagePath = imagePath;
        this.roomNum = roomNum;
        this.hallNum = hallNum;
        this.cookNum = cookNum;
        this.toiletNum = toiletNum;
        this.size = size;
        this.price = price;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getHallNum() {
        return hallNum;
    }

    public void setHallNum(String hallNum) {
        this.hallNum = hallNum;
    }

    public String getCookNum() {
        return cookNum;
    }

    public void setCookNum(String cookNum) {
        this.cookNum = cookNum;
    }

    public String getToiletNum() {
        return toiletNum;
    }

    public void setToiletNum(String toiletNum) {
        this.toiletNum = toiletNum;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
