package me.goldze.mvvmhabit.http.net.entity;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/22 16:43
 * email:zsp872126510@gmail.com
 */
public class BuildingListEntity extends BaseEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * latitude : 39.6114659222
         * average_price : 399999
         * developer_name :
         * open_time : 2019-08-25
         * id : 3
         * listing_name :
         * longitude : 116.7253479027
         * labels : ["地铁房","学区房","住宅","别墅"]
         */

        private String latitude;
        private int average_price;
        private String developer_name;
        private String open_time;
        private int id;
        private String listing_name;
        private String longitude;
        private List<String> labels;
        private String url;
        private String type;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public int getAverage_price() {
            return average_price;
        }

        public void setAverage_price(int average_price) {
            this.average_price = average_price;
        }

        public String getDeveloper_name() {
            return developer_name;
        }

        public void setDeveloper_name(String developer_name) {
            this.developer_name = developer_name;
        }

        public String getOpen_time() {
            return open_time;
        }

        public void setOpen_time(String open_time) {
            this.open_time = open_time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getListing_name() {
            return listing_name;
        }

        public void setListing_name(String listing_name) {
            this.listing_name = listing_name;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public List<String> getLabels() {
            return labels;
        }

        public void setLabels(List<String> labels) {
            this.labels = labels;
        }
    }
}
