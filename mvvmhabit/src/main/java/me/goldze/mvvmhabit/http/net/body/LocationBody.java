package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/20 20:57
 * email:zsp872126510@gmail.com
 */
public class LocationBody {
    private String province ; // 省
    private String city ; // 市
    private String district ; // 区
    private String location ; // 地点
    private String longitude ; // 经度
    private String latitude ; // 纬度

    public LocationBody(String province, String city, String district, String location, String longitude, String latitude) {
        this.province = province;
        this.city = city;
        this.district = district;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
