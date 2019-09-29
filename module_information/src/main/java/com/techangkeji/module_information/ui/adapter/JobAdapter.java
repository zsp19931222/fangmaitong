package com.techangkeji.module_information.ui.adapter;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.techangkeji.model_information.R;
import com.techangkeji.module_information.ui.activity.InviteDetailActivity;
import com.techangkeji.module_information.ui.activity.JobDetailActivity;

import java.util.List;

import me.goldze.mvvmhabit.http.net.entity.JobHuntingEntity;
import me.goldze.mvvmhabit.http.net.entity.RecruitmentListEntity;

public class JobAdapter extends BaseQuickAdapter<JobHuntingEntity.DataBean, BaseViewHolder> {
    public JobAdapter(int layoutResId, @Nullable List<JobHuntingEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JobHuntingEntity.DataBean item) {
        helper.setText(R.id.tv_name,item.getHuntingTitle());
        helper.setText(R.id.tv_area,"地区："+item.getCity()+" "+item.getDistrict());
//        helper.setText(R.id.tv_time,"发布时间："+ item.getCreateTime());

        if ("1".equals(item.getRemark())) {
            helper.getView(R.id.tv_top).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv_top).setVisibility(View.GONE);
        }

        helper.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(helper.itemView.getContext(), JobDetailActivity.class);
            intent.putExtra("data",item);
            helper.itemView.getContext().startActivity(intent);
        });
    }
}
