package com.techangkeji.model_mine.ui.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.router.ARouterPath;
import com.goldze.base.utils.DateUtil;
import com.goldze.base.utils.glide.GlideLoadUtils;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.ui.viewModel.CollectViewModel;

import java.util.List;

import me.goldze.mvvmhabit.http.net.entity.RecommendBuildingEntity;
import me.goldze.mvvmhabit.view.shape.RadiusTextView;

public class CollectAdapter extends BaseQuickAdapter<RecommendBuildingEntity.DataBean, BaseViewHolder> {
    private CollectViewModel viewModel;
    public CollectAdapter(int layoutResId, @Nullable List<RecommendBuildingEntity.DataBean> data,CollectViewModel viewModel) {
        super(layoutResId, data);
        this.viewModel=viewModel;
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendBuildingEntity.DataBean item) {
        RadiusTextView tv_ihr_label1 = helper.getView(R.id.tv_ihr_label1);
        RadiusTextView tv_ihr_label2 = helper.getView(R.id.tv_ihr_label2);
        RadiusTextView tv_ihr_label3 = helper.getView(R.id.tv_ihr_label3);
        helper.setText(R.id.tv_ihr_name, item.getListing_name());
        helper.setText(R.id.rv_ihr_price, item.getAverage_price() + "元/m²");
//        helper.setText(R.id.rv_ihr_type,)
        if (item.getLabels().size() == 1) {
            tv_ihr_label1.setText(item.getLabels().get(0));
            tv_ihr_label2.setVisibility(View.GONE);
            tv_ihr_label3.setVisibility(View.GONE);
        } else if (item.getLabels().size() == 2) {
            tv_ihr_label1.setText(item.getLabels().get(0));
            tv_ihr_label2.setText(item.getLabels().get(1));
            tv_ihr_label3.setVisibility(View.GONE);
        } else if (item.getLabels().size() >= 3) {
            tv_ihr_label1.setText(item.getLabels().get(0));
            tv_ihr_label2.setText(item.getLabels().get(1));
            tv_ihr_label3.setText(item.getLabels().get(2));
        } else {
            tv_ihr_label1.setVisibility(View.GONE);
            tv_ihr_label2.setVisibility(View.GONE);
            tv_ihr_label3.setVisibility(View.GONE);
        }
        if (DateUtil.getInstance().compare(DateUtil.getInstance().timeStampChangeString1(System.currentTimeMillis() + "", null), item.getOpen_time() + " 00:00:00")) {
            helper.setText(R.id.tv_ihr_sales_status, "在售");
        } else {
            helper.setText(R.id.tv_ihr_sales_status, "开盘时间：" + item.getOpen_time());
        }
        helper.getView(R.id.tv_ihr_developers).setOnClickListener(v -> viewModel.deleteCollection(helper.getAdapterPosition()));
        helper.itemView.setOnClickListener(v -> ARouter.getInstance().build(ARouterPath.Public.HRDetailActivity).withInt("id",item.getId()).navigation());
        GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), item.getUrl(), helper.getView(R.id.iv_ihr), 0);
        helper.setText(R.id.rv_ihr_type, item.getType());

    }
}
