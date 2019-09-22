package me.goldze.mvvmhabit.litepal;

import org.litepal.crud.LitePalSupport;

/**
 * description:
 * author:created by Andy on 2019/9/21 13:10
 * email:zsp872126510@gmail.com
 */
public class ProvinceLitePal extends LitePalSupport {
    private int provinceid;
    private String areaName;

    public int getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(int provinceid) {
        this.provinceid = provinceid;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
