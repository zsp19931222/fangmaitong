package com.techangkeji.module_information.ui.adapter;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.techangkeji.module_information.ui.activity.InformationDetailActivity;

import java.util.List;

public class InformationAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public InformationAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(helper.itemView.getContext(), InformationDetailActivity.class);
            helper.itemView.getContext().startActivity(intent);
        });
    }
}
