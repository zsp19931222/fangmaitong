package me.goldze.mvvmhabit.litepal;

import org.litepal.crud.LitePalSupport;

/**
 * description:
 * author:created by Andy on 2019/9/21 13:10
 * email:zsp872126510@gmail.com
 */
public class DistrictLitePal extends LitePalSupport {
    private int districtid;
    private int cityId;
    private String cityName;
    private String areaName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getDistrictid() {
        return districtid;
    }

    public void setDistrictid(int districtid) {
        this.districtid = districtid;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
