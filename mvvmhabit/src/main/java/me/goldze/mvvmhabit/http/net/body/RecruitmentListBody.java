package me.goldze.mvvmhabit.http.net.body;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/26 0026 13:47
 * email:zsp872126510@gmail.com
 */
public class RecruitmentListBody {
    private int offset;
    private int max=10;
    private int page = 1;
    private String remark ; // 备注
    private double moneyDown = -1 ; // 薪资下限
    private String workAddress ; // 工作地址
    private String createTime ; // 创建时间
    private int topping = -1 ; // 设为推荐 0-不置顶 1-置顶
    private String recruitmentHumenAccount ; // 招聘人帐号
    private int qualificationAuthenticate = -1 ; // 资质认证 0-未认证 1-认证已通过 2-认证未通过
    private String recruitmentHumenHeadUrl ; // 招聘人头像
    private int sex = -1 ; // 1-男、2-女、0-未知
    private int del = -1 ; // 删除标志
    private String treatment ; // 福利待遇
    private int areaId = -1 ; // 区域id
    private int contactId = -1 ; // 应聘联系人 关联tab_tc_contact的id
    private int state = -1 ; // 状态 1-未审核 2-审核已通过 3-审核未通过
    private String recruitmentHumenIdentity ; // 招聘人身份
    private String recruitmentTitle ; // 招聘标题
    private String workContent ; // 工作简介
    private String position ; // 职位
    private String workYear ; // 工作年限
    private long recruitmentHumenId = -1 ; // 关联tab_tc_user的id
    private String recruitmentHumenName ; // 招聘人姓名
    private double moneyUp = -1 ; // 薪资上限
    private String workNature ; // 工作性质
    private long id = -1 ; // 编号
    private String education ; // 学历
    private int recommend = -1 ; // 设为推荐 0-不推荐 1-推荐
    private int level;
    private List<Long> ids;
    private String startTime ; // 开始时间
    private String endTime ; // 结束时间
    private String sort;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
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

    public String getRecruitmentHumenAccount() {
        return recruitmentHumenAccount;
    }

    public void setRecruitmentHumenAccount(String recruitmentHumenAccount) {
        this.recruitmentHumenAccount = recruitmentHumenAccount;
    }

    public int getQualificationAuthenticate() {
        return qualificationAuthenticate;
    }

    public void setQualificationAuthenticate(int qualificationAuthenticate) {
        this.qualificationAuthenticate = qualificationAuthenticate;
    }

    public String getRecruitmentHumenHeadUrl() {
        return recruitmentHumenHeadUrl;
    }

    public void setRecruitmentHumenHeadUrl(String recruitmentHumenHeadUrl) {
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

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRecruitmentHumenIdentity() {
        return recruitmentHumenIdentity;
    }

    public void setRecruitmentHumenIdentity(String recruitmentHumenIdentity) {
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

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public long getRecruitmentHumenId() {
        return recruitmentHumenId;
    }

    public void setRecruitmentHumenId(long recruitmentHumenId) {
        this.recruitmentHumenId = recruitmentHumenId;
    }

    public String getRecruitmentHumenName() {
        return recruitmentHumenName;
    }

    public void setRecruitmentHumenName(String recruitmentHumenName) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "RecruitmentListBody{" +
                "offset=" + offset +
                ", max=" + max +
                ", page=" + page +
                ", remark='" + remark + '\'' +
                ", moneyDown=" + moneyDown +
                ", workAddress='" + workAddress + '\'' +
                ", createTime='" + createTime + '\'' +
                ", topping=" + topping +
                ", recruitmentHumenAccount='" + recruitmentHumenAccount + '\'' +
                ", qualificationAuthenticate=" + qualificationAuthenticate +
                ", recruitmentHumenHeadUrl='" + recruitmentHumenHeadUrl + '\'' +
                ", sex=" + sex +
                ", del=" + del +
                ", treatment='" + treatment + '\'' +
                ", areaId=" + areaId +
                ", contactId=" + contactId +
                ", state=" + state +
                ", recruitmentHumenIdentity='" + recruitmentHumenIdentity + '\'' +
                ", recruitmentTitle='" + recruitmentTitle + '\'' +
                ", workContent='" + workContent + '\'' +
                ", position='" + position + '\'' +
                ", workYear='" + workYear + '\'' +
                ", recruitmentHumenId=" + recruitmentHumenId +
                ", recruitmentHumenName='" + recruitmentHumenName + '\'' +
                ", moneyUp=" + moneyUp +
                ", workNature='" + workNature + '\'' +
                ", id=" + id +
                ", education='" + education + '\'' +
                ", recommend=" + recommend +
                ", level=" + level +
                ", ids=" + ids +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }
}
