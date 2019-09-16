package com.techangkeji.module_hr.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.techangkeji.module_hr.R;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/16 0016 14:11
 * email:zsp872126510@gmail.com
 */
public class FilterAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public FilterAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_if, item);
    }
}
