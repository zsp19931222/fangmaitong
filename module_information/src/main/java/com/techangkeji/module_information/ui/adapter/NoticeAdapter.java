package com.techangkeji.module_information.ui.adapter;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.techangkeji.module_information.ui.activity.InviteDetailActivity;
import com.techangkeji.module_information.ui.activity.NoticeActivity;

import java.util.List;

public class NoticeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public NoticeAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(helper.itemView.getContext(), NoticeActivity.class);
            helper.itemView.getContext().startActivity(intent);
        });
    }
}
