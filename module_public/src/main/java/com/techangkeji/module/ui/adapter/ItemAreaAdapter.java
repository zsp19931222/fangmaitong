package com.techangkeji.module.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.techangkeji.module.R;
import com.techangkeji.module.ui.bean.AreaItemBean;

import java.util.List;

import me.goldze.mvvmhabit.http.net.entity.AreaListEntity;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/21 00:11
 * email:zsp872126510@gmail.com
 */
public class ItemAreaAdapter extends BaseQuickAdapter<AreaItemBean, BaseViewHolder> {
    public ItemAreaAdapter(int layoutResId, @Nullable List<AreaItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaItemBean item) {
        helper.setText(R.id.tv_ia, item.getAreaName());
        ZLog.d(item.getAreaName());
    }
}
