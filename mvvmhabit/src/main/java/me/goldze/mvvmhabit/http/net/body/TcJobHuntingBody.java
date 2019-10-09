package me.goldze.mvvmhabit.http.net.body;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/10/9 22:11
 * email:zsp872126510@gmail.com
 */
public class TcJobHuntingBody {
    private String remark; // 备注
    private String workContent; // 工作简介
    private String lon; // 经度
    private String workAddress; // 工作地址
    private String workYear; // 工作年限
    private String createTime; // 创建时间
    private String huntingHumenName; // 求职人姓名
    private long huntingHumenId = -1; // 关联tab_tc_user的id
    private String huntingHumenHeadUrl; // 求职人头像
    private String workNature; // 工作性质
    private long id = -1; // 编号
    private int qualificationAuthenticate = -1; // 资质认证 0-未认证 1-认证已通过 2-认证未通过
    private String huntingHumenIdentity; // 求职人身份
    private String huntingHumenAccount; // 求职人帐号
    private int sex = -1; // 1-男、2-女、0-未知
    private int del = -1; // 删除标志
    private String huntingTitle; // 求职标题
    private int contactId = -1; // 应聘联系人 关联tab_tc_contact的id
    private int state = -1; // 状态 1-未审核 2-审核已通过 3-审核未通过
    private String lat; // 纬度
    private String district; // 区
    private String province; // 省
    private String city; // 市

    private List<Long> ids;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
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

    public long getHuntingHumenId() {
        return huntingHumenId;
    }

    public void setHuntingHumenId(long huntingHumenId) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "TcJobHuntingBody{" +
                "remark='" + remark + '\'' +
                ", workContent='" + workContent + '\'' +
                ", lon='" + lon + '\'' +
                ", workAddress='" + workAddress + '\'' +
                ", workYear='" + workYear + '\'' +
                ", createTime='" + createTime + '\'' +
                ", huntingHumenName='" + huntingHumenName + '\'' +
                ", huntingHumenId=" + huntingHumenId +
                ", huntingHumenHeadUrl='" + huntingHumenHeadUrl + '\'' +
                ", workNature='" + workNature + '\'' +
                ", id=" + id +
                ", qualificationAuthenticate=" + qualificationAuthenticate +
                ", huntingHumenIdentity='" + huntingHumenIdentity + '\'' +
                ", huntingHumenAccount='" + huntingHumenAccount + '\'' +
                ", sex=" + sex +
                ", del=" + del +
                ", huntingTitle='" + huntingTitle + '\'' +
                ", contactId=" + contactId +
                ", state=" + state +
                ", lat='" + lat + '\'' +
                ", district='" + district + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
