package com.techangkeji.model_home.ui.adapter;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.router.ARouterPath;

import java.util.List;

public class FriendRecommendAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public FriendRecommendAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.itemView.setOnClickListener(view -> {
            ARouter.getInstance().build(ARouterPath.FriendCircle.CardActivity).navigation();
        });
    }
}
