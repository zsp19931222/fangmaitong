package me.goldze.mvvmhabit.http.net.entity.information;

import java.util.List;

import me.goldze.mvvmhabit.http.net.entity.BaseEntity;

/**
 * description:
 * author:created by Andy on 2019/9/25 0025 14:50
 * email:zsp872126510@gmail.com
 */
public class PlacardListEntity extends BaseEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * create_time : 2019-09-25 10:23:25
         * show_img_url : zhangjiakou.aliyuncs.com/ADB8980F52045F6033E17104C3145342.jpg
         * placard_title : 寻人启事
         * id : 1
         * look_num : 666
         */

        private String create_time;
        private String show_img_url;
        private String placard_title;
        private int id;
        private int look_num;
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getShow_img_url() {
            return show_img_url;
        }

        public void setShow_img_url(String show_img_url) {
            this.show_img_url = show_img_url;
        }

        public String getPlacard_title() {
            return placard_title;
        }

        public void setPlacard_title(String placard_title) {
            this.placard_title = placard_title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLook_num() {
            return look_num;
        }

        public void setLook_num(int look_num) {
            this.look_num = look_num;
        }
    }
}
