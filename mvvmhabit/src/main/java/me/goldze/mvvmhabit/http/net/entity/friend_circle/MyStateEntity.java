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
         * id : 1
         * remark : null
         * imgsUrl : http://oss.linlihouse.com/CA70C62262D2DE0B4FBC0CEE92424EDC.png,http://oss.linlihouse.com/3252FBBFC3F8A35F0DD28392983DE289.png,http://oss.linlihouse.com/D8D9AE8382C651D14E88D38613CCC0C6.png,http://oss.linlihouse.com/0032EC5E2E46C046CE58F73990533513.JPEG,http://oss.linlihouse.com/756297A18CFA36E214A376ECFA2875C2.jpg,
         * content :
         * onlyFriend : 0
         * createTime : 2019-09-17 21:37:34
         * del : 0
         * userId : 35
         * commnetNum : 0
         * state : 0
         * voteNum : 0
         * voteUser : []
         * commentList : []
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
        private List<?> voteUser;
        private List<?> commentList;

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

        public List<?> getVoteUser() {
            return voteUser;
        }

        public void setVoteUser(List<?> voteUser) {
            this.voteUser = voteUser;
        }

        public List<?> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<?> commentList) {
            this.commentList = commentList;
        }
    }
}
