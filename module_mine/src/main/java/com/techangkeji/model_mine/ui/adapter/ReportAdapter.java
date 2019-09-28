package com.techangkeji.model_mine.ui.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kcrason.highperformancefriendscircle.beans.FriendCircleBean;
import com.kcrason.highperformancefriendscircle.widgets.VerticalCommentWidget;
import com.techangkeji.model_mine.R;

import java.util.List;

import me.goldze.mvvmhabit.http.net.entity.AppReportListEntity;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/28 22:12
 * email:zsp872126510@gmail.com
 */
public class ReportAdapter extends BaseQuickAdapter<AppReportListEntity.DataBean, BaseViewHolder> {

    public ReportAdapter(int layoutResId, @Nullable List<AppReportListEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppReportListEntity.DataBean item) {
        helper.setText(R.id.tv_if_name,item.getListingName());
        helper.setText(R.id.tv_if_time,item.getCreateTime());
        helper.setText(R.id.tv_if_reason,"举报原因："+item.getContent());
        helper.setText(R.id.tv_if_content,item.getRemark());
    }
}
