package me.goldze.mvvmhabit.http.net.entity;

import java.io.Serializable;
import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/26 23:16
 * email:zsp872126510@gmail.com
 */
public class RecruitmentListEntity extends BaseEntity{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * remark : null
         * moneyDown : 2000.0
         * workAddress : 河北省保定市高碑店市
         * createTime : 2019-09-26 23:14:27
         * topping : -1
         * recruitmentHumenAccount : null
         * qualificationAuthenticate : 0
         * recruitmentHumenHeadUrl : null
         * sex : 0
         * del : 0
         * treatment : null
         * areaId : -1
         * contactId : -1
         * contactIds : null
         * state : 0
         * recruitmentHumenIdentity : null
         * recruitmentTitle : 标题
         * workContent : 没有公司简介
         * position : 经理
         * workYear : null
         * recruitmentHumenId : 1
         * recruitmentHumenName : null
         * moneyUp : 3000.0
         * workNature : 兼职
         * id : 5
         * education : 本科
         * recommend : -1
         * user : {"imNickname":null,"remark":null,"imUsername":"18702305506","squareNum":0,"movingNum":0,"qqProvince":null,"wxappOpenId":null,"createTime":"2019-09-20 10:40:07","qqAvatarUrl":null,"brokerAuthenticate":0,"wechatNickname":null,"headUrl":"https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/64DB72530CE70259B6499B8C792B21CB.jpg","password":"123456","recruitmentNum":0,"wechatLanguage":null,"qualificationAuthenticate":0,"qqNickname":null,"realName":"我是1","phone":"18702305506","oldPassword":null,"wechatAvatarUrl":null,"wechatProvince":null,"jobhuntingNum":0,"identity":0,"realNameAuthenticate":0,"wechatCountry":null,"sex":2,"del":0,"qqOpenId":null,"wechatCity":null,"qqCity":null,"name":"look","imPassword":"123456","state":0,"mail":null,"age":0,"buildNum":0,"cumulativeLoginTime":6242260,"id":1,"lastLogin":"2019-09-26","enable":0,"wechatOpenId":null,"qqLanguage":null,"freeze":0,"collectNum":0,"qqCountry":null,"recommend":-1,"ids":null,"recom":-1}
         * contactUser : null
         * ids : null
         * district : null
         * province : null
         * city : null
         */

        private Object remark;
        private double moneyDown;
        private String workAddress;
        private String createTime;
        private int topping;
        private Object recruitmentHumenAccount;
        private int qualificationAuthenticate;
        private Object recruitmentHumenHeadUrl;
        private int sex;
        private int del;
        private Object treatment;
        private int areaId;
        private int contactId;
        private Object contactIds;
        private int state;
        private Object recruitmentHumenIdentity;
        private String recruitmentTitle;
        private String workContent;
        private String position;
        private Object workYear;
        private int recruitmentHumenId;
        private Object recruitmentHumenName;
        private double moneyUp;
        private String workNature;
        private int id;
        private String education;
        private int recommend;
        private UserBean user;
        private Object contactUser;
        private Object ids;
        private Object district;
        private Object province;
        private Object city;

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public double getMoneyDown() {
            return moneyDown;
        }

        public void setMoneyDown(double moneyDown) {
            this.moneyDown = moneyDown;
        }

        public String getWorkAddress() {
            return workAddress;
        }

        public void setWorkAddress(String workAddress) {
            this.workAddress = workAddress;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getTopping() {
            return topping;
        }

        public void setTopping(int topping) {
            this.topping = topping;
        }

        public Object getRecruitmentHumenAccount() {
            return recruitmentHumenAccount;
        }

        public void setRecruitmentHumenAccount(Object recruitmentHumenAccount) {
            this.recruitmentHumenAccount = recruitmentHumenAccount;
        }

        public int getQualificationAuthenticate() {
            return qualificationAuthenticate;
        }

        public void setQualificationAuthenticate(int qualificationAuthenticate) {
            this.qualificationAuthenticate = qualificationAuthenticate;
        }

        public Object getRecruitmentHumenHeadUrl() {
            return recruitmentHumenHeadUrl;
        }

        public void setRecruitmentHumenHeadUrl(Object recruitmentHumenHeadUrl) {
            this.recruitmentHumenHeadUrl = recruitmentHumenHeadUrl;
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

        public Object getTreatment() {
            return treatment;
        }

        public void setTreatment(Object treatment) {
            this.treatment = treatment;
        }

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public int getContactId() {
            return contactId;
        }

        public void setContactId(int contactId) {
            this.contactId = contactId;
        }

        public Object getContactIds() {
            return contactIds;
        }

        public void setContactIds(Object contactIds) {
            this.contactIds = contactIds;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public Object getRecruitmentHumenIdentity() {
            return recruitmentHumenIdentity;
        }

        public void setRecruitmentHumenIdentity(Object recruitmentHumenIdentity) {
            this.recruitmentHumenIdentity = recruitmentHumenIdentity;
        }

        public String getRecruitmentTitle() {
            return recruitmentTitle;
        }

        public void setRecruitmentTitle(String recruitmentTitle) {
            this.recruitmentTitle = recruitmentTitle;
        }

        public String getWorkContent() {
            return workContent;
        }

        public void setWorkContent(String workContent) {
            this.workContent = workContent;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public Object getWorkYear() {
            return workYear;
        }

        public void setWorkYear(Object workYear) {
            this.workYear = workYear;
        }

        public int getRecruitmentHumenId() {
            return recruitmentHumenId;
        }

        public void setRecruitmentHumenId(int recruitmentHumenId) {
            this.recruitmentHumenId = recruitmentHumenId;
        }

        public Object getRecruitmentHumenName() {
            return recruitmentHumenName;
        }

        public void setRecruitmentHumenName(Object recruitmentHumenName) {
            this.recruitmentHumenName = recruitmentHumenName;
        }

        public double getMoneyUp() {
            return moneyUp;
        }

        public void setMoneyUp(double moneyUp) {
            this.moneyUp = moneyUp;
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

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public int getRecommend() {
            return recommend;
        }

        public void setRecommend(int recommend) {
            this.recommend = recommend;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public Object getContactUser() {
            return contactUser;
        }

        public void setContactUser(Object contactUser) {
            this.contactUser = contactUser;
        }

        public Object getIds() {
            return ids;
        }

        public void setIds(Object ids) {
            this.ids = ids;
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

        public static class UserBean {
            /**
             * imNickname : null
             * remark : null
             * imUsername : 18702305506
             * squareNum : 0
             * movingNum : 0
             * qqProvince : null
             * wxappOpenId : null
             * createTime : 2019-09-20 10:40:07
             * qqAvatarUrl : null
             * brokerAuthenticate : 0
             * wechatNickname : null
             * headUrl : https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/64DB72530CE70259B6499B8C792B21CB.jpg
             * password : 123456
             * recruitmentNum : 0
             * wechatLanguage : null
             * qualificationAuthenticate : 0
             * qqNickname : null
             * realName : 我是1
             * phone : 18702305506
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
             * name : look
             * imPassword : 123456
             * state : 0
             * mail : null
             * age : 0
             * buildNum : 0
             * cumulativeLoginTime : 6242260
             * id : 1
             * lastLogin : 2019-09-26
             * enable : 0
             * wechatOpenId : null
             * qqLanguage : null
             * freeze : 0
             * collectNum : 0
             * qqCountry : null
             * recommend : -1
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
            private int cumulativeLoginTime;
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

            public int getCumulativeLoginTime() {
                return cumulativeLoginTime;
            }

            public void setCumulativeLoginTime(int cumulativeLoginTime) {
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
