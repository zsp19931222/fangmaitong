package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/18 22:27
 * email:zsp872126510@gmail.com
 */
public class CommentBody {
    private long id = -1; // 编号
    private String otherUsername; // 被评论人名称 关联tab_tc_user的user_name
    private String remark; // 备注
    private String content; // 评论内容
    private int entityType = -1; // 评论类型 1-资讯 2-动态 3-房源
    private String createTime; // 创建时间
    private int del = -1; // 删除标志
    private long entityId = -1; // 关联tab_tc_moving的id tab_tc_news的id
    private long userId = -1; // 评论人 关联tab_tc_user的id
    private int otherUserId = -1; // 被评论人 关联tab_tc_user的id
    private String username; // 评论人名称 关联tab_tc_user的user_name
    private int state = -1; // 状态、


    /**
     * description: 评论状态
     * author: Andy
     * date: 2019/9/18  22:28
     */
    public CommentBody(String content, int entityType, long entityId, long userId, String username) {
        this.content = content;
        this.entityType = entityType;
        this.entityId = entityId;
        this.userId = userId;
        this.username = username;
    }

    /**
     * description: 评论别人的评论
     * author: Andy
     * date: 2019/9/18  22:29
     */
    public CommentBody(String content, int entityType, long entityId, int otherUserId, String otherUsername,boolean b) {
        this.content = content;
        this.entityType = entityType;
        this.entityId = entityId;
        this.otherUserId = otherUserId;
        this.otherUsername = otherUsername;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOtherUsername() {
        return otherUsername;
    }

    public void setOtherUsername(String otherUsername) {
        this.otherUsername = otherUsername;
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

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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
