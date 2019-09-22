package com.techangkeji.module_hr.ui.adapter;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.techangkeji.module_hr.R;
import com.techangkeji.module_hr.ui.bean.AreaPopupBean;

import java.util.List;

import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.view.shape.RadiusTextView;

public class AreaAdapter extends BaseQuickAdapter<AreaPopupBean, BaseViewHolder> {
    public AreaAdapter(int layoutResId, @Nullable List<AreaPopupBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaPopupBean item) {
        RadiusTextView tv = helper.getView(R.id.tv_ia);
        tv.setText(item.getName());
        if (item.getLevel() == 3) {
            helper.itemView.setOnClickListener(v -> {
                RxBus.getDefault().post(item);
                onSelectListener.select(helper.getAdapterPosition(), item.getLevel());
            });
        } else {
            if (item.isSelect()) {
                tv.getDelegate().setBackgroundColor(ContextCompat.getColor(helper.itemView.getContext(), R.color.color_FF8C00));
                tv.getDelegate().setTextColor(ContextCompat.getColor(helper.itemView.getContext(), R.color.white));
            } else {
                tv.getDelegate().setBackgroundColor(ContextCompat.getColor(helper.itemView.getContext(), R.color.white));
                tv.getDelegate().setTextColor(ContextCompat.getColor(helper.itemView.getContext(), R.color.color_dark_333333));
            }
            tv.getDelegate().init();
            helper.itemView.setOnClickListener(v -> {
                onSelectListener.select(helper.getAdapterPosition(), item.getLevel());
            });
        }
    }

    public interface OnSelectListener {
        void select(int position, int level);
    }

    public OnSelectListener onSelectListener;

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }
}
