package com.techangkeji.module.ui.adapter;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.techangkeji.module.R;
import com.techangkeji.module.ui.bean.AreaListBean;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/20 23:36
 * email:zsp872126510@gmail.com
 */
public class AreaListAdapter extends BaseSectionQuickAdapter<AreaListBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public AreaListAdapter(int layoutResId, int sectionHeadResId, List<AreaListBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, AreaListBean item) {
        helper.setText(R.id.tv_ah, item.header);
        RecyclerView recyclerView=helper.getView(R.id.rv_ah);
        ItemAreaAdapter itemAreaAdapter = new ItemAreaAdapter(R.layout.item_area, item.getItemBeanList());
        recyclerView.setLayoutManager(new GridLayoutManager(helper.itemView.getContext(),4));
        recyclerView.setAdapter(itemAreaAdapter);
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaListBean item) {
        RecyclerView recyclerView=helper.getView(R.id.rv_ac);
        ItemAreaAdapter itemAreaAdapter = new ItemAreaAdapter(R.layout.item_area, item.t);
        recyclerView.setLayoutManager(new GridLayoutManager(helper.itemView.getContext(),4));
        recyclerView.setAdapter(itemAreaAdapter);
    }
}
