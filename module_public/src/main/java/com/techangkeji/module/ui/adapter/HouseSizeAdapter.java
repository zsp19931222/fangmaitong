package com.techangkeji.module.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.utils.glide.GlideLoadUtils;
import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseSizeBean;
import com.techangkeji.module.R;

import java.util.List;

public class HouseSizeAdapter extends BaseQuickAdapter<HouseResourceReleaseSizeBean, BaseViewHolder> {
    public HouseSizeAdapter(int layoutResId, @Nullable List<HouseResourceReleaseSizeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HouseResourceReleaseSizeBean item) {
        GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(),item.getImagePath(),helper.getView(R.id.iv_hs),0,0);
        helper.setText(R.id.tv_hs,item.getRoomNum()+"室"+item.getHallNum()+"厅"+item.getCookNum()+"厨"+item.getToiletNum()+"卫");
        switch (item.getHouseTypePriceEnum()){
            case Square:
                helper.setText(R.id.tv_hs_price,item.getPrice()+"元/m²");
                break;
            case Undetermined:
                helper.setText(R.id.tv_hs_price,"销售待定");
                break;
            case ASuitOf:
                helper.setText(R.id.tv_hs_price,item.getPrice()+"元/套");
                break;
        }

    }
}
