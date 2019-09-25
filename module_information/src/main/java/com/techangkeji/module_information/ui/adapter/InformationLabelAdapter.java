package com.techangkeji.module_information.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.eventbus.PopupwindowRxBusBean;
import com.goldze.base.listener.PopupSelectListener;
import com.techangkeji.model_information.R;

import java.util.List;

import me.goldze.mvvmhabit.http.net.entity.FeaturedLabelEntity;

/**
 * description:
 * author:created by Andy on 2019/9/25 20:14
 * email:zsp872126510@gmail.com
 */
public class InformationLabelAdapter extends BaseQuickAdapter<FeaturedLabelEntity.DataBean, BaseViewHolder> {
    public InformationLabelAdapter(int layoutResId, @Nullable List<FeaturedLabelEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeaturedLabelEntity.DataBean item) {
        helper.setText(R.id.tv_popup, item.getLabel_name());
        helper.itemView.setOnClickListener(v -> selectListener.select(helper.getAdapterPosition()));
    }
    private PopupSelectListener selectListener;

    public void setSelectListener(PopupSelectListener selectListener) {
        this.selectListener = selectListener;
    }
}
