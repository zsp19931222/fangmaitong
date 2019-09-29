package me.goldze.mvvmhabit.http.net.entity;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/29 23:39
 * email:zsp872126510@gmail.com
 */
public class BannerEntity extends BaseEntity {

    /**
     * data : {"totalCount":2,"entityList":[{"imgUrl":"http://img1.imgtn.bdimg.com/it/u=3247749323,1379996244&fm=26&gp=0.jpg","innerType":0,"remark":null,"createTime":"2019-09-29 22:47:44","sort":1,"bannerName":"第一张banner","outerUrl":"www.baidu.com","id":1,"innerUrl":0,"enable":1,"del":0,"areaId":10,"state":0},{"imgUrl":"http://img2.imgtn.bdimg.com/it/u=4064075977,3738371861&fm=26&gp=0.jpg","innerType":1,"remark":null,"createTime":"2019-09-29 22:48:37","sort":2,"bannerName":"第二章banner","outerUrl":null,"id":2,"innerUrl":10,"enable":1,"del":0,"areaId":10,"state":0}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * totalCount : 2
         * entityList : [{"imgUrl":"http://img1.imgtn.bdimg.com/it/u=3247749323,1379996244&fm=26&gp=0.jpg","innerType":0,"remark":null,"createTime":"2019-09-29 22:47:44","sort":1,"bannerName":"第一张banner","outerUrl":"www.baidu.com","id":1,"innerUrl":0,"enable":1,"del":0,"areaId":10,"state":0},{"imgUrl":"http://img2.imgtn.bdimg.com/it/u=4064075977,3738371861&fm=26&gp=0.jpg","innerType":1,"remark":null,"createTime":"2019-09-29 22:48:37","sort":2,"bannerName":"第二章banner","outerUrl":null,"id":2,"innerUrl":10,"enable":1,"del":0,"areaId":10,"state":0}]
         */

        private int totalCount;
        private List<EntityListBean> entityList;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<EntityListBean> getEntityList() {
            return entityList;
        }

        public void setEntityList(List<EntityListBean> entityList) {
            this.entityList = entityList;
        }

        public static class EntityListBean {
            /**
             * imgUrl : http://img1.imgtn.bdimg.com/it/u=3247749323,1379996244&fm=26&gp=0.jpg
             * innerType : 0
             * remark : null
             * createTime : 2019-09-29 22:47:44
             * sort : 1
             * bannerName : 第一张banner
             * outerUrl : www.baidu.com
             * id : 1
             * innerUrl : 0
             * enable : 1
             * del : 0
             * areaId : 10
             * state : 0
             */

            private String imgUrl;
            private int innerType;
            private Object remark;
            private String createTime;
            private int sort;
            private String bannerName;
            private String outerUrl;
            private int id;
            private int innerUrl;
            private int enable;
            private int del;
            private int areaId;
            private int state;

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public int getInnerType() {
                return innerType;
            }

            public void setInnerType(int innerType) {
                this.innerType = innerType;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getBannerName() {
                return bannerName;
            }

            public void setBannerName(String bannerName) {
                this.bannerName = bannerName;
            }

            public String getOuterUrl() {
                return outerUrl;
            }

            public void setOuterUrl(String outerUrl) {
                this.outerUrl = outerUrl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getInnerUrl() {
                return innerUrl;
            }

            public void setInnerUrl(int innerUrl) {
                this.innerUrl = innerUrl;
            }

            public int getEnable() {
                return enable;
            }

            public void setEnable(int enable) {
                this.enable = enable;
            }

            public int getDel() {
                return del;
            }

            public void setDel(int del) {
                this.del = del;
            }

            public int getAreaId() {
                return areaId;
            }

            public void setAreaId(int areaId) {
                this.areaId = areaId;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }
        }
    }
}
