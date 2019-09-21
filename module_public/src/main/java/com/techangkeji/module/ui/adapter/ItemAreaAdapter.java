package com.techangkeji.module.ui.adapter;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.techangkeji.module.R;
import com.techangkeji.module.ui.activity.AreaSelectActivity;
import com.techangkeji.module.ui.bean.AreaItemBean;

import java.util.List;

import me.goldze.mvvmhabit.utils.ZLog;
import me.goldze.mvvmhabit.view.shape.RadiusTextView;

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
        RadiusTextView radiusTextView = helper.getView(R.id.tv_area);
        if (item.isSelect()) {
            radiusTextView.getDelegate().setBackgroundColor(ContextCompat.getColor(helper.itemView.getContext(), R.color.color_FF8C00));
            radiusTextView.getDelegate().setTextColor(ContextCompat.getColor(helper.itemView.getContext(), R.color.white));
        } else {
            radiusTextView.getDelegate().setBackgroundColor(ContextCompat.getColor(helper.itemView.getContext(), R.color.white));
            radiusTextView.getDelegate().setTextColor(ContextCompat.getColor(helper.itemView.getContext(), R.color.color_dark_333333));
        }
        radiusTextView.getDelegate().init();
        radiusTextView.setText(item.getAreaName());
        helper.itemView.setOnClickListener(v -> {
            onCityIdListener.cityId(item.getId(),helper.getAdapterPosition());
        });
    }

    public interface OnCityIdListener {
        void cityId(int id,int position);
    }

    public OnCityIdListener onCityIdListener;

    public void setOnCityIdListener(OnCityIdListener onCityIdListener) {
        this.onCityIdListener = onCityIdListener;
    }
}
