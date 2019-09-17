package me.goldze.mvvmhabit.http.net.entity.login;

import org.litepal.crud.LitePalSupport;

/**
 * description:
 * author:created by Andy on 2019/9/17 0017 10:52
 * email:zsp872126510@gmail.com
 */
public class RegisterEntity extends LitePalSupport {


    /**
     * imNickname : null
     * remark : null
     * imUsername : null
     * squareNum : 0
     * movingNum : 0
     * qqProvince : null
     * wxappOpenId : null
     * createTime : 2019-09-17 14:54:27
     * qqAvatarUrl : null
     * brokerAuthenticate : 0
     * wechatNickname : null
     * headUrl : null
     * password : 123456
     * recruitmentNum : 0
     * wechatLanguage : null
     * qualificationAuthenticate : 0
     * qqNickname : null
     * realName : null
     * phone : 13983251013
     * oldPassword : null
     * wechatAvatarUrl : null
     * wechatProvince : null
     * jobhuntingNum : 0
     * identity : 4
     * realNameAuthenticate : 0
     * wechatCountry : null
     * sex : 1
     * del : 0
     * qqOpenId : null
     * wechatCity : null
     * qqCity : null
     * name : 测试
     * imPassword : null
     * state : 0
     * mail : null
     * age : 3
     * buildNum : 0
     * cumulativeLoginTime : 26030
     * id : 35
     * lastLogin : null
     * enable : 0
     * wechatOpenId : null
     * qqLanguage : null
     * freeze : 0
     * collectNum : 0
     * qqCountry : null
     * jwtToken : {"token":"bearer;eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiIzNSIsImlzcyI6ImZhbmdtYWl0b25nIiwiYXVkIjoiNDg1NWJjNjQ0MThjMTdjYWFjMGViMTZlNmY3MmMxOTNkM2RiNjQ0NiIsImV4cCI6MTU3MTMwMTAwOSwibmJmIjoxNTY4NzA5MDA5fQ.sTbqyNfiwNzig715tW6ByxOhpENsezrGPfkjdkOmCDk","expireTime":1571301009765}
     * type : 0
     */

    private String imNickname ; // 环信昵称
    private String remark ; // 备注
    private String imUsername ; // 环信id
    private int squareNum = -1 ; // 广场发布数
    private int movingNum = -1 ; // 动态发布数
    private String qqProvince ; // QQ省
    private String wxappOpenId ; // 微信小程序openid
    private String createTime ; // 创建时间
    private String qqAvatarUrl ; // QQ头像url
    private int brokerAuthenticate = -1 ; // 经纪人认证 0-未认证 1-认证已通过 2-认证未通过
    private String wechatNickname ; // 微信昵称
    private String headUrl ; // 房脉通头像url
    private String password ; // 密码
    private int recruitmentNum = -1 ; // 招聘发布数
    private String wechatLanguage ; // 微信语言
    private int qualificationAuthenticate = -1 ; // 资质认证 0-未认证 1-认证已通过 2-认证未通过
    private String qqNickname ; // QQ昵称
    private String realName ; // 真实姓名
    private String phone ; // 手机号
    private String oldPassword ; // 旧密码
    private String wechatAvatarUrl ; // 微信头像url
    private String wechatProvince ; // 微信省
    private int jobhuntingNum = -1 ; // 求职发布数
    private int identity = -1 ; // 身份 1-总代 2-渠道代理 3-联合代理 4-经纪人
    private int realNameAuthenticate = -1 ; // 实名认证 0-未认证 1-认证已通过 2-认证未通过
    private String wechatCountry ; // 微信国家
    private int sex = -1 ; // 1-男、2-女、0-未知
    private int del = -1 ; // 删除标志
    private String qqOpenId ; // QQopenid
    private String wechatCity ; // 微信城市
    private String qqCity ; // QQ城市
    private String name ; // 房脉通昵称
    private String imPassword ; // 环信密码
    private int state = -1 ; // 状态 1-禁言 0-不禁言
    private String mail ; // 邮箱
    private int age = -1 ; // 年龄
    private int buildNum = -1 ; // 楼盘发布数
    private long cumulativeLoginTime = -1 ; // 累计登录时间
    private long id = -1 ; // 编号
    private String lastLogin ; // 上次登录时间
    private int enable = -1 ; // 注销帐号 0-未注销 1-已注销
    private String wechatOpenId ; // 微信openid
    private String qqLanguage ; // QQ语言
    private int freeze = -1 ; // 冻结帐号 0-未冻结 1-已冻结
    private int collectNum = -1 ; // 收藏数
    private String qqCountry ; // QQ国家
    private int type;//1-手机 2-邮箱 3-微信 4-QQ
    private JwtTokenBean jwtToken;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImNickname() {
        return imNickname;
    }

    public void setImNickname(String imNickname) {
        this.imNickname = imNickname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
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

    public String getQqProvince() {
        return qqProvince;
    }

    public void setQqProvince(String qqProvince) {
        this.qqProvince = qqProvince;
    }

    public String getWxappOpenId() {
        return wxappOpenId;
    }

    public void setWxappOpenId(String wxappOpenId) {
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

    public String getWechatNickname() {
        return wechatNickname;
    }

    public void setWechatNickname(String wechatNickname) {
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

    public String getWechatLanguage() {
        return wechatLanguage;
    }

    public void setWechatLanguage(String wechatLanguage) {
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getWechatAvatarUrl() {
        return wechatAvatarUrl;
    }

    public void setWechatAvatarUrl(String wechatAvatarUrl) {
        this.wechatAvatarUrl = wechatAvatarUrl;
    }

    public String getWechatProvince() {
        return wechatProvince;
    }

    public void setWechatProvince(String wechatProvince) {
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

    public String getWechatCountry() {
        return wechatCountry;
    }

    public void setWechatCountry(String wechatCountry) {
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

    public String getWechatCity() {
        return wechatCity;
    }

    public void setWechatCity(String wechatCity) {
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getWechatOpenId() {
        return wechatOpenId;
    }

    public void setWechatOpenId(String wechatOpenId) {
        this.wechatOpenId = wechatOpenId;
    }

    public String getQqLanguage() {
        return qqLanguage;
    }

    public void setQqLanguage(String qqLanguage) {
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

    public String getQqCountry() {
        return qqCountry;
    }

    public void setQqCountry(String qqCountry) {
        this.qqCountry = qqCountry;
    }

    public JwtTokenBean getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(JwtTokenBean jwtToken) {
        this.jwtToken = jwtToken;
    }

    public static class JwtTokenBean {
        /**
         * token : bearer;eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiIzNSIsImlzcyI6ImZhbmdtYWl0b25nIiwiYXVkIjoiNDg1NWJjNjQ0MThjMTdjYWFjMGViMTZlNmY3MmMxOTNkM2RiNjQ0NiIsImV4cCI6MTU3MTMwMTAwOSwibmJmIjoxNTY4NzA5MDA5fQ.sTbqyNfiwNzig715tW6ByxOhpENsezrGPfkjdkOmCDk
         * expireTime : 1571301009765
         */

        private String token;
        private long expireTime;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public long getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(long expireTime) {
            this.expireTime = expireTime;
        }
    }
}
