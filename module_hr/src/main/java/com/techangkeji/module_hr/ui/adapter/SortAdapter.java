package com.techangkeji.module_hr.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.listener.PopupSelectListener;
import com.techangkeji.module_hr.R;

import java.util.List;

public class SortAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public SortAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_ia, item);
        helper.itemView.setOnClickListener(v ->popupSelectListener.select(helper.getAdapterPosition()));
    }

    private PopupSelectListener popupSelectListener;

    public void setPopupSelectListener(PopupSelectListener popupSelectListener) {
        this.popupSelectListener = popupSelectListener;
    }
}