package com.techangkeji.model_mine.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.utils.glide.GlideLoadUtils;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.ui.bean.SelectFriendBean;
import com.techangkeji.model_mine.ui.viewModel.HouseResourceReleaseViewModel;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/22 20:52
 * email:zsp872126510@gmail.com
 */
public class LinkManAdapter extends BaseQuickAdapter<SelectFriendBean, BaseViewHolder> {
    private HouseResourceReleaseViewModel viewModel;
    public LinkManAdapter(int layoutResId, @Nullable List<SelectFriendBean> data,HouseResourceReleaseViewModel viewModel) {
        super(layoutResId, data);
        this.viewModel=viewModel;
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectFriendBean item) {
        helper.setText(R.id.tv_name,item.getReal_name());
        helper.setText(R.id.tv_phone,item.getPhone());
        helper.getView(R.id.tv_il_delete).setOnClickListener(v -> {
            viewModel.linkManList.remove(helper.getAdapterPosition());
            viewModel.adapterObservableField.get().notifyDataSetChanged();
        });
        GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(),item.getHead_url(),helper.getView(R.id.iv_il),0,22);
    }
}
