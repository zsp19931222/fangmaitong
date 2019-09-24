package me.goldze.mvvmhabit.http.net.entity;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/24 0024 17:08
 * email:zsp872126510@gmail.com
 */
public class HouseResourceDetailEntity {

    /**
     * imgs : [{"img_url":"https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/D6F47CF2F83065ACBFBCB01A65EBF422.jpg"},{"img_url":"https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/ADAD88512576EA2C455B9159676A6EB6.jpg"}]
     * decoration_type : 1
     * sales_address : 重庆市沙坪坝区天星桥正街46-28号
     * property_money : 65.4
     * latitude : 29.525098028958013
     * average_price : 12000
     * developer_name : 开发区
     * open_time : 2020-01-01
     * type : []
     * labels : ["地铁房","学区房"]
     * license : 456797
     * property_year : 70
     * car_num : 20
     * dynamic : []
     * id : 16
     * property_company : 4676
     * build_type : 1
     * properties_type : 3
     * volume_rate : 1
     * listing_name : 楼盘
     * hand_time : 2030-01-01
     * longitude : 106.46715599999995
     * resident : 1
     * notice : null
     */

    private String decoration_type;
    private String sales_address;
    private String property_money;
    private String latitude;
    private int average_price;
    private String developer_name;
    private String open_time;
    private String license;
    private int property_year;
    private int car_num;
    private int id;
    private String property_company;
    private int build_type;
    private int properties_type;
    private String volume_rate;
    private String listing_name;
    private String hand_time;
    private String longitude;
    private int resident;
    private Object notice;
    private List<ImgsBean> imgs;
    private List<?> type;
    private List<String> labels;
    private List<?> dynamic;

    public String getDecoration_type() {
        return decoration_type;
    }

    public void setDecoration_type(String decoration_type) {
        this.decoration_type = decoration_type;
    }

    public String getSales_address() {
        return sales_address;
    }

    public void setSales_address(String sales_address) {
        this.sales_address = sales_address;
    }

    public String getProperty_money() {
        return property_money;
    }

    public void setProperty_money(String property_money) {
        this.property_money = property_money;
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

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int getProperty_year() {
        return property_year;
    }

    public void setProperty_year(int property_year) {
        this.property_year = property_year;
    }

    public int getCar_num() {
        return car_num;
    }

    public void setCar_num(int car_num) {
        this.car_num = car_num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProperty_company() {
        return property_company;
    }

    public void setProperty_company(String property_company) {
        this.property_company = property_company;
    }

    public int getBuild_type() {
        return build_type;
    }

    public void setBuild_type(int build_type) {
        this.build_type = build_type;
    }

    public int getProperties_type() {
        return properties_type;
    }

    public void setProperties_type(int properties_type) {
        this.properties_type = properties_type;
    }

    public String getVolume_rate() {
        return volume_rate;
    }

    public void setVolume_rate(String volume_rate) {
        this.volume_rate = volume_rate;
    }

    public String getListing_name() {
        return listing_name;
    }

    public void setListing_name(String listing_name) {
        this.listing_name = listing_name;
    }

    public String getHand_time() {
        return hand_time;
    }

    public void setHand_time(String hand_time) {
        this.hand_time = hand_time;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getResident() {
        return resident;
    }

    public void setResident(int resident) {
        this.resident = resident;
    }

    public Object getNotice() {
        return notice;
    }

    public void setNotice(Object notice) {
        this.notice = notice;
    }

    public List<ImgsBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgsBean> imgs) {
        this.imgs = imgs;
    }

    public List<?> getType() {
        return type;
    }

    public void setType(List<?> type) {
        this.type = type;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<?> getDynamic() {
        return dynamic;
    }

    public void setDynamic(List<?> dynamic) {
        this.dynamic = dynamic;
    }

    public static class ImgsBean {
        /**
         * img_url : https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/D6F47CF2F83065ACBFBCB01A65EBF422.jpg
         */

        private String img_url;

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
}
