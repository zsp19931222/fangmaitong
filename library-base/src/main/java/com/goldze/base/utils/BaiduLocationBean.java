package com.goldze.base.utils;

/**
 * description:
 * author:created by Andy on 2019/7/4 0004 11:29
 * email:zsp872126510@gmail.com
 */
public class BaiduLocationBean {
    private boolean locationSuccess;//定位是否成功
    private double latitude;    //获取纬度信息
    private double longitude;    //获取经度信息
    private String country;    //获取国家
    private String province;    //获取省份
    private String city;    //获取城市
    private String district;    //获取区县
    private String street;    //获取街道信息
    private String streetnum;//获取街道号码

    public BaiduLocationBean(boolean locationSuccess,double latitude, double longitude, String country, String province, String city, String district, String street, String streetnum) {
        this.locationSuccess = locationSuccess;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.province = province;
        this.city = city;
        this.district = district;
        this.street = street;
        this.streetnum = streetnum;
    }

    public boolean isLocationSuccess() {
        return locationSuccess;
    }

    public void setLocationSuccess(boolean locationSuccess) {
        this.locationSuccess = locationSuccess;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetnum() {
        return streetnum;
    }

    public void setStreetnum(String streetnum) {
        this.streetnum = streetnum;
    }

    @Override
    public String toString() {
        return "BaiduLocationBean{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", streetnum='" + streetnum + '\'' +
                '}';
    }
}
