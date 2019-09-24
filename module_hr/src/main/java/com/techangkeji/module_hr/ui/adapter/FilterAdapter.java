package com.techangkeji.module_hr.ui.adapter;

import android.graphics.Color;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.bean.FeaturedLabelBean;
import com.techangkeji.module_hr.R;
import com.techangkeji.module_hr.ui.listener.PopupSelectListener;

import java.util.List;

import me.goldze.mvvmhabit.view.shape.RadiusTextView;

/**
 * description:
 * author:created by Andy on 2019/9/16 0016 14:11
 * email:zsp872126510@gmail.com
 */
public class FilterAdapter extends BaseQuickAdapter<FeaturedLabelBean, BaseViewHolder> {
    public FilterAdapter(int layoutResId, @Nullable List<FeaturedLabelBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeaturedLabelBean item) {
        RadiusTextView radiusTextView=helper.getView(R.id.tv_if);
        radiusTextView.setText(item.getLabel_name());
        if (item.isSelect()){
            radiusTextView.getDelegate().setBackgroundColor(ContextCompat.getColor(helper.itemView.getContext(),R.color.color_FF8C00));
            radiusTextView.getDelegate().setTextColor(ContextCompat.getColor(helper.itemView.getContext(),R.color.white));
        }else {
            radiusTextView.getDelegate().setBackgroundColor(ContextCompat.getColor(helper.itemView.getContext(),R.color.color_DDDDDD));
            radiusTextView.getDelegate().setTextColor(ContextCompat.getColor(helper.itemView.getContext(),R.color.color_dark_333333));
        }
        helper.itemView.setOnClickListener(v -> {
            if (item.isSelect()){
                item.setSelect(false);
            }else {
                item.setSelect(true);
            }
            popupSelectListener.select(helper.getAdapterPosition());
        });
    }

    private PopupSelectListener popupSelectListener;

    public void setPopupSelectListener(PopupSelectListener popupSelectListener) {
        this.popupSelectListener = popupSelectListener;
    }
}
