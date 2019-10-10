package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/10/10 0010 10:22
 * email:zsp872126510@gmail.com
 */
public class TcReviewBody {
    private long id = -1 ; // 编号
    private String remark ; // 备注
    private String content ; // 点评内容
    private String createTime ; // 创建时间
    private int del = -1 ; // 删除标志
    private long userId = -1 ; // 用户id
    private long entityId = -1 ; // 点评对象id
    private String lotStar ; // 地段评分
    private String trafficStar ; // 交通评分
    private String matchingStar ; // 配套评分
    private int state = -1 ; // 状态
    private String priceStar ; // 价格评分

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
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
}
