package me.goldze.mvvmhabit.http.net.entity;

import java.util.List;

import me.goldze.mvvmhabit.http.net.entity.friend_circle.MyStateEntity;

/**
 * description:
 * author:created by Andy on 2019/9/28 22:06
 * email:zsp872126510@gmail.com
 */
public class AppReportListEntity extends BaseEntity{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * remark : 有毒这个
         * content : 此账号可能被盗用了
         * listingName : 楼盘
         * createTime : 2019-09-28 22:05:09
         * del : 0
         * userId : 3
         * listingId : 39
         * state : 0
         * commentList : []
         * reason : null
         * reasonType : -1
         */

        private int id;
        private String remark;
        private String content;
        private String listingName;
        private String createTime;
        private int del;
        private int userId;
        private int listingId;
        private int state;
        private Object reason;
        private int reasonType;
        private List<MyStateEntity.DataBean.CommentListBean> commentList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public String getListingName() {
            return listingName;
        }

        public void setListingName(String listingName) {
            this.listingName = listingName;
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

        public int getListingId() {
            return listingId;
        }

        public void setListingId(int listingId) {
            this.listingId = listingId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public Object getReason() {
            return reason;
        }

        public void setReason(Object reason) {
            this.reason = reason;
        }

        public int getReasonType() {
            return reasonType;
        }

        public void setReasonType(int reasonType) {
            this.reasonType = reasonType;
        }

        public List<?> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<MyStateEntity.DataBean.CommentListBean> commentList) {
            this.commentList = commentList;
        }
    }
}
