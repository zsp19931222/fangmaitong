package me.goldze.mvvmhabit.http.net.entity;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/27 0027 10:02
 * email:zsp872126510@gmail.com
 */
public class RecommendBuildingEntity extends BaseEntity{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * latitude : 40.02487864504815
         * developer_name : 恶魔之眼
         * price_type : 1
         * open_time : 2034-01-01
         * id : 40
         * type : 住宅
         * url : https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/206D268A4DCBE5C8360F7729CB0C9337.jpg
         * listing_name : 扣图
         * longitude : 116.52789891201977
         * labels : ["地铁房","学区房"]
         */

        private String latitude;
        private String developer_name;
        private int price_type;
        private String open_time;
        private int id;
        private String type;
        private String url;
        private String listing_name;
        private String longitude;
        private List<String> labels;
        private int average_price;

        public int getAverage_price() {
            return average_price;
        }

        public void setAverage_price(int average_price) {
            this.average_price = average_price;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getDeveloper_name() {
            return developer_name;
        }

        public void setDeveloper_name(String developer_name) {
            this.developer_name = developer_name;
        }

        public int getPrice_type() {
            return price_type;
        }

        public void setPrice_type(int price_type) {
            this.price_type = price_type;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
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
