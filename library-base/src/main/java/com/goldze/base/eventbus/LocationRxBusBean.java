package com.goldze.base.eventbus;

/**
 * description:
 * author:created by Andy on 2019/9/21 20:31
 * email:zsp872126510@gmail.com
 */
public class LocationRxBusBean {
    private String address;
    private int type;//来源
    private String longitude;
    private String latitude;

    public LocationRxBusBean(String address, int type, String longitude, String latitude) {
        this.address = address;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
