package com.techangkeji.model_mine.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.techangkeji.model_mine.R;

import java.util.List;

import me.goldze.mvvmhabit.http.net.entity.JobHuntingEntity;
import me.goldze.mvvmhabit.http.net.entity.RecruitmentListEntity;

public class JobAdapter extends BaseQuickAdapter<JobHuntingEntity.DataBean, BaseViewHolder> {
    public JobAdapter(int layoutResId, @Nullable List<JobHuntingEntity.DataBean> data) {
        super(layoutResId, data);
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
    }
}
