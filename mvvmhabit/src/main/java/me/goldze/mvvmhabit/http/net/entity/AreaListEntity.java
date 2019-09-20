package me.goldze.mvvmhabit.http.net.entity;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/20 23:21
 * email:zsp872126510@gmail.com
 */
public class AreaListEntity extends BaseEntity{

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
         * parentId : 0
         * areaName : 四川省
         * remark : 3
         * createTime : 2019-09-13 23:19:10
         * del : 0
         * type : 1
         * state : 0
         * child : [{"id":5,"parentId":1,"areaName":"成都市","remark":"3","createTime":"2019-09-13 23:19:10","del":0,"type":3,"state":0,"child":[{"id":9,"parentId":16,"areaName":"渝中区","remark":"3","createTime":"2019-09-13 23:19:10","del":0,"type":4,"state":0,"child":null}]},{"id":6,"parentId":1,"areaName":"遂宁市","remark":"3","createTime":"2019-09-13 23:19:10","del":0,"type":3,"state":0,"child":[]},{"id":14,"parentId":1,"areaName":"绵阳市","remark":"3","createTime":"2019-09-13 23:41:54","del":0,"type":3,"state":0,"child":[{"id":15,"parentId":14,"areaName":"安州区","remark":"3","createTime":"2019-09-13 23:43:02","del":0,"type":4,"state":0,"child":null}]}]
         */

        private int id;
        private int parentId;
        private String areaName;
        private String remark;
        private String createTime;
        private int del;
        private int type;
        private int state;
        private List<ChildBeanX> child;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public List<ChildBeanX> getChild() {
            return child;
        }

        public void setChild(List<ChildBeanX> child) {
            this.child = child;
        }

        public static class ChildBeanX {
            /**
             * id : 5
             * parentId : 1
             * areaName : 成都市
             * remark : 3
             * createTime : 2019-09-13 23:19:10
             * del : 0
             * type : 3
             * state : 0
             * child : [{"id":9,"parentId":16,"areaName":"渝中区","remark":"3","createTime":"2019-09-13 23:19:10","del":0,"type":4,"state":0,"child":null}]
             */

            private int id;
            private int parentId;
            private String areaName;
            private String remark;
            private String createTime;
            private int del;
            private int type;
            private int state;
            private List<ChildBean> child;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
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

            public List<ChildBean> getChild() {
                return child;
            }

            public void setChild(List<ChildBean> child) {
                this.child = child;
            }

            public static class ChildBean {
                /**
                 * id : 9
                 * parentId : 16
                 * areaName : 渝中区
                 * remark : 3
                 * createTime : 2019-09-13 23:19:10
                 * del : 0
                 * type : 4
                 * state : 0
                 * child : null
                 */

                private int id;
                private int parentId;
                private String areaName;
                private String remark;
                private String createTime;
                private int del;
                private int type;
                private int state;
                private Object child;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getParentId() {
                    return parentId;
                }

                public void setParentId(int parentId) {
                    this.parentId = parentId;
                }

                public String getAreaName() {
                    return areaName;
                }

                public void setAreaName(String areaName) {
                    this.areaName = areaName;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
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

                public Object getChild() {
                    return child;
                }

                public void setChild(Object child) {
                    this.child = child;
                }
            }
        }
    }
}
