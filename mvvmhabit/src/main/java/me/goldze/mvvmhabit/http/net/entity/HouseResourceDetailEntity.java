package me.goldze.mvvmhabit.http.net.entity;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/24 0024 17:08
 * email:zsp872126510@gmail.com
 */
public class HouseResourceDetailEntity {

    /**
     * decoration_type : 2
     * sales_address : 北京市大兴区左堤路
     * property_money : 755
     * latitude : 39.82618753829337
     * developer_name : 口卡
     * open_time : 2005-01-01
     * type : [{"area":"1200","img_url":"https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/A2A39F81F091B12DA37904657A01BDC0.jpg","average_price":359,"price_type":2,"type":"1室2厅3厨4卫"}]
     * property_year : 6
     * friend : [{"head_url":"https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/64DB72530CE70259B6499B8C792B21CB.jpg","phone":"18702305506","real_name":"我是1","id":1},{"head_url":"https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/64DB72530CE70259B6499B8C792B21CB.jpg","phone":"18702305506","real_name":"我是1","id":1}]
     * car_num : 3
     * dynamic : []
     * id : 37
     * property_company : 9
     * build_type : 1
     * properties_type : 3
     * listing_name : 楼市
     * longitude : 116.26501534066686
     * resident : 1
     * notice : {"look_rule":"恶魔来了","commission_rule":"没有"}
     * imgs : [{"img_url":"https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/ADB8980F52045F6033E17104C3145342.jpg"}]
     * average_price : 125886
     * broker_notice_id : 1
     * labels : ["地铁房","学区房"]
     * license : 9
     * price_type : 1
     * volume_rate : 2
     * hand_time : 2012-01-01
     */

    private String decoration_type;
    private String sales_address;
    private String property_money;
    private String latitude;
    private String developer_name;
    private String open_time;
    private int property_year;
    private int car_num;
    private int id;
    private String property_company;
    private int build_type;
    private int properties_type;
    private String listing_name;
    private String longitude;
    private int resident;
    private NoticeBean notice;
    private int average_price;
    private String broker_notice_id;
    private String license;
    private int price_type;
    private String volume_rate;
    private String hand_time;
    private List<TypeBean> type;
    private List<FriendBean> friend;
    private List<?> dynamic;
    private List<ImgsBean> imgs;
    private List<String> labels;

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

    public int getResident() {
        return resident;
    }

    public void setResident(int resident) {
        this.resident = resident;
    }

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

    public int getAverage_price() {
        return average_price;
    }

    public void setAverage_price(int average_price) {
        this.average_price = average_price;
    }

    public String getBroker_notice_id() {
        return broker_notice_id;
    }

    public void setBroker_notice_id(String broker_notice_id) {
        this.broker_notice_id = broker_notice_id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int getPrice_type() {
        return price_type;
    }

    public void setPrice_type(int price_type) {
        this.price_type = price_type;
    }

    public String getVolume_rate() {
        return volume_rate;
    }

    public void setVolume_rate(String volume_rate) {
        this.volume_rate = volume_rate;
    }

    public String getHand_time() {
        return hand_time;
    }

    public void setHand_time(String hand_time) {
        this.hand_time = hand_time;
    }

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(List<TypeBean> type) {
        this.type = type;
    }

    public List<FriendBean> getFriend() {
        return friend;
    }

    public void setFriend(List<FriendBean> friend) {
        this.friend = friend;
    }

    public List<?> getDynamic() {
        return dynamic;
    }

    public void setDynamic(List<?> dynamic) {
        this.dynamic = dynamic;
    }

    public List<ImgsBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<ImgsBean> imgs) {
        this.imgs = imgs;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public static class NoticeBean {
        /**
         * look_rule : 恶魔来了
         * commission_rule : 没有
         */

        private String look_rule;
        private String commission_rule;

        public String getLook_rule() {
            return look_rule;
        }

        public void setLook_rule(String look_rule) {
            this.look_rule = look_rule;
        }

        public String getCommission_rule() {
            return commission_rule;
        }

        public void setCommission_rule(String commission_rule) {
            this.commission_rule = commission_rule;
        }
    }

    public static class TypeBean {
        /**
         * area : 1200
         * img_url : https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/A2A39F81F091B12DA37904657A01BDC0.jpg
         * average_price : 359
         * price_type : 2
         * type : 1室2厅3厨4卫
         */

        private String area;
        private String img_url;
        private int average_price;
        private int price_type;
        private String type;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public int getAverage_price() {
            return average_price;
        }

        public void setAverage_price(int average_price) {
            this.average_price = average_price;
        }

        public int getPrice_type() {
            return price_type;
        }

        public void setPrice_type(int price_type) {
            this.price_type = price_type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class FriendBean {
        /**
         * head_url : https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/64DB72530CE70259B6499B8C792B21CB.jpg
         * phone : 18702305506
         * real_name : 我是1
         * id : 1
         */

        private String head_url;
        private String phone;
        private String real_name;
        private int id;

        public String getHead_url() {
            return head_url;
        }

        public void setHead_url(String head_url) {
            this.head_url = head_url;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class ImgsBean {
        /**
         * img_url : https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/ADB8980F52045F6033E17104C3145342.jpg
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
