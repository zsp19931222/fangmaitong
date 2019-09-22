package com.techangkeji.model_mine.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
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
        helper.setText(R.id.tv_name,item.getPhone());
        helper.getView(R.id.tv_il_delete).setOnClickListener(v -> {
            viewModel.linkManList.remove(helper.getAdapterPosition());
            viewModel.adapterObservableField.get().notifyDataSetChanged();
        });
    }
}