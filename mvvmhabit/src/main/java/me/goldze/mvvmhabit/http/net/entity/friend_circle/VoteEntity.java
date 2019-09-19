package me.goldze.mvvmhabit.http.net.entity.friend_circle;

/**
 * description:
 * author:created by Andy on 2019/9/19 0019 15:12
 * email:zsp872126510@gmail.com
 */
public class VoteEntity {

    /**
     * id : -1
     * entityId : 10
     * remark : null
     * createTime : null
     * del : -1
     * userId : 50
     * type : 2
     * state : -1
     */

    private int id;
    private int entityId;
    private Object remark;
    private Object createTime;
    private int del;
    private int userId;
    private int type;
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
