package me.goldze.mvvmhabit.http.net.entity.friend_circle;

/**
 * description:
 * author:created by Andy on 2019/9/19 0019 09:59
 * email:zsp872126510@gmail.com
 */
public class CommentBean {

    /**
     * id : 11
     * otherUsername : 梦佳姐
     * remark : null
     * content : 回复梦佳
     * entityType : 2
     * createTime : null
     * del : -1
     * entityId : 6
     * userId : 50
     * otherUserId : 1
     * username : 832
     * state : -1
     */

    private int id;
    private String otherUsername;
    private Object remark;
    private String content;
    private int entityType;
    private Object createTime;
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

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
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
