package com.techangkeji.module.ui.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/21 10:39
 * email:zsp872126510@gmail.com
 */
public class AreaLevel1Bean extends AbstractExpandableItem implements MultiItemEntity {
    private List<AreaItemBean> cityBeanList;

    public AreaLevel1Bean(List<AreaItemBean> cityBeanList) {
        this.cityBeanList = cityBeanList;
    }

    public List<AreaItemBean> getCityBeanList() {
        return cityBeanList;
    }

    public void setCityBeanList(List<AreaItemBean> cityBeanList) {
        this.cityBeanList = cityBeanList;
    }

    @Override
    public int getLevel() {
        return 1;
    }


    @Override
    public int getItemType() {
        return 1;
    }
}
