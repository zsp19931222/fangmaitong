package com.techangkeji.module_information.ui.adapter;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.utils.DateUtil;
import com.goldze.base.utils.glide.GlideLoadUtils;
import com.techangkeji.model_information.R;
import com.techangkeji.module_information.ui.activity.NoticeActivity;

import java.util.List;

import me.goldze.mvvmhabit.http.net.entity.information.PlacardListEntity;

public class NoticeAdapter extends BaseQuickAdapter<PlacardListEntity.DataBean, BaseViewHolder> {
    public NoticeAdapter(int layoutResId, @Nullable List<PlacardListEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PlacardListEntity.DataBean item) {
        helper.setText(R.id.tv_iin1, item.getPlacard_title());
        helper.setText(R.id.tv_iin_time, DateUtil.getInstance().getData(item.getCreate_time()));
        helper.setText(R.id.tv_iin_num, item.getLook_num()+"");
        GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), item.getShow_img_url(), helper.getView(R.id.iv_iid), 0);
        helper.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(helper.itemView.getContext(), NoticeActivity.class);
            intent.putExtra("id",item.getId()+"");
            helper.itemView.getContext().startActivity(intent);
        });
    }
}
