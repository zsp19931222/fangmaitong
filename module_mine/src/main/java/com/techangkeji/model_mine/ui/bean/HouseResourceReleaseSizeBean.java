package com.techangkeji.model_mine.ui.bean;

import com.techangkeji.model_mine.ui.m_enum.HouseTypePriceEnum;
import com.techangkeji.model_mine.ui.m_enum.HouseTypeSizeEnum;

import java.io.Serializable;

public class HouseResourceReleaseSizeBean implements Serializable {
    private String imagePath;
    private String roomNum;
    private String hallNum;
    private String cookNum;
    private String toiletNum;
    private String size;
    private String price;
    private HouseTypePriceEnum houseTypePriceEnum;
    private HouseTypeSizeEnum houseTypeSizeEnum;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
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

    public HouseTypePriceEnum getHouseTypePriceEnum() {
        return houseTypePriceEnum;
    }

    public void setHouseTypePriceEnum(HouseTypePriceEnum houseTypePriceEnum) {
        this.houseTypePriceEnum = houseTypePriceEnum;
    }

    public HouseTypeSizeEnum getHouseTypeSizeEnum() {
        return houseTypeSizeEnum;
    }

    public void setHouseTypeSizeEnum(HouseTypeSizeEnum houseTypeSizeEnum) {
        this.houseTypeSizeEnum = houseTypeSizeEnum;
    }
}
