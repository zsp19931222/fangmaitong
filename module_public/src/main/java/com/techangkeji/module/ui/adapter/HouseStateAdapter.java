package com.techangkeji.module.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.techangkeji.module.R;

import java.util.List;

import me.goldze.mvvmhabit.http.net.entity.information.CommentListEntity;

public class HouseStateAdapter extends BaseQuickAdapter<CommentListEntity.DataBean, BaseViewHolder> {
    public HouseStateAdapter(int layoutResId, @Nullable List<CommentListEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentListEntity.DataBean item) {
        helper.setText(R.id.tv_hs_content,item.getContent());
        helper.setText(R.id.tv_hs_time,item.getCreateTime());
    }
}
