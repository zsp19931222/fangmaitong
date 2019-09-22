package me.goldze.mvvmhabit.litepal;

import org.litepal.crud.LitePalSupport;

/**
 * description:
 * author:created by Andy on 2019/9/21 13:10
 * email:zsp872126510@gmail.com
 */
public class CityLitePal extends LitePalSupport {
    private int provinceid;
    private int cityId;
    private String cityName;
    private String provinceName;

    public int getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(int provinceid) {
        this.provinceid = provinceid;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
