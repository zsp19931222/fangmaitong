package com.techangkeji.module.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.listener.PopupSelectListener;
import com.goldze.base.utils.glide.GlideLoadUtils;
import com.techangkeji.module.R;
import com.techangkeji.module.ui.bean.RemarkPopupBean;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/30 0030 09:55
 * email:zsp872126510@gmail.com
 */
public class RemarkPopupAdapter extends BaseQuickAdapter<RemarkPopupBean, BaseViewHolder> {
    public RemarkPopupAdapter(int layoutResId, @Nullable List<RemarkPopupBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RemarkPopupBean item) {
        helper.setText(R.id.name, item.getName());
        helper.setText(R.id.score,item.getScore()+"分");
        switch (item.getScore()) {
            case 0:
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.start1), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.start2), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.start3), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.start4), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.start5), 0);
                break;
            case 1:
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.start1), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.start2), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.start3), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.start4), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.start5), 0);
                break;
            case 2:
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.start1), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.start2), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.start3), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.start4), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.start5), 0);
                break;
            case 3:
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.start1), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.start2), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.start3), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.start4), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.start5), 0);
                break;
            case 4:
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.start1), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.start2), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.start3), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.start4), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.start5), 0);
                break;
            case 5:
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.start1), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.start2), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.start3), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.start4), 0);
                GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.start5), 0);
                break;
        }
        helper.getView(R.id.start1).setOnClickListener(view -> {
            item.setScore(1);
            notifyDataSetChanged();
            popupSelectListener.select(helper.getAdapterPosition());
        });
        helper.getView(R.id.start2).setOnClickListener(view -> {
            item.setScore(2);
            notifyDataSetChanged();
            popupSelectListener.select(helper.getAdapterPosition());
        });
        helper.getView(R.id.start3).setOnClickListener(view -> {
            item.setScore(3);
            notifyDataSetChanged();
            popupSelectListener.select(helper.getAdapterPosition());
        });
        helper.getView(R.id.start4).setOnClickListener(view -> {
            item.setScore(4);
            notifyDataSetChanged();
            popupSelectListener.select(helper.getAdapterPosition());
        });
        helper.getView(R.id.start5).setOnClickListener(view -> {
            item.setScore(5);
            notifyDataSetChanged();
            popupSelectListener.select(helper.getAdapterPosition());
        });
    }
    private PopupSelectListener popupSelectListener;

    public void setPopupSelectListener(PopupSelectListener popupSelectListener) {
        this.popupSelectListener = popupSelectListener;
    }
}
