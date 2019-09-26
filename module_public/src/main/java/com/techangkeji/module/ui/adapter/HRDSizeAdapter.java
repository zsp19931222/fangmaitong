package com.techangkeji.module.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.utils.glide.GlideLoadUtils;
import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseSizeBean;
import com.techangkeji.module.R;

import java.util.List;

import static com.techangkeji.model_mine.ui.m_enum.HouseTypeSizeEnum.BuildingSurface;

public class HRDSizeAdapter extends BaseQuickAdapter<HouseResourceReleaseSizeBean, BaseViewHolder> {
    public HRDSizeAdapter(int layoutResId, @Nullable List<HouseResourceReleaseSizeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HouseResourceReleaseSizeBean item) {
        GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(),item.getImagePath(),helper.getView(R.id.iv_hhs),0,0);
        helper.setText(R.id.tv_hhs_structure,item.getRoomNum()+"室"+item.getHallNum()+"厅"+item.getCookNum()+"厨"+item.getToiletNum()+"卫");
        if (item.getHouseTypeSizeEnum()==BuildingSurface){
            helper.setText(R.id.tv_hhs_acreage,"建面"+item.getSize()+"m²");
        }else {
            helper.setText(R.id.tv_hhs_acreage,"套内"+item.getSize()+"m²");
        }
    }
}
