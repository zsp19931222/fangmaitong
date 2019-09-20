package com.techangkeji.module.ui.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

import me.goldze.mvvmhabit.http.net.entity.AreaListEntity;

/**
 * description:
 * author:created by Andy on 2019/9/21 00:05
 * email:zsp872126510@gmail.com
 */
public class AreaListBean extends SectionEntity<List<AreaItemBean>> {
   private List<AreaItemBean> itemBeanList;

    public AreaListBean(boolean isHeader, String header, List<AreaItemBean> itemBeanList) {
        super(isHeader, header);
        this.itemBeanList = itemBeanList;
    }

    public List<AreaItemBean> getItemBeanList() {
        return itemBeanList;
    }

    public void setItemBeanList(List<AreaItemBean> itemBeanList) {
        this.itemBeanList = itemBeanList;
    }

    public AreaListBean(List<AreaItemBean> areaListEntity) {
        super(areaListEntity);
    }
}
