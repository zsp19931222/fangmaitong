package com.techangkeji.module_hr.ui.adapter;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.router.ARouterPath;

import java.util.List;

public class HRAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public HRAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.itemView.setOnClickListener(view -> {
            ARouter.getInstance().build(ARouterPath.Public.HRDetailActivity).withInt("id",39).navigation();
        });
    }
}