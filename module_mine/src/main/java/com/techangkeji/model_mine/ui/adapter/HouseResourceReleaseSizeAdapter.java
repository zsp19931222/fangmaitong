package com.techangkeji.model_mine.ui.adapter;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.utils.glide.GlideLoadUtils;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.ui.activity.AddSizeActivity;
import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseSizeBean;
import com.techangkeji.model_mine.ui.data.HouseResourceReleaseSizeData;
import com.techangkeji.model_mine.ui.viewModel.HouseResourceReleaseViewModel;

import java.util.List;

public class HouseResourceReleaseSizeAdapter extends BaseQuickAdapter<HouseResourceReleaseSizeBean, BaseViewHolder> {
    private HouseResourceReleaseViewModel viewModel;

    public HouseResourceReleaseSizeAdapter(int layoutResId, @Nullable List<HouseResourceReleaseSizeBean> data, HouseResourceReleaseViewModel viewModel) {
        super(layoutResId, data);
        this.viewModel = viewModel;
    }


    @Override
    protected void convert(BaseViewHolder helper, HouseResourceReleaseSizeBean item) {
        helper.setText(R.id.tv_hs_type, item.getRoomNum() + "室" + item.getHallNum() + "厅" + item.getCookNum() + "厨" + item.getToiletNum() + "卫");
        helper.setText(R.id.tv_hs_price, item.getPrice());
        helper.setText(R.id.tv_hs_size, item.getSize());
        GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), item.getImagePath(), helper.getView(R.id.iv_hs), 0);
        helper.getView(R.id.tv_hs_delete).setOnClickListener(view -> {
            HouseResourceReleaseSizeData.getInstance().getList().remove(item);
            notifyDataSetChanged();
        });
        helper.getView(R.id.tv_hs_compile).setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position", helper.getAdapterPosition());
            viewModel.startActivity(AddSizeActivity.class, bundle);
        });
    }
}
