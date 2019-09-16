package com.techangkeji.model_message.ui.adapter;

import android.Manifest;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.utils.PermissionsUtils;
import com.goldze.base.utils.glide.GlideLoadUtils;
import com.techangkeji.model_message.R;
import com.techangkeji.model_message.ui.bean.ReleaseInformationBean;

import java.util.List;

import static com.goldze.base.constant.RxBusMessageEventConstants.OPEN_GALLERY;

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
                PermissionsUtils.getInstance().getPermissionsWithFragmentActivity((FragmentActivity) helper.itemView.getContext(),OPEN_GALLERY, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        });

    }
}
