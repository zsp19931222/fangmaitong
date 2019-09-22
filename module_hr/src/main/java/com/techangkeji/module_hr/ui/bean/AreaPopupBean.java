package com.techangkeji.module_hr.ui.bean;

import java.util.Map;

/**
 * description:
 * author:created by Andy on 2019/9/22 15:03
 * email:zsp872126510@gmail.com
 */
public class AreaPopupBean {
    private int level;//层级
    private String name;
    private String id;
    private boolean select;

    public AreaPopupBean(int level, String name, String id, boolean select) {
        this.level = level;
        this.name = name;
        this.id = id;
        this.select = select;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
