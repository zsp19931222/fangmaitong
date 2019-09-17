package com.techangkeji.model_message.ui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.utils.glide.GlideLoadUtils;
import com.techangkeji.model_message.R;
import com.techangkeji.model_message.ui.bean.ReleaseInformationBean;

import java.util.List;

import me.goldze.mvvmhabit.bus.RxBus;

/**
 * description:发布信息
 * author:created by Andy on 2019/9/16 21:38
 * email:zsp872126510@gmail.com
 */
public class ReleaseInformationAdapter extends BaseQuickAdapter<ReleaseInformationBean, BaseViewHolder> {
    public ReleaseInformationAdapter(int layoutResId, @Nullable List<ReleaseInformationBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReleaseInformationBean item) {
        GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(),item.getImage(),helper.getView(R.id.iv_iri),0);
        helper.itemView.setOnClickListener(v -> {
            if (item.isCanAdd()){
                RxBus.getDefault().post(new ReleaseInformationAdapterRxBen());
            }
        });

    }

    public class ReleaseInformationAdapterRxBen{

    }
}
