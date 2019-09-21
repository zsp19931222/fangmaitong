package com.techangkeji.module.ui.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * description:
 * author:created by Andy on 2019/9/21 10:39
 * email:zsp872126510@gmail.com
 */
public class AreaLevel0Bean extends AbstractExpandableItem<AreaLevel1Bean> implements MultiItemEntity {
    private String header;
    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return 0;
    }

    public AreaLevel0Bean(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
