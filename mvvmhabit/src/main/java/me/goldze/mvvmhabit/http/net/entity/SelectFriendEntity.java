package me.goldze.mvvmhabit.http.net.entity;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/23 19:57
 * email:zsp872126510@gmail.com
 */
public class SelectFriendEntity extends BaseEntity{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * head_url : https://fangmaitong01.oss-cn-zhangjiakou.aliyuncs.com/64DB72530CE70259B6499B8C792B21CB.jpg
         * phone : 18702305506
         * real_name : 我是1
         * id : 1
         */

        private String head_url;
        private String phone;
        private String real_name;
        private int id;

        public String getHead_url() {
            return head_url;
        }

        public void setHead_url(String head_url) {
            this.head_url = head_url;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
