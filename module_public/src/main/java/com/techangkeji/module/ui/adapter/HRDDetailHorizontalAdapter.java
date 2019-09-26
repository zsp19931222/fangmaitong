package com.techangkeji.module.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.techangkeji.module.R;

import java.util.List;

public class HRDDetailHorizontalAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public HRDDetailHorizontalAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_label, item);
    }
}
