package com.techangkeji.model_home.ui.adapter;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.constant.RxBusMessageEventConstants;
import com.goldze.base.router.ARouterPath;
import com.goldze.base.utils.glide.GlideLoadUtils;
import com.techangkeji.model_home.R;
import com.techangkeji.model_home.ui.bean.HomeGridViewBean;

import java.util.List;

import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.utils.ZLog;

public class GridAdapter extends BaseQuickAdapter<HomeGridViewBean, BaseViewHolder> {
    public GridAdapter(int layoutResId, @Nullable List<HomeGridViewBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeGridViewBean item) {
        helper.setText(R.id.tv_ig, item.getName());
        GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), item.getImage(), helper.getView(R.id.iv_ig), 0);
        helper.itemView.setOnClickListener(view -> {
            if ("新房".equals(item.getName())) {
                RxBus.getDefault().post(RxBusMessageEventConstants.XF);
            }else if("商业地产".equals(item.getName())){
                RxBus.getDefault().post(RxBusMessageEventConstants.SYDC);
            } else if ("地图找房".equals(item.getName())) {
                ARouter.getInstance().build(ARouterPath.Message.MapActivity).navigation();
            } else if ("最新资讯".equals(item.getName()) || "新手入门".equals(item.getName())) {
                RxBus.getDefault().post(RxBusMessageEventConstants.ZXZX);
            } else if ("招聘信息".equals(item.getName())) {
                RxBus.getDefault().post(RxBusMessageEventConstants.ZPXX);
            } else if ("人脉交友".equals(item.getName())) {
                ARouter.getInstance().build(ARouterPath.Message.AddContactActivity).navigation();
            }else if ("房贷咨询".equals(item.getName())){
                ARouter.getInstance().build(ARouterPath.Home.HousingInformationActivity).navigation();
            }
        });

    }
}
