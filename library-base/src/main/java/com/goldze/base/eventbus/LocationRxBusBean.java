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
    private String province ; // 省
    private String city ; // 市
    private String district ; // 区

    public LocationRxBusBean(String address, int type, String longitude, String latitude, String province, String city, String district) {
        this.address = address;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.province = province;
        this.city = city;
        this.district = district;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
