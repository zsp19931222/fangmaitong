package me.goldze.mvvmhabit.http.net.entity;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/10/10 0010 10:48
 * email:zsp872126510@gmail.com
 */
public class ReviewListEntity extends BaseEntity{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * remark : null
         * content : 补不到
         * createTime : 2019-10-10 10:47:11
         * del : 0
         * userId : 3
         * entityId : 39
         * lotStar : 4
         * trafficStar : 2
         * matchingStar : 2
         * state : 0
         * priceStar : 3
         * user : {"movingNum":0,"qqAvatarUrl":null,"wechatNickname":null,"password":"654321","wechatLanguage":null,"realName":"我是3","oldPassword":null,"wechatProvince":null,"jobhuntingNum":0,"realNameAuthenticate":0,"wechatCountry":null,"sex":2,"imPassword":"123456","state":0,"wxappCountry":null,"id":3,"lastLogin":"2019-10-09","qqLanguage":null,"freeze":0,"qqCountry":null,"imNickname":null,"remark":null,"imUsername":"13983251013","squareNum":0,"wxappAvatarUrl":null,"wxappCity":null,"qqProvince":null,"wxappOpenId":null,"createTime":"2019-09-20 11:07:52","brokerAuthenticate":0,"headUrl":"https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/C6446BA6DF170315187D0C65AF7D2298.jpg","recruitmentNum":0,"qualificationAuthenticate":0,"qqNickname":null,"phone":"13983251013","wechatAvatarUrl":null,"wxappLanguage":null,"identity":0,"del":0,"qqOpenId":null,"wechatCity":null,"qqCity":null,"name":"鞠","mail":null,"age":25,"buildNum":0,"cumulativeLoginTime":767400416,"wxappProvince":null,"enable":0,"wechatOpenId":null,"wxappNickname":null,"collectNum":0,"jwtToken":null,"type":0,"recommend":1,"unionid":null,"location":null}
         */

        private int id;
        private Object remark;
        private String content;
        private String createTime;
        private int del;
        private int userId;
        private int entityId;
        private String lotStar;
        private String trafficStar;
        private String matchingStar;
        private int state;
        private String priceStar;
        private UserBean user;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDel() {
            return del;
        }

        public void setDel(int del) {
            this.del = del;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getEntityId() {
            return entityId;
        }

        public void setEntityId(int entityId) {
            this.entityId = entityId;
        }

        public String getLotStar() {
            return lotStar;
        }

        public void setLotStar(String lotStar) {
            this.lotStar = lotStar;
        }

        public String getTrafficStar() {
            return trafficStar;
        }

        public void setTrafficStar(String trafficStar) {
            this.trafficStar = trafficStar;
        }

        public String getMatchingStar() {
            return matchingStar;
        }

        public void setMatchingStar(String matchingStar) {
            this.matchingStar = matchingStar;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getPriceStar() {
            return priceStar;
        }

        public void setPriceStar(String priceStar) {
            this.priceStar = priceStar;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * movingNum : 0
             * qqAvatarUrl : null
             * wechatNickname : null
             * password : 654321
             * wechatLanguage : null
             * realName : 我是3
             * oldPassword : null
             * wechatProvince : null
             * jobhuntingNum : 0
             * realNameAuthenticate : 0
             * wechatCountry : null
             * sex : 2
             * imPassword : 123456
             * state : 0
             * wxappCountry : null
             * id : 3
             * lastLogin : 2019-10-09
             * qqLanguage : null
             * freeze : 0
             * qqCountry : null
             * imNickname : null
             * remark : null
             * imUsername : 13983251013
             * squareNum : 0
             * wxappAvatarUrl : null
             * wxappCity : null
             * qqProvince : null
             * wxappOpenId : null
             * createTime : 2019-09-20 11:07:52
             * brokerAuthenticate : 0
             * headUrl : https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/C6446BA6DF170315187D0C65AF7D2298.jpg
             * recruitmentNum : 0
             * qualificationAuthenticate : 0
             * qqNickname : null
             * phone : 13983251013
             * wechatAvatarUrl : null
             * wxappLanguage : null
             * identity : 0
             * del : 0
             * qqOpenId : null
             * wechatCity : null
             * qqCity : null
             * name : 鞠
             * mail : null
             * age : 25
             * buildNum : 0
             * cumulativeLoginTime : 767400416
             * wxappProvince : null
             * enable : 0
             * wechatOpenId : null
             * wxappNickname : null
             * collectNum : 0
             * jwtToken : null
             * type : 0
             * recommend : 1
             * unionid : null
             * location : null
             */

            private int movingNum;
            private Object qqAvatarUrl;
            private Object wechatNickname;
            private String password;
            private Object wechatLanguage;
            private String realName;
            private Object oldPassword;
            private Object wechatProvince;
            private int jobhuntingNum;
            private int realNameAuthenticate;
            private Object wechatCountry;
            private int sex;
            private String imPassword;
            private int state;
            private Object wxappCountry;
            private int id;
            private String lastLogin;
            private Object qqLanguage;
            private int freeze;
            private Object qqCountry;
            private Object imNickname;
            private Object remark;
            private String imUsername;
            private int squareNum;
            private Object wxappAvatarUrl;
            private Object wxappCity;
            private Object qqProvince;
            private Object wxappOpenId;
            private String createTime;
            private int brokerAuthenticate;
            private String headUrl;
            private int recruitmentNum;
            private int qualificationAuthenticate;
            private Object qqNickname;
            private String phone;
            private Object wechatAvatarUrl;
            private Object wxappLanguage;
            private int identity;
            private int del;
            private Object qqOpenId;
            private Object wechatCity;
            private Object qqCity;
            private String name;
            private Object mail;
            private int age;
            private int buildNum;
            private int cumulativeLoginTime;
            private Object wxappProvince;
            private int enable;
            private Object wechatOpenId;
            private Object wxappNickname;
            private int collectNum;
            private Object jwtToken;
            private int type;
            private int recommend;
            private Object unionid;
            private Object location;

            public int getMovingNum() {
                return movingNum;
            }

            public void setMovingNum(int movingNum) {
                this.movingNum = movingNum;
            }

            public Object getQqAvatarUrl() {
                return qqAvatarUrl;
            }

            public void setQqAvatarUrl(Object qqAvatarUrl) {
                this.qqAvatarUrl = qqAvatarUrl;
            }

            public Object getWechatNickname() {
                return wechatNickname;
            }

            public void setWechatNickname(Object wechatNickname) {
                this.wechatNickname = wechatNickname;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public Object getWechatLanguage() {
                return wechatLanguage;
            }

            public void setWechatLanguage(Object wechatLanguage) {
                this.wechatLanguage = wechatLanguage;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public Object getOldPassword() {
                return oldPassword;
            }

            public void setOldPassword(Object oldPassword) {
                this.oldPassword = oldPassword;
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

            public Object getWxappCountry() {
                return wxappCountry;
            }

            public void setWxappCountry(Object wxappCountry) {
                this.wxappCountry = wxappCountry;
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

            public Object getQqCountry() {
                return qqCountry;
            }

            public void setQqCountry(Object qqCountry) {
                this.qqCountry = qqCountry;
            }

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

            public Object getWxappAvatarUrl() {
                return wxappAvatarUrl;
            }

            public void setWxappAvatarUrl(Object wxappAvatarUrl) {
                this.wxappAvatarUrl = wxappAvatarUrl;
            }

            public Object getWxappCity() {
                return wxappCity;
            }

            public void setWxappCity(Object wxappCity) {
                this.wxappCity = wxappCity;
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

            public int getBrokerAuthenticate() {
                return brokerAuthenticate;
            }

            public void setBrokerAuthenticate(int brokerAuthenticate) {
                this.brokerAuthenticate = brokerAuthenticate;
            }

            public String getHeadUrl() {
                return headUrl;
            }

            public void setHeadUrl(String headUrl) {
                this.headUrl = headUrl;
            }

            public int getRecruitmentNum() {
                return recruitmentNum;
            }

            public void setRecruitmentNum(int recruitmentNum) {
                this.recruitmentNum = recruitmentNum;
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

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public Object getWechatAvatarUrl() {
                return wechatAvatarUrl;
            }

            public void setWechatAvatarUrl(Object wechatAvatarUrl) {
                this.wechatAvatarUrl = wechatAvatarUrl;
            }

            public Object getWxappLanguage() {
                return wxappLanguage;
            }

            public void setWxappLanguage(Object wxappLanguage) {
                this.wxappLanguage = wxappLanguage;
            }

            public int getIdentity() {
                return identity;
            }

            public void setIdentity(int identity) {
                this.identity = identity;
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

            public Object getWxappProvince() {
                return wxappProvince;
            }

            public void setWxappProvince(Object wxappProvince) {
                this.wxappProvince = wxappProvince;
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

            public Object getWxappNickname() {
                return wxappNickname;
            }

            public void setWxappNickname(Object wxappNickname) {
                this.wxappNickname = wxappNickname;
            }

            public int getCollectNum() {
                return collectNum;
            }

            public void setCollectNum(int collectNum) {
                this.collectNum = collectNum;
            }

            public Object getJwtToken() {
                return jwtToken;
            }

            public void setJwtToken(Object jwtToken) {
                this.jwtToken = jwtToken;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getRecommend() {
                return recommend;
            }

            public void setRecommend(int recommend) {
                this.recommend = recommend;
            }

            public Object getUnionid() {
                return unionid;
            }

            public void setUnionid(Object unionid) {
                this.unionid = unionid;
            }

            public Object getLocation() {
                return location;
            }

            public void setLocation(Object location) {
                this.location = location;
            }
        }
    }
}
