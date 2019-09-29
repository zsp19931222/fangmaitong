package com.techangkeji.model_home.ui.adapter;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_home.R;
import com.techangkeji.module_information.ui.activity.InviteDetailActivity;

import java.util.List;

import me.goldze.mvvmhabit.http.net.entity.RecruitmentListEntity;
import me.goldze.mvvmhabit.utils.ZLog;

public class RecruitmentAdapter extends BaseQuickAdapter<RecruitmentListEntity.DataBean, BaseViewHolder> {
    public RecruitmentAdapter(int layoutResId, @Nullable List<RecruitmentListEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecruitmentListEntity.DataBean item) {
        helper.setText(R.id.tv_name,item.getRecruitmentTitle());
        helper.setText(R.id.tv_area,"地区："+item.getWorkAddress());
        helper.setText(R.id.tv_time,"发布时间："+ item.getCreateTime());
        helper.setText(R.id.tv_price,"薪资待遇："+item.getMoneyDown()+"-"+item.getMoneyUp());

        if (1 == item.getTopping()) {
            helper.getView(R.id.tv_top).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv_top).setVisibility(View.GONE);
        }

        helper.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(helper.itemView.getContext(), InviteDetailActivity.class);
            intent.putExtra("data",item);
            helper.itemView.getContext().startActivity(intent);
        });
    }
}
