package com.techangkeji.model_mine.ui.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.ui.popup.HRSDLabelPopupwindow;
import com.techangkeji.model_mine.ui.viewModel.HouseResourceReleaseViewModel;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/12 0012 14:51
 * email:zsp872126510@gmail.com
 */
public class HSRDAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private HouseResourceReleaseViewModel viewModel;

    public HSRDAdapter(int layoutResId, @Nullable List<String> data, HouseResourceReleaseViewModel viewModel) {
        super(layoutResId, data);
        this.viewModel = viewModel;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_ih, item);
        if ("+".equals(item)) {
            helper.getView(R.id.iv_ih).setVisibility(View.GONE);
            helper.getView(R.id.tv_ih).setOnClickListener(view -> new HRSDLabelPopupwindow(helper.itemView.getContext(), viewModel).showPopupWindow());
        }else {
            helper.getView(R.id.iv_ih).setVisibility(View.VISIBLE);
        }
        helper.getView(R.id.iv_ih).setOnClickListener(view -> {
            viewModel.labelList.remove(item);
            notifyDataSetChanged();
        });
    }
}
