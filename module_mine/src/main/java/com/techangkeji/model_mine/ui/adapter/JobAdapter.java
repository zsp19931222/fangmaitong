package com.techangkeji.model_mine.ui.adapter;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.ui.activity.JobReleaseActivity;
import com.techangkeji.model_mine.ui.viewModel.JobViewModel;

import java.util.List;

import me.goldze.mvvmhabit.http.net.entity.JobHuntingEntity;

public class JobAdapter extends BaseQuickAdapter<JobHuntingEntity.DataBean, BaseViewHolder> {
    private JobViewModel viewModel;
    public JobAdapter(int layoutResId, @Nullable List<JobHuntingEntity.DataBean> data,JobViewModel viewModel) {
        super(layoutResId, data);
        this.viewModel=viewModel;
    }

    @Override
    protected void convert(BaseViewHolder helper, JobHuntingEntity.DataBean item) {
        helper.setText(R.id.tv_iii_title, item.getHuntingTitle());
        switch (item.getState()){
            case 1:
                helper.setText(R.id.tv_iii_state,"未审核");
                break;
            case 2:
                helper.setText(R.id.tv_iii_state,"通过审核");
                break;
            case 3:
                helper.setText(R.id.tv_iii_state,"未通过审核");
                break;
        }
        helper.setText(R.id.tv_iii_area,"地区："+item.getWorkAddress());
        helper.setText(R.id.tv_iii_time,"发布时间："+item.getCreateTime());
        helper.getView(R.id.tv_iii_compile).setOnClickListener(v ->{
            Intent intent=new Intent(helper.itemView.getContext(), JobReleaseActivity.class);
            intent.putExtra("data",item);
            helper.itemView.getContext().startActivity(intent);
        } );
        helper.getView(R.id.tv_iii_delete).setOnClickListener(v -> viewModel.delete(helper.getAdapterPosition()));
    }
}
