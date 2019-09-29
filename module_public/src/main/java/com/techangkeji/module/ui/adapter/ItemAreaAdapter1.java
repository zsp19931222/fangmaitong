package com.techangkeji.module.ui.adapter;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.eventbus.SelectRxBusBean;
import com.techangkeji.module.R;
import com.techangkeji.module.ui.bean.AreaItemBean;

import java.util.List;

import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/21 00:11
 * email:zsp872126510@gmail.com
 */
public class ItemAreaAdapter1 extends BaseQuickAdapter<AreaItemBean, BaseViewHolder> {
    public ItemAreaAdapter1(int layoutResId, @Nullable List<AreaItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaItemBean item) {
        helper.setText(R.id.tv_area, item.getAreaName());
        ZLog.d(item.getAreaName());
        helper.itemView.setOnClickListener(v -> {
            SPUtils.getInstance().put("areaId", item.getId() + "");
            SelectRxBusBean selectRxBusBean = new SelectRxBusBean(item.getId(), item.getParentId(), item.getAreaName());
            RxBus.getDefault().post(selectRxBusBean);
        });
    }
}
