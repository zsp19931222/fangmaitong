package me.goldze.mvvmhabit.http.net.entity.information;

import java.util.List;

import me.goldze.mvvmhabit.http.net.entity.BaseEntity;

/**
 * description:
 * author:created by Andy on 2019/9/25 0025 16:08
 * email:zsp872126510@gmail.com
 */
public class NewsListEntity extends BaseEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * comment_num : 666
         * like_num : 666
         * id : 1
         * news_title : 测试资讯
         * look_num : 666
         * label : ["海外资讯","新手介绍"]
         */

        private int comment_num;
        private int like_num;
        private int id;
        private String news_title;
        private int look_num;
        private List<String> label;
        private int topping;
        private String news_img;
        private String content;
        private String type;
        private String create_time;

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getNews_img() {
            return news_img;
        }

        public void setNews_img(String news_img) {
            this.news_img = news_img;
        }

        public int getTopping() {
            return topping;
        }

        public void setTopping(int topping) {
            this.topping = topping;
        }

        public int getComment_num() {
            return comment_num;
        }

        public void setComment_num(int comment_num) {
            this.comment_num = comment_num;
        }

        public int getLike_num() {
            return like_num;
        }

        public void setLike_num(int like_num) {
            this.like_num = like_num;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNews_title() {
            return news_title;
        }

        public void setNews_title(String news_title) {
            this.news_title = news_title;
        }

        public int getLook_num() {
            return look_num;
        }

        public void setLook_num(int look_num) {
            this.look_num = look_num;
        }

        public List<String> getLabel() {
            return label;
        }

        public void setLabel(List<String> label) {
            this.label = label;
        }
    }
}
