package com.techangkeji.model_mine.ui.adapter;

import android.content.Intent;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.techangkeji.model_mine.ui.activity.InformationDetailActivity;
import com.techangkeji.model_mine.ui.bean.InformationCommentBean;

import java.util.List;

public class InformationCommentAdapter extends BaseSectionQuickAdapter<InformationCommentBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public InformationCommentAdapter(int layoutResId, int sectionHeadResId, List<InformationCommentBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, InformationCommentBean item) {
    }

    @Override
    protected void convert(BaseViewHolder helper, InformationCommentBean item) {
        helper.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(helper.itemView.getContext(), InformationDetailActivity.class);
            helper.itemView.getContext().startActivity(intent);
        });
    }
}
