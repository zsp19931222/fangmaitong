package me.goldze.mvvmhabit.http.net.entity;

/**
 * description:
 * author:created by Andy on 2019/9/23 21:31
 * email:zsp872126510@gmail.com
 */
public class LocationEntity {

    /**
     * remark : null
     * location : 重庆市重庆市九龙坡区
     * createTime : null
     * latitude : 29.524817
     * id : 187
     * district : 九龙坡区
     * del : -1
     * userId : 3
     * areaId : 10
     * province : 重庆市
     * longitude : 106.466902
     * city : 重庆市
     * state : -1
     */

    private Object remark;
    private String location;
    private Object createTime;
    private String latitude;
    private int id;
    private String district;
    private int del;
    private int userId;
    private int areaId;
    private String province;
    private String longitude;
    private String city;
    private int state;

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
