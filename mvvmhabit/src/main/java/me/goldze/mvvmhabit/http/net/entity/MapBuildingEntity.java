package me.goldze.mvvmhabit.http.net.entity;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/29 0029 16:51
 * email:zsp872126510@gmail.com
 */
public class MapBuildingEntity extends BaseEntity{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * areaName : 九龙坡区
         * count : 1
         * lon : 109.3936157227
         * lat : 32.6440004483
         */

        private String areaName;
        private int count;
        private String lon;
        private String lat;

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }
    }
}
