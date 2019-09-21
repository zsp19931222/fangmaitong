package com.techangkeji.module.ui.adapter;

import androidx.annotation.Nullable;

import com.baidu.mapapi.search.sug.SuggestionResult;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.techangkeji.module.R;

import java.util.List;

import me.goldze.mvvmhabit.bus.RxBus;

/**
 * description:
 * author:created by Andy on 2019/9/21 20:00
 * email:zsp872126510@gmail.com
 */
public class LocationAdapter extends BaseQuickAdapter<SuggestionResult.SuggestionInfo, BaseViewHolder> {
    public LocationAdapter(int layoutResId, @Nullable List<SuggestionResult.SuggestionInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SuggestionResult.SuggestionInfo item) {
        helper.setText(R.id.tv_il_name, item.key);
        helper.setText(R.id.tv_il_address, item.city + item.district);
        helper.itemView.setOnClickListener(v -> RxBus.getDefault().post(item));
    }
}
