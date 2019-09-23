package com.techangkeji.model_mine.ui.adapter;

import android.graphics.Color;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.bean.FeaturedLabelBean;
import com.techangkeji.model_mine.R;

import java.util.List;

import me.goldze.mvvmhabit.view.shape.RadiusTextView;

/**
 * description:
 * author:created by Andy on 2019/9/22 18:04
 * email:zsp872126510@gmail.com
 */
public class HRSDLabelAdapter extends BaseQuickAdapter<FeaturedLabelBean, BaseViewHolder> {
    public HRSDLabelAdapter(int layoutResId, @Nullable List<FeaturedLabelBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeaturedLabelBean item) {
        RadiusTextView radiusTextView = helper.getView(R.id.tv_select);
        radiusTextView.setText(item.getLabel_name());
        if (item.isSelect()) {
            radiusTextView.getDelegate().setTextColor(Color.parseColor("#FF8C00"));
            radiusTextView.getDelegate().setBackgroundColor(Color.parseColor("#FFF8EF"));
        } else {
            radiusTextView.getDelegate().setTextColor(Color.parseColor("#999999"));
            radiusTextView.getDelegate().setBackgroundColor(Color.parseColor("#F6F6F6"));
        }
        helper.itemView.setOnClickListener(v -> {
            if (item.isSelect()) {
                item.setSelect(false);
            } else {
                item.setSelect(true);
            }
            notifyDataSetChanged();
        });
    }
}
