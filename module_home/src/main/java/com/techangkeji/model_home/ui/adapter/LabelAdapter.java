package com.techangkeji.model_home.ui.adapter;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_home.R;

import java.util.List;

import me.goldze.mvvmhabit.http.net.entity.WordEntity;
import me.goldze.mvvmhabit.utils.ZLog;

public class LabelAdapter extends BaseQuickAdapter<WordEntity.DataBean, BaseViewHolder> {
    public LabelAdapter(int layoutResId, @Nullable List<WordEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WordEntity.DataBean item) {
        helper.itemView.setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterPath.Public.SearchActivity).withInt("from", 0).withString("input",item.getWordName()).navigation();
        });
        helper.setText(R.id.label, item.getWordName());
        ZLog.d(item);
    }
}
