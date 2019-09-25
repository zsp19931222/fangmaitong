package com.techangkeji.module_information.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.eventbus.PopupwindowRxBusBean;
import com.goldze.base.listener.PopupSelectListener;
import com.techangkeji.model_information.R;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/25 20:14
 * email:zsp872126510@gmail.com
 */
public class InformationSortAdapter extends BaseQuickAdapter<PopupwindowRxBusBean, BaseViewHolder> {
    public InformationSortAdapter(int layoutResId, @Nullable List<PopupwindowRxBusBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PopupwindowRxBusBean item) {
        helper.setText(R.id.tv_popup, item.getStr());
        helper.itemView.setOnClickListener(v -> selectListener.select(helper.getAdapterPosition()));
    }
    private PopupSelectListener selectListener;

    public void setSelectListener(PopupSelectListener selectListener) {
        this.selectListener = selectListener;
    }
}
