package com.techangkeji.model_home.ui.adapter;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.router.ARouterPath;
import com.goldze.base.utils.glide.GlideLoadUtils;
import com.techangkeji.model_home.R;

import java.util.List;

import me.goldze.mvvmhabit.http.net.entity.RecommendFriendEntity;

public class FriendRecommendAdapter extends BaseQuickAdapter<RecommendFriendEntity.DataBean, BaseViewHolder> {
    public FriendRecommendAdapter(int layoutResId, @Nullable List<RecommendFriendEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendFriendEntity.DataBean item) {
        GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(),item.getHeadUrl(),helper.getView(R.id.iv_ifr),0);
        if (item.getBrokerAuthenticate()==1){
            helper.setText(R.id.rtv_ifr_broker,"经纪人");
        }else if (item.getIdentity()==1){
            helper.setText(R.id.rtv_ifr_broker,"总代");
        }else {
            helper.setText(R.id.rtv_ifr_broker,"无身份");
        }
        helper.setText(R.id.iv_ifr_name,item.getRealName());
       if (item.getSex()==1){//男
           GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(),R.mipmap.woman_icon,helper.getView(R.id.iv_ifr_sex),0);
       }else if (item.getSex()==2){//女
           GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(),R.mipmap.woman_icon,helper.getView(R.id.iv_ifr_sex),0);
       }
        helper.itemView.setOnClickListener(view -> {
            ARouter.getInstance().build(ARouterPath.FriendCircle.CardActivity).withInt("id",item.getId()).navigation();
        });
    }
}
