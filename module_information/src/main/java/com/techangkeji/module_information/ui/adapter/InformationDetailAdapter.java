package com.techangkeji.module_information.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.utils.glide.GlideLoadUtils;
import com.techangkeji.model_information.R;

import java.util.List;

import me.goldze.mvvmhabit.http.net.entity.information.CommentListEntity;

public class InformationDetailAdapter extends BaseQuickAdapter<CommentListEntity.DataBean, BaseViewHolder> {
    public InformationDetailAdapter(int layoutResId, @Nullable List<CommentListEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentListEntity.DataBean item) {
        GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(),item.getOtherUser().getHeadUrl(),helper.getView(R.id.iv_iid),0);
        helper.setText(R.id.tv_iid_name,item.getUsername());
        helper.setText(R.id.tv_iid_time,item.getCreateTime());
        helper.setText(R.id.tv_iid_content,item.getContent());
    }
}
