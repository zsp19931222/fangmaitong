package com.techangkeji.model_mine.ui.adapter;

import android.widget.CheckBox;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.ui.bean.SelectFriendBean;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/22 20:40
 * email:zsp872126510@gmail.com
 */
public class SelectFriendAdapter extends BaseQuickAdapter<SelectFriendBean, BaseViewHolder> {
    public SelectFriendAdapter(int layoutResId, @Nullable List<SelectFriendBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectFriendBean item) {
     CheckBox checkBox= helper.getView(R.id.cb_isf);
     if (item.isSelect()){
         checkBox.setChecked(true);
     }else {
         checkBox.setChecked(false);
     }
     helper.itemView.setOnClickListener(v -> {
         if (item.isSelect()) {
             checkBox.setChecked(false);
             item.setSelect(false);
         } else {
             checkBox.setChecked(true);
             item.setSelect(true);
         }
         notifyDataSetChanged();
     });
     helper.setText(R.id.tv_isf,item.getPhone());
    }
}
