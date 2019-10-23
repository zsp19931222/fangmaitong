package com.techangkeji.model_mine.ui.adapter;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.bean.FeaturedLabelBean;
import com.techangkeji.model_mine.R;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/10/23 0023 15:43
 * email:zsp872126510@gmail.com
 */
public class PropertyTypeAdapter extends BaseQuickAdapter<FeaturedLabelBean, BaseViewHolder> {
    public PropertyTypeAdapter(int layoutResId, @Nullable List<FeaturedLabelBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeaturedLabelBean item) {
        helper.setText(R.id.tv_pt, item.getLabel_name()).addOnClickListener(R.id.tv_pt);
        if (item.isSelect()){
            helper.getView(R.id.v_pt).setVisibility(View.VISIBLE);
            helper.setTextColor(R.id.tv_pt,ContextCompat.getColor(helper.itemView.getContext(),R.color.color_FF8C00));
        }else {
            helper.getView(R.id.v_pt).setVisibility(View.INVISIBLE);
            helper.setTextColor(R.id.tv_pt,ContextCompat.getColor(helper.itemView.getContext(),R.color.color_gray_666666));
        }
    }
}
