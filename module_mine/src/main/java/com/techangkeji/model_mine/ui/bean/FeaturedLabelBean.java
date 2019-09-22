package com.techangkeji.model_mine.ui.bean;

/**
 * description:
 * author:created by Andy on 2019/9/22 19:44
 * email:zsp872126510@gmail.com
 */
public class FeaturedLabelBean {
    private int id;
    private String label_name;
    private boolean select;

    public FeaturedLabelBean(int id, String label_name, boolean select) {
        this.id = id;
        this.label_name = label_name;
        this.select = select;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel_name() {
        return label_name;
    }

    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
