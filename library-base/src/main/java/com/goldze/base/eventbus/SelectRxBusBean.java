package com.goldze.base.eventbus;

/**
 * description:
 * author:created by Andy on 2019/9/21 14:25
 * email:zsp872126510@gmail.com
 */
public class SelectRxBusBean {
    private int id;
    private int parentId;
    private String areaName;

    public SelectRxBusBean(int id, int parentId, String areaName) {
        this.id = id;
        this.parentId = parentId;
        this.areaName = areaName;
    }

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
}
