package com.techangkeji.model_mine.ui.adapter;

import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseBannerBean;
import com.techangkeji.model_mine.ui.post_bean.HouseResourceReleaseBannerPostBean;

import java.util.List;

import me.goldze.mvvmhabit.bus.RxBus;

/**
 * description:房源发布banner
 * author:created by Andy on 2019/9/12 0012 09:19
 * email:zsp872126510@gmail.com
 */
public class HouseResourceReleaseBannerAdapter extends BaseQuickAdapter<HouseResourceReleaseBannerBean, BaseViewHolder> {
    public HouseResourceReleaseBannerAdapter(int layoutResId, @Nullable List<HouseResourceReleaseBannerBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HouseResourceReleaseBannerBean item) {
        EditText describe = helper.getView(R.id.et_ihb_describe);
        Glide.with(helper.itemView.getContext()).load(item.getImagePath()).into((ImageView) helper.getView(R.id.iv_ihb));
        describe.setText(item.getDescribe());
        item.setDescribe(describe.getText().toString());
        helper.getView(R.id.rl_ihb_delete).setOnClickListener(view -> RxBus.getDefault().post(new HouseResourceReleaseBannerPostBean(helper.getAdapterPosition())));
    }
}
