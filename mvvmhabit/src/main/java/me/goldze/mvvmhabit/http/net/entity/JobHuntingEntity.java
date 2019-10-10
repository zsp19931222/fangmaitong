package me.goldze.mvvmhabit.http.net.entity;

import java.io.Serializable;
import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/29 22:11
 * email:zsp872126510@gmail.com
 */
public class JobHuntingEntity extends BaseEntity implements Serializable {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * remark : null
         * workContent : 捡垃圾过活
         * lon : null
         * workAddress : 废品回收站
         * workYear : 10
         * createTime : 2019-09-24 15:13:43
         * huntingHumenName : 孟佳杰
         * huntingHumenId : 3
         * huntingHumenHeadUrl : aaa.jpg
         * workNature : 捡垃圾
         * id : 1
         * qualificationAuthenticate : 0
         * huntingHumenIdentity : 傻逼
         * huntingHumenAccount : 11111111111
         * sex : 0
         * del : 0
         * huntingTitle : 求口饭吃
         * contactId : 3
         * state : 0
         * lat : null
         * user : {"imNickname":null,"remark":null,"imUsername":"13983251013","squareNum":0,"movingNum":0,"qqProvince":null,"wxappOpenId":null,"createTime":"2019-09-20 11:07:52","qqAvatarUrl":null,"brokerAuthenticate":0,"wechatNickname":null,"headUrl":"https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/C6446BA6DF170315187D0C65AF7D2298.jpg","password":"654321","recruitmentNum":0,"wechatLanguage":null,"qualificationAuthenticate":0,"qqNickname":null,"realName":"我是3","phone":"13983251013","oldPassword":null,"wechatAvatarUrl":null,"wechatProvince":null,"jobhuntingNum":0,"identity":0,"realNameAuthenticate":0,"wechatCountry":null,"sex":2,"del":0,"qqOpenId":null,"wechatCity":null,"qqCity":null,"name":"鞠","imPassword":"123456","state":0,"mail":null,"age":25,"buildNum":0,"cumulativeLoginTime":404216157,"id":3,"lastLogin":"2019-09-29","enable":0,"wechatOpenId":null,"qqLanguage":null,"freeze":0,"collectNum":0,"qqCountry":null,"recommend":1,"ids":null,"recom":-1}
         * contactUser : {"imNickname":null,"remark":null,"imUsername":"13983251013","squareNum":0,"movingNum":0,"qqProvince":null,"wxappOpenId":null,"createTime":"2019-09-20 11:07:52","qqAvatarUrl":null,"brokerAuthenticate":0,"wechatNickname":null,"headUrl":"https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/C6446BA6DF170315187D0C65AF7D2298.jpg","password":"654321","recruitmentNum":0,"wechatLanguage":null,"qualificationAuthenticate":0,"qqNickname":null,"realName":"我是3","phone":"13983251013","oldPassword":null,"wechatAvatarUrl":null,"wechatProvince":null,"jobhuntingNum":0,"identity":0,"realNameAuthenticate":0,"wechatCountry":null,"sex":2,"del":0,"qqOpenId":null,"wechatCity":null,"qqCity":null,"name":"鞠","imPassword":"123456","state":0,"mail":null,"age":25,"buildNum":0,"cumulativeLoginTime":404216157,"id":3,"lastLogin":"2019-09-29","enable":0,"wechatOpenId":null,"qqLanguage":null,"freeze":0,"collectNum":0,"qqCountry":null,"recommend":1,"ids":null,"recom":-1}
         * district : null
         * province : null
         * city : null
         */

        private Object remark;
        private String workContent;
        private Object lon;
        private String workAddress;
        private String workYear;
        private String createTime;
        private String huntingHumenName;
        private int huntingHumenId;
        private String huntingHumenHeadUrl;
        private String workNature;
        private int id;
        private int qualificationAuthenticate;
        private String huntingHumenIdentity;
        private String huntingHumenAccount;
        private int sex;
        private int del;
        private String huntingTitle;
        private int contactId;
        private int state;
        private Object lat;
        private UserBean user;
        private ContactUserBean contactUser;
        private Object district;
        private Object province;
        private Object city;

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getWorkContent() {
            return workContent;
        }

        public void setWorkContent(String workContent) {
            this.workContent = workContent;
        }

        public Object getLon() {
            return lon;
        }

        public void setLon(Object lon) {
            this.lon = lon;
        }

        public String getWorkAddress() {
            return workAddress;
        }

        public void setWorkAddress(String workAddress) {
            this.workAddress = workAddress;
        }

        public String getWorkYear() {
            return workYear;
        }

        public void setWorkYear(String workYear) {
            this.workYear = workYear;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getHuntingHumenName() {
            return huntingHumenName;
        }

        public void setHuntingHumenName(String huntingHumenName) {
            this.huntingHumenName = huntingHumenName;
        }

        public int getHuntingHumenId() {
            return huntingHumenId;
        }

        public void setHuntingHumenId(int huntingHumenId) {
            this.huntingHumenId = huntingHumenId;
        }

        public String getHuntingHumenHeadUrl() {
            return huntingHumenHeadUrl;
        }

        public void setHuntingHumenHeadUrl(String huntingHumenHeadUrl) {
            this.huntingHumenHeadUrl = huntingHumenHeadUrl;
        }

        public String getWorkNature() {
            return workNature;
        }

        public void setWorkNature(String workNature) {
            this.workNature = workNature;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getQualificationAuthenticate() {
            return qualificationAuthenticate;
        }

        public void setQualificationAuthenticate(int qualificationAuthenticate) {
            this.qualificationAuthenticate = qualificationAuthenticate;
        }

        public String getHuntingHumenIdentity() {
            return huntingHumenIdentity;
        }

        public void setHuntingHumenIdentity(String huntingHumenIdentity) {
            this.huntingHumenIdentity = huntingHumenIdentity;
        }

        public String getHuntingHumenAccount() {
            return huntingHumenAccount;
        }

        public void setHuntingHumenAccount(String huntingHumenAccount) {
            this.huntingHumenAccount = huntingHumenAccount;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
        }

        public String getHuntingTitle() {
            return huntingTitle;
        }

        public void setHuntingTitle(String huntingTitle) {
            this.huntingTitle = huntingTitle;
        }

        public int getContactId() {
            return contactId;
        }

        public void setContactId(int contactId) {
            this.contactId = contactId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public Object getLat() {
            return lat;
        }

        public void setLat(Object lat) {
            this.lat = lat;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public ContactUserBean getContactUser() {
            return contactUser;
        }

        public void setContactUser(ContactUserBean contactUser) {
            this.contactUser = contactUser;
        }

        public Object getDistrict() {
            return district;
        }

        public void setDistrict(Object district) {
            this.district = district;
        }

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public static class UserBean implements Serializable{
            /**
             * imNickname : null
             * remark : null
             * imUsername : 13983251013
             * squareNum : 0
             * movingNum : 0
             * qqProvince : null
             * wxappOpenId : null
             * createTime : 2019-09-20 11:07:52
             * qqAvatarUrl : null
             * brokerAuthenticate : 0
             * wechatNickname : null
             * headUrl : https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/C6446BA6DF170315187D0C65AF7D2298.jpg
             * password : 654321
             * recruitmentNum : 0
             * wechatLanguage : null
             * qualificationAuthenticate : 0
             * qqNickname : null
             * realName : 我是3
             * phone : 13983251013
             * oldPassword : null
             * wechatAvatarUrl : null
             * wechatProvince : null
             * jobhuntingNum : 0
             * identity : 0
             * realNameAuthenticate : 0
             * wechatCountry : null
             * sex : 2
             * del : 0
             * qqOpenId : null
             * wechatCity : null
             * qqCity : null
             * name : 鞠
             * imPassword : 123456
             * state : 0
             * mail : null
             * age : 25
             * buildNum : 0
             * cumulativeLoginTime : 404216157
             * id : 3
             * lastLogin : 2019-09-29
             * enable : 0
             * wechatOpenId : null
             * qqLanguage : null
             * freeze : 0
             * collectNum : 0
             * qqCountry : null
             * recommend : 1
             * ids : null
             * recom : -1
             */

            private Object imNickname;
            private Object remark;
            private String imUsername;
            private int squareNum;
            private int movingNum;
            private Object qqProvince;
            private Object wxappOpenId;
            private String createTime;
            private Object qqAvatarUrl;
            private int brokerAuthenticate;
            private Object wechatNickname;
            private String headUrl;
            private String password;
            private int recruitmentNum;
            private Object wechatLanguage;
            private int qualificationAuthenticate;
            private Object qqNickname;
            private String realName;
            private String phone;
            private Object oldPassword;
            private Object wechatAvatarUrl;
            private Object wechatProvince;
            private int jobhuntingNum;
            private int identity;
            private int realNameAuthenticate;
            private Object wechatCountry;
            private int sex;
            private int del;
            private Object qqOpenId;
            private Object wechatCity;
            private Object qqCity;
            private String name;
            private String imPassword;
            private int state;
            private Object mail;
            private int age;
            private int buildNum;
            private long cumulativeLoginTime;
            private int id;
            private String lastLogin;
            private int enable;
            private Object wechatOpenId;
            private Object qqLanguage;
            private int freeze;
            private int collectNum;
            private Object qqCountry;
            private int recommend;
            private Object ids;
            private int recom;

            public Object getImNickname() {
                return imNickname;
            }

            public void setImNickname(Object imNickname) {
                this.imNickname = imNickname;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public String getImUsername() {
                return imUsername;
            }

            public void setImUsername(String imUsername) {
                this.imUsername = imUsername;
            }

            public int getSquareNum() {
                return squareNum;
            }

            public void setSquareNum(int squareNum) {
                this.squareNum = squareNum;
            }

            public int getMovingNum() {
                return movingNum;
            }

            public void setMovingNum(int movingNum) {
                this.movingNum = movingNum;
            }

            public Object getQqProvince() {
                return qqProvince;
            }

            public void setQqProvince(Object qqProvince) {
                this.qqProvince = qqProvince;
            }

            public Object getWxappOpenId() {
                return wxappOpenId;
            }

            public void setWxappOpenId(Object wxappOpenId) {
                this.wxappOpenId = wxappOpenId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getQqAvatarUrl() {
                return qqAvatarUrl;
            }

            public void setQqAvatarUrl(Object qqAvatarUrl) {
                this.qqAvatarUrl = qqAvatarUrl;
            }

            public int getBrokerAuthenticate() {
                return brokerAuthenticate;
            }

            public void setBrokerAuthenticate(int brokerAuthenticate) {
                this.brokerAuthenticate = brokerAuthenticate;
            }

            public Object getWechatNickname() {
                return wechatNickname;
            }

            public void setWechatNickname(Object wechatNickname) {
                this.wechatNickname = wechatNickname;
            }

            public String getHeadUrl() {
                return headUrl;
            }

            public void setHeadUrl(String headUrl) {
                this.headUrl = headUrl;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public int getRecruitmentNum() {
                return recruitmentNum;
            }

            public void setRecruitmentNum(int recruitmentNum) {
                this.recruitmentNum = recruitmentNum;
            }

            public Object getWechatLanguage() {
                return wechatLanguage;
            }

            public void setWechatLanguage(Object wechatLanguage) {
                this.wechatLanguage = wechatLanguage;
            }

            public int getQualificationAuthenticate() {
                return qualificationAuthenticate;
            }

            public void setQualificationAuthenticate(int qualificationAuthenticate) {
                this.qualificationAuthenticate = qualificationAuthenticate;
            }

            public Object getQqNickname() {
                return qqNickname;
            }

            public void setQqNickname(Object qqNickname) {
                this.qqNickname = qqNickname;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public Object getOldPassword() {
                return oldPassword;
            }

            public void setOldPassword(Object oldPassword) {
                this.oldPassword = oldPassword;
            }

            public Object getWechatAvatarUrl() {
                return wechatAvatarUrl;
            }

            public void setWechatAvatarUrl(Object wechatAvatarUrl) {
                this.wechatAvatarUrl = wechatAvatarUrl;
            }

            public Object getWechatProvince() {
                return wechatProvince;
            }

            public void setWechatProvince(Object wechatProvince) {
                this.wechatProvince = wechatProvince;
            }

            public int getJobhuntingNum() {
                return jobhuntingNum;
            }

            public void setJobhuntingNum(int jobhuntingNum) {
                this.jobhuntingNum = jobhuntingNum;
            }

            public int getIdentity() {
                return identity;
            }

            public void setIdentity(int identity) {
                this.identity = identity;
            }

            public int getRealNameAuthenticate() {
                return realNameAuthenticate;
            }

            public void setRealNameAuthenticate(int realNameAuthenticate) {
                this.realNameAuthenticate = realNameAuthenticate;
            }

            public Object getWechatCountry() {
                return wechatCountry;
            }

            public void setWechatCountry(Object wechatCountry) {
                this.wechatCountry = wechatCountry;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getDel() {
                return del;
            }

            public void setDel(int del) {
                this.del = del;
            }

            public Object getQqOpenId() {
                return qqOpenId;
            }

            public void setQqOpenId(Object qqOpenId) {
                this.qqOpenId = qqOpenId;
            }

            public Object getWechatCity() {
                return wechatCity;
            }

            public void setWechatCity(Object wechatCity) {
                this.wechatCity = wechatCity;
            }

            public Object getQqCity() {
                return qqCity;
            }

            public void setQqCity(Object qqCity) {
                this.qqCity = qqCity;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImPassword() {
                return imPassword;
            }

            public void setImPassword(String imPassword) {
                this.imPassword = imPassword;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public Object getMail() {
                return mail;
            }

            public void setMail(Object mail) {
                this.mail = mail;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public int getBuildNum() {
                return buildNum;
            }

            public void setBuildNum(int buildNum) {
                this.buildNum = buildNum;
            }

            public long getCumulativeLoginTime() {
                return cumulativeLoginTime;
            }

            public void setCumulativeLoginTime(long cumulativeLoginTime) {
                this.cumulativeLoginTime = cumulativeLoginTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLastLogin() {
                return lastLogin;
            }

            public void setLastLogin(String lastLogin) {
                this.lastLogin = lastLogin;
            }

            public int getEnable() {
                return enable;
            }

            public void setEnable(int enable) {
                this.enable = enable;
            }

            public Object getWechatOpenId() {
                return wechatOpenId;
            }

            public void setWechatOpenId(Object wechatOpenId) {
                this.wechatOpenId = wechatOpenId;
            }

            public Object getQqLanguage() {
                return qqLanguage;
            }

            public void setQqLanguage(Object qqLanguage) {
                this.qqLanguage = qqLanguage;
            }

            public int getFreeze() {
                return freeze;
            }

            public void setFreeze(int freeze) {
                this.freeze = freeze;
            }

            public int getCollectNum() {
                return collectNum;
            }

            public void setCollectNum(int collectNum) {
                this.collectNum = collectNum;
            }

            public Object getQqCountry() {
                return qqCountry;
            }

            public void setQqCountry(Object qqCountry) {
                this.qqCountry = qqCountry;
            }

            public int getRecommend() {
                return recommend;
            }

            public void setRecommend(int recommend) {
                this.recommend = recommend;
            }

            public Object getIds() {
                return ids;
            }

            public void setIds(Object ids) {
                this.ids = ids;
            }

            public int getRecom() {
                return recom;
            }

            public void setRecom(int recom) {
                this.recom = recom;
            }
        }

        public static class ContactUserBean implements Serializable{
            /**
             * imNickname : null
             * remark : null
             * imUsername : 13983251013
             * squareNum : 0
             * movingNum : 0
             * qqProvince : null
             * wxappOpenId : null
             * createTime : 2019-09-20 11:07:52
             * qqAvatarUrl : null
             * brokerAuthenticate : 0
             * wechatNickname : null
             * headUrl : https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/C6446BA6DF170315187D0C65AF7D2298.jpg
             * password : 654321
             * recruitmentNum : 0
             * wechatLanguage : null
             * qualificationAuthenticate : 0
             * qqNickname : null
             * realName : 我是3
             * phone : 13983251013
             * oldPassword : null
             * wechatAvatarUrl : null
             * wechatProvince : null
             * jobhuntingNum : 0
             * identity : 0
             * realNameAuthenticate : 0
             * wechatCountry : null
             * sex : 2
             * del : 0
             * qqOpenId : null
             * wechatCity : null
             * qqCity : null
             * name : 鞠
             * imPassword : 123456
             * state : 0
             * mail : null
             * age : 25
             * buildNum : 0
             * cumulativeLoginTime : 404216157
             * id : 3
             * lastLogin : 2019-09-29
             * enable : 0
             * wechatOpenId : null
             * qqLanguage : null
             * freeze : 0
             * collectNum : 0
             * qqCountry : null
             * recommend : 1
             * ids : null
             * recom : -1
             */

            private Object imNickname;
            private Object remark;
            private String imUsername;
            private int squareNum;
            private int movingNum;
            private Object qqProvince;
            private Object wxappOpenId;
            private String createTime;
            private Object qqAvatarUrl;
            private int brokerAuthenticate;
            private Object wechatNickname;
            private String headUrl;
            private String password;
            private int recruitmentNum;
            private Object wechatLanguage;
            private int qualificationAuthenticate;
            private Object qqNickname;
            private String realName;
            private String phone;
            private Object oldPassword;
            private Object wechatAvatarUrl;
            private Object wechatProvince;
            private int jobhuntingNum;
            private int identity;
            private int realNameAuthenticate;
            private Object wechatCountry;
            private int sex;
            private int del;
            private Object qqOpenId;
            private Object wechatCity;
            private Object qqCity;
            private String name;
            private String imPassword;
            private int state;
            private Object mail;
            private int age;
            private int buildNum;
            private long cumulativeLoginTime;
            private int id;
            private String lastLogin;
            private int enable;
            private Object wechatOpenId;
            private Object qqLanguage;
            private int freeze;
            private int collectNum;
            private Object qqCountry;
            private int recommend;
            private Object ids;
            private int recom;

            public Object getImNickname() {
                return imNickname;
            }

            public void setImNickname(Object imNickname) {
                this.imNickname = imNickname;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public String getImUsername() {
                return imUsername;
            }

            public void setImUsername(String imUsername) {
                this.imUsername = imUsername;
            }

            public int getSquareNum() {
                return squareNum;
            }

            public void setSquareNum(int squareNum) {
                this.squareNum = squareNum;
            }

            public int getMovingNum() {
                return movingNum;
            }

            public void setMovingNum(int movingNum) {
                this.movingNum = movingNum;
            }

            public Object getQqProvince() {
                return qqProvince;
            }

            public void setQqProvince(Object qqProvince) {
                this.qqProvince = qqProvince;
            }

            public Object getWxappOpenId() {
                return wxappOpenId;
            }

            public void setWxappOpenId(Object wxappOpenId) {
                this.wxappOpenId = wxappOpenId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getQqAvatarUrl() {
                return qqAvatarUrl;
            }

            public void setQqAvatarUrl(Object qqAvatarUrl) {
                this.qqAvatarUrl = qqAvatarUrl;
            }

            public int getBrokerAuthenticate() {
                return brokerAuthenticate;
            }

            public void setBrokerAuthenticate(int brokerAuthenticate) {
                this.brokerAuthenticate = brokerAuthenticate;
            }

            public Object getWechatNickname() {
                return wechatNickname;
            }

            public void setWechatNickname(Object wechatNickname) {
                this.wechatNickname = wechatNickname;
            }

            public String getHeadUrl() {
                return headUrl;
            }

            public void setHeadUrl(String headUrl) {
                this.headUrl = headUrl;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public int getRecruitmentNum() {
                return recruitmentNum;
            }

            public void setRecruitmentNum(int recruitmentNum) {
                this.recruitmentNum = recruitmentNum;
            }

            public Object getWechatLanguage() {
                return wechatLanguage;
            }

            public void setWechatLanguage(Object wechatLanguage) {
                this.wechatLanguage = wechatLanguage;
            }

            public int getQualificationAuthenticate() {
                return qualificationAuthenticate;
            }

            public void setQualificationAuthenticate(int qualificationAuthenticate) {
                this.qualificationAuthenticate = qualificationAuthenticate;
            }

            public Object getQqNickname() {
                return qqNickname;
            }

            public void setQqNickname(Object qqNickname) {
                this.qqNickname = qqNickname;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public Object getOldPassword() {
                return oldPassword;
            }

            public void setOldPassword(Object oldPassword) {
                this.oldPassword = oldPassword;
            }

            public Object getWechatAvatarUrl() {
                return wechatAvatarUrl;
            }

            public void setWechatAvatarUrl(Object wechatAvatarUrl) {
                this.wechatAvatarUrl = wechatAvatarUrl;
            }

            public Object getWechatProvince() {
                return wechatProvince;
            }

            public void setWechatProvince(Object wechatProvince) {
                this.wechatProvince = wechatProvince;
            }

            public int getJobhuntingNum() {
                return jobhuntingNum;
            }

            public void setJobhuntingNum(int jobhuntingNum) {
                this.jobhuntingNum = jobhuntingNum;
            }

            public int getIdentity() {
                return identity;
            }

            public void setIdentity(int identity) {
                this.identity = identity;
            }

            public int getRealNameAuthenticate() {
                return realNameAuthenticate;
            }

            public void setRealNameAuthenticate(int realNameAuthenticate) {
                this.realNameAuthenticate = realNameAuthenticate;
            }

            public Object getWechatCountry() {
                return wechatCountry;
            }

            public void setWechatCountry(Object wechatCountry) {
                this.wechatCountry = wechatCountry;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getDel() {
                return del;
            }

            public void setDel(int del) {
                this.del = del;
            }

            public Object getQqOpenId() {
                return qqOpenId;
            }

            public void setQqOpenId(Object qqOpenId) {
                this.qqOpenId = qqOpenId;
            }

            public Object getWechatCity() {
                return wechatCity;
            }

            public void setWechatCity(Object wechatCity) {
                this.wechatCity = wechatCity;
            }

            public Object getQqCity() {
                return qqCity;
            }

            public void setQqCity(Object qqCity) {
                this.qqCity = qqCity;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImPassword() {
                return imPassword;
            }

            public void setImPassword(String imPassword) {
                this.imPassword = imPassword;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public Object getMail() {
                return mail;
            }

            public void setMail(Object mail) {
                this.mail = mail;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public int getBuildNum() {
                return buildNum;
            }

            public void setBuildNum(int buildNum) {
                this.buildNum = buildNum;
            }

            public long getCumulativeLoginTime() {
                return cumulativeLoginTime;
            }

            public void setCumulativeLoginTime(long cumulativeLoginTime) {
                this.cumulativeLoginTime = cumulativeLoginTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLastLogin() {
                return lastLogin;
            }

            public void setLastLogin(String lastLogin) {
                this.lastLogin = lastLogin;
            }

            public int getEnable() {
                return enable;
            }

            public void setEnable(int enable) {
                this.enable = enable;
            }

            public Object getWechatOpenId() {
                return wechatOpenId;
            }

            public void setWechatOpenId(Object wechatOpenId) {
                this.wechatOpenId = wechatOpenId;
            }

            public Object getQqLanguage() {
                return qqLanguage;
            }

            public void setQqLanguage(Object qqLanguage) {
                this.qqLanguage = qqLanguage;
            }

            public int getFreeze() {
                return freeze;
            }

            public void setFreeze(int freeze) {
                this.freeze = freeze;
            }

            public int getCollectNum() {
                return collectNum;
            }

            public void setCollectNum(int collectNum) {
                this.collectNum = collectNum;
            }

            public Object getQqCountry() {
                return qqCountry;
            }

            public void setQqCountry(Object qqCountry) {
                this.qqCountry = qqCountry;
            }

            public int getRecommend() {
                return recommend;
            }

            public void setRecommend(int recommend) {
                this.recommend = recommend;
            }

            public Object getIds() {
                return ids;
            }

            public void setIds(Object ids) {
                this.ids = ids;
            }

            public int getRecom() {
                return recom;
            }

            public void setRecom(int recom) {
                this.recom = recom;
            }
        }
    }
}
