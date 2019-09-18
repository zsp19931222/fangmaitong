package me.goldze.mvvmhabit.http.net.entity.friend_circle;

import java.util.List;

import me.goldze.mvvmhabit.http.net.entity.BaseEntity;

/**
 * description:
 * author:created by Andy on 2019/9/17 21:47
 * email:zsp872126510@gmail.com
 */
public class MyStateEntity extends BaseEntity {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 6
         * remark : null
         * imgsUrl : https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/AA1531060DC76624D939ACE8F5C9A28B.png,https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/3A5CBB33C685D3A0DB58402836E065FB.png,
         * content : 测试
         * onlyFriend : 0
         * createTime : 2019-09-18 22:03:46
         * del : 0
         * userId : 50
         * commnetNum : 0
         * state : 0
         * voteNum : 0
         * voteUser : [{"imNickname":null,"remark":null,"imUsername":null,"squareNum":0,"movingNum":0,"qqProvince":"","wxappOpenId":null,"createTime":"2019-09-18 21:25:34","qqAvatarUrl":"http://thirdqq.qlogo.cn/g?b=oidb&k=ogYEgAfdvyZCgkicd8hc4fw&s=100&t=1555119605","brokerAuthenticate":0,"wechatNickname":null,"headUrl":"https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/20190917170958.png","password":"123456","recruitmentNum":0,"wechatLanguage":null,"qualificationAuthenticate":0,"qqNickname":"Are you kidding me、","realName":null,"phone":"13983251013","oldPassword":null,"wechatAvatarUrl":null,"wechatProvince":null,"jobhuntingNum":0,"identity":4,"realNameAuthenticate":0,"wechatCountry":null,"sex":0,"del":0,"qqOpenId":"1F255FBD192AC11A84B21606EB27E69C","wechatCity":null,"qqCity":"马德里","name":"832","imPassword":null,"state":0,"mail":null,"age":0,"buildNum":0,"cumulativeLoginTime":74673,"id":50,"lastLogin":null,"enable":0,"wechatOpenId":null,"qqLanguage":null,"freeze":0,"collectNum":0,"qqCountry":null,"jwtToken":null,"type":0}]
         * commentList : [{"id":1,"otherUsername":"0","remark":null,"content":"发送","entityType":2,"createTime":"2019-09-18 22:46:48","del":0,"entityId":6,"userId":4,"otherUserId":0,"username":"832","state":0}]
         */

        private int id;
        private Object remark;
        private String imgsUrl;
        private String content;
        private int onlyFriend;
        private String createTime;
        private int del;
        private int userId;
        private int commnetNum;
        private int state;
        private int voteNum;
        private List<VoteUserBean> voteUser;
        private List<CommentListBean> commentList;

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

        public String getImgsUrl() {
            return imgsUrl;
        }

        public void setImgsUrl(String imgsUrl) {
            this.imgsUrl = imgsUrl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getOnlyFriend() {
            return onlyFriend;
        }

        public void setOnlyFriend(int onlyFriend) {
            this.onlyFriend = onlyFriend;
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

        public int getCommnetNum() {
            return commnetNum;
        }

        public void setCommnetNum(int commnetNum) {
            this.commnetNum = commnetNum;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getVoteNum() {
            return voteNum;
        }

        public void setVoteNum(int voteNum) {
            this.voteNum = voteNum;
        }

        public List<VoteUserBean> getVoteUser() {
            return voteUser;
        }

        public void setVoteUser(List<VoteUserBean> voteUser) {
            this.voteUser = voteUser;
        }

        public List<CommentListBean> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<CommentListBean> commentList) {
            this.commentList = commentList;
        }

        public static class VoteUserBean {
            /**
             * imNickname : null
             * remark : null
             * imUsername : null
             * squareNum : 0
             * movingNum : 0
             * qqProvince :
             * wxappOpenId : null
             * createTime : 2019-09-18 21:25:34
             * qqAvatarUrl : http://thirdqq.qlogo.cn/g?b=oidb&k=ogYEgAfdvyZCgkicd8hc4fw&s=100&t=1555119605
             * brokerAuthenticate : 0
             * wechatNickname : null
             * headUrl : https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/20190917170958.png
             * password : 123456
             * recruitmentNum : 0
             * wechatLanguage : null
             * qualificationAuthenticate : 0
             * qqNickname : Are you kidding me、
             * realName : null
             * phone : 13983251013
             * oldPassword : null
             * wechatAvatarUrl : null
             * wechatProvince : null
             * jobhuntingNum : 0
             * identity : 4
             * realNameAuthenticate : 0
             * wechatCountry : null
             * sex : 0
             * del : 0
             * qqOpenId : 1F255FBD192AC11A84B21606EB27E69C
             * wechatCity : null
             * qqCity : 马德里
             * name : 832
             * imPassword : null
             * state : 0
             * mail : null
             * age : 0
             * buildNum : 0
             * cumulativeLoginTime : 74673
             * id : 50
             * lastLogin : null
             * enable : 0
             * wechatOpenId : null
             * qqLanguage : null
             * freeze : 0
             * collectNum : 0
             * qqCountry : null
             * jwtToken : null
             * type : 0
             */

            private Object imNickname;
            private Object remark;
            private Object imUsername;
            private int squareNum;
            private int movingNum;
            private String qqProvince;
            private Object wxappOpenId;
            private String createTime;
            private String qqAvatarUrl;
            private int brokerAuthenticate;
            private Object wechatNickname;
            private String headUrl;
            private String password;
            private int recruitmentNum;
            private Object wechatLanguage;
            private int qualificationAuthenticate;
            private String qqNickname;
            private Object realName;
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
            private String qqOpenId;
            private Object wechatCity;
            private String qqCity;
            private String name;
            private Object imPassword;
            private int state;
            private Object mail;
            private int age;
            private int buildNum;
            private int cumulativeLoginTime;
            private int id;
            private Object lastLogin;
            private int enable;
            private Object wechatOpenId;
            private Object qqLanguage;
            private int freeze;
            private int collectNum;
            private Object qqCountry;
            private Object jwtToken;
            private int type;

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

            public Object getImUsername() {
                return imUsername;
            }

            public void setImUsername(Object imUsername) {
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

            public String getQqProvince() {
                return qqProvince;
            }

            public void setQqProvince(String qqProvince) {
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

            public String getQqAvatarUrl() {
                return qqAvatarUrl;
            }

            public void setQqAvatarUrl(String qqAvatarUrl) {
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

            public String getQqNickname() {
                return qqNickname;
            }

            public void setQqNickname(String qqNickname) {
                this.qqNickname = qqNickname;
            }

            public Object getRealName() {
                return realName;
            }

            public void setRealName(Object realName) {
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

            public String getQqOpenId() {
                return qqOpenId;
            }

            public void setQqOpenId(String qqOpenId) {
                this.qqOpenId = qqOpenId;
            }

            public Object getWechatCity() {
                return wechatCity;
            }

            public void setWechatCity(Object wechatCity) {
                this.wechatCity = wechatCity;
            }

            public String getQqCity() {
                return qqCity;
            }

            public void setQqCity(String qqCity) {
                this.qqCity = qqCity;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getImPassword() {
                return imPassword;
            }

            public void setImPassword(Object imPassword) {
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

            public Object getLastLogin() {
                return lastLogin;
            }

            public void setLastLogin(Object lastLogin) {
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
        }

        public static class CommentListBean {
            /**
             * id : 1
             * otherUsername : 0
             * remark : null
             * content : 发送
             * entityType : 2
             * createTime : 2019-09-18 22:46:48
             * del : 0
             * entityId : 6
             * userId : 4
             * otherUserId : 0
             * username : 832
             * state : 0
             */

            private int id;
            private String otherUsername;
            private Object remark;
            private String content;
            private int entityType;
            private String createTime;
            private int del;
            private int entityId;
            private int userId;
            private int otherUserId;
            private String username;
            private int state;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOtherUsername() {
                return otherUsername;
            }

            public void setOtherUsername(String otherUsername) {
                this.otherUsername = otherUsername;
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

            public int getEntityType() {
                return entityType;
            }

            public void setEntityType(int entityType) {
                this.entityType = entityType;
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

            public int getEntityId() {
                return entityId;
            }

            public void setEntityId(int entityId) {
                this.entityId = entityId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getOtherUserId() {
                return otherUserId;
            }

            public void setOtherUserId(int otherUserId) {
                this.otherUserId = otherUserId;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }
        }
    }
}
