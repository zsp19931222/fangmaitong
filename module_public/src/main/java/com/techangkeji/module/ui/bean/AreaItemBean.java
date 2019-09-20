package com.techangkeji.module.ui.bean;

/**
 * description:
 * author:created by Andy on 2019/9/21 00:12
 * email:zsp872126510@gmail.com
 */
public class AreaItemBean {
    private int id;
    private int parentId;
    private String areaName;
    private boolean select;

    public AreaItemBean(int id, int parentId, String areaName, boolean select) {
        this.id = id;
        this.parentId = parentId;
        this.areaName = areaName;
        this.select = select;
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

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
