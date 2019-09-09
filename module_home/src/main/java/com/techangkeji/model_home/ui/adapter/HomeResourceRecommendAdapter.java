package com.techangkeji.model_home.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import me.goldze.mvvmhabit.utils.ZLog;

public class HomeResourceRecommendAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public HomeResourceRecommendAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ZLog.d(item);
    }
}
