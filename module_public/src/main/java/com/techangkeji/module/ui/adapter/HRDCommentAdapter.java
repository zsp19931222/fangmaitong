package com.techangkeji.module.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.utils.glide.GlideLoadUtils;
import com.techangkeji.module.R;

import java.util.List;

import me.goldze.mvvmhabit.http.net.entity.ReviewListEntity;

public class HRDCommentAdapter extends BaseQuickAdapter<ReviewListEntity.DataBean, BaseViewHolder> {
    public HRDCommentAdapter(int layoutResId, @Nullable List<ReviewListEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReviewListEntity.DataBean item) {
        helper.setText(R.id.tv_hc_site, helper.getAdapterPosition() + 1 + "楼");
        helper.setText(R.id.tv_hc_name, item.getUser().getName());
        helper.setText(R.id.tv_hc_time, item.getCreateTime());
        helper.setText(R.id.tv_hc_content, item.getContent());
        helper.setText(R.id.tv_hc_grade, "价格：" + item.getPriceStar() + "   地段：" + item.getLotStar() + "   交通：" + item.getTrafficStar() + "   配套：" + item.getMatchingStar());

        int allPoint = Integer.valueOf(item.getPriceStar()) + Integer.valueOf(item.getLotStar()) + Integer.valueOf(item.getTrafficStar()) + Integer.valueOf(item.getMatchingStar());
        int average = allPoint/4;
        if (average == 0) {
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.iv_hc_start1), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.iv_hc_start2), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.iv_hc_start3), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.iv_hc_start4), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.iv_hc_start5), 0);
        } else if (average > 0 && average <= 1) {
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.iv_hc_start1), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.iv_hc_start2), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.iv_hc_start3), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.iv_hc_start4), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.iv_hc_start5), 0);
        } else if (average > 1 && average <= 2) {
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.iv_hc_start1), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.iv_hc_start2), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.iv_hc_start3), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.iv_hc_start4), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.iv_hc_start5), 0);
        } else if (average > 2 && average <= 3) {
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.iv_hc_start1), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.iv_hc_start2), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.iv_hc_start3), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.iv_hc_start4), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.iv_hc_start5), 0);
        } else if (average > 3 && average <= 4)  {
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.iv_hc_start1), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.iv_hc_start2), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.iv_hc_start3), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.iv_hc_start4), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start, helper.getView(R.id.iv_hc_start5), 0);
        }else{
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.iv_hc_start1), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.iv_hc_start2), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.iv_hc_start3), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.iv_hc_start4), 0);
            GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.start_select, helper.getView(R.id.iv_hc_start5), 0);
        }
    }
}
