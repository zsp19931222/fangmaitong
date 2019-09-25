package com.techangkeji.module_information.ui.adapter;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.utils.glide.GlideLoadUtils;
import com.techangkeji.model_information.R;
import com.techangkeji.module_information.ui.activity.InformationDetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.goldze.mvvmhabit.http.net.entity.information.NewsListEntity;
import me.goldze.mvvmhabit.utils.ZLog;
import me.goldze.mvvmhabit.view.shape.RadiusImageView;
import me.goldze.mvvmhabit.view.shape.RadiusTextView;

public class InformationAdapter extends BaseQuickAdapter<NewsListEntity.DataBean, BaseViewHolder> {

    public InformationAdapter(int layoutResId, @Nullable List<NewsListEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsListEntity.DataBean item) {
        GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(),item.getNews_img(),helper.getView(R.id.iv_iii),0);
        helper.setText(R.id.tv_iii_title, item.getNews_title());
        helper.setText(R.id.tv_iii_look, item.getLook_num() + "");
        helper.setText(R.id.tv_iii_like, item.getLike_num() + "");
        helper.setText(R.id.tv_iii_comment, item.getComment_num() + "");
        RadiusTextView label1 = helper.getView(R.id.tv_iii_label1);
        RadiusTextView label2 = helper.getView(R.id.tv_iii_label2);
        RadiusTextView label3 = helper.getView(R.id.tv_iii_label3);
        RadiusTextView label4 = helper.getView(R.id.tv_iii_label4);
        ZLog.d(item.getLabel().size());
        switch (item.getLabel().size()) {
            case 1:
                label1.setText(item.getLabel().get(0));
                label1.setVisibility(View.VISIBLE);
                break;
            case 2:
                label1.setText(item.getLabel().get(0));
                label2.setText(item.getLabel().get(1));
                label1.setVisibility(View.VISIBLE);
                label2.setVisibility(View.VISIBLE);
                break;
            case 3:
                label1.setText(item.getLabel().get(0));
                label2.setText(item.getLabel().get(1));
                label3.setText(item.getLabel().get(2));
                label1.setVisibility(View.VISIBLE);
                label2.setVisibility(View.VISIBLE);
                label3.setVisibility(View.VISIBLE);
                break;
            case 4:
                label1.setText(item.getLabel().get(0));
                label2.setText(item.getLabel().get(1));
                label3.setText(item.getLabel().get(2));
                label4.setText(item.getLabel().get(3));
                label1.setVisibility(View.VISIBLE);
                label2.setVisibility(View.VISIBLE);
                label3.setVisibility(View.VISIBLE);
                label4.setVisibility(View.VISIBLE);
                break;
        }
        if (1 == item.getTopping()) {
            helper.getView(R.id.tv_iii_top).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv_iii_top).setVisibility(View.GONE);
        }
        helper.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(helper.itemView.getContext(), InformationDetailActivity.class);
            intent.putExtra("id",item.getId()+"");
            helper.itemView.getContext().startActivity(intent);
        });
    }
}
