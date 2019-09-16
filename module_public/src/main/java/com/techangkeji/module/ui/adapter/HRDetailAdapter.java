package com.techangkeji.module.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.goldze.base.router.ARouterPath;
import com.goldze.base.utils.BannerSetting;
import com.goldze.base.utils.ShareUtil;
import com.techangkeji.module.R;
import com.techangkeji.module.ui.activity.CommentActivity;
import com.techangkeji.module.ui.activity.HouseSizeActivity;
import com.techangkeji.module.ui.activity.HouseStateActivity;
import com.techangkeji.module.ui.bean.HRDetailAdapterBean;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 17:01
 * email:zsp872126510@gmail.com
 */
public class HRDetailAdapter extends BaseQuickAdapter<HRDetailAdapterBean, BaseViewHolder> {
    private Context context;

    public HRDetailAdapter(@Nullable List<HRDetailAdapterBean> data, Context context) {
        super(data);
        this.context = context;
        setMultiTypeDelegate(new MultiTypeDelegate<HRDetailAdapterBean>() {
            @Override
            protected int getItemType(HRDetailAdapterBean homeAdapterBean) {
                //根据你的实体类来判断布局类型
                return homeAdapterBean.getType();
            }
        });
        getMultiTypeDelegate()
                .registerItemType(HRDetailAdapterBean.Banner, R.layout.view_hrd_banner)
                .registerItemType(HRDetailAdapterBean.Notice, R.layout.view_hrd_notice)
                .registerItemType(HRDetailAdapterBean.Attache, R.layout.view_hrd_attache)
                .registerItemType(HRDetailAdapterBean.Detail, R.layout.view_hrd_house_detail)
                .registerItemType(HRDetailAdapterBean.Size, R.layout.view_hrd_house_size)
                .registerItemType(HRDetailAdapterBean.Site, R.layout.view_hrd_site)
                .registerItemType(HRDetailAdapterBean.State, R.layout.view_hrd_state)
                .registerItemType(HRDetailAdapterBean.Comment, R.layout.view_hrd_comment)
                .registerItemType(HRDetailAdapterBean.Recommend, R.layout.view_hrd_recommend)
        ;
    }

    @Override
    protected void convert(BaseViewHolder helper, HRDetailAdapterBean item) {
        switch (helper.getItemViewType()) {
            case HRDetailAdapterBean.Banner:
                ConvenientBanner banner = helper.getView(R.id.banner);
                List<Object> images = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    images.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=508387608,2848974022&fm=26&gp=0.jpg");
                }
                BannerSetting.getInstance().setBanner(context, banner, images);
                helper.getView(R.id.tv_hrd_inform).setOnClickListener(view -> {
                    ARouter.getInstance().build(ARouterPath.Home.InformActivity).navigation();
                });
                break;
            case HRDetailAdapterBean.Notice:
                break;
            case HRDetailAdapterBean.Attache:
                RecyclerView recyclerViewAttache = helper.getView(R.id.rv_ha);
                List<String> stringsAttache = new ArrayList<>();
                for (int i = 0; i < 11; i++) {
                    stringsAttache.add("");
                }
                HRDAttacheAdapter hrdAttacheAdapter = new HRDAttacheAdapter(R.layout.item_hrd_attache, stringsAttache);
                recyclerViewAttache.setLayoutManager(new LinearLayoutManager(context));
                recyclerViewAttache.setAdapter(hrdAttacheAdapter);
                break;
            case HRDetailAdapterBean.Detail:
                RecyclerView recyclerViewV = helper.getView(R.id.rv_hrdhd_horizontal);
                List<String> strings = new ArrayList<>();
                for (int i = 0; i < 11; i++) {
                    strings.add("");
                }
                HRDDetailHorizontalAdapter horizontalAdapter = new HRDDetailHorizontalAdapter(R.layout.item_hrd_house_detail_horizontal, strings);
                recyclerViewV.setLayoutManager(new GridLayoutManager(context, 5));
                recyclerViewV.setAdapter(horizontalAdapter);

                List<String> strings1 = new ArrayList<>();
                for (int i = 0; i < 11; i++) {
                    strings1.add("");
                }
                RecyclerView recyclerViewH = helper.getView(R.id.rv_hrdhd_vertical);
                HRDDetailVerticalAdapter verticalAdapter = new HRDDetailVerticalAdapter(R.layout.item_hrd_house_detail_vertical, strings1);
                recyclerViewH.setLayoutManager(new LinearLayoutManager(context));
                recyclerViewH.setAdapter(verticalAdapter);
                break;
            case HRDetailAdapterBean.Size:
                RecyclerView recyclerViewHHS = helper.getView(R.id.rv_hhs);
                List<String> stringsHHS = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    stringsHHS.add("");
                }
                HRDSizeAdapter hrdSizeAdapter = new HRDSizeAdapter(R.layout.item_hrd_house_size, stringsHHS);
                recyclerViewHHS.setLayoutManager(new GridLayoutManager(context, 4));
                recyclerViewHHS.setAdapter(hrdSizeAdapter);
                helper.getView(R.id.tv_more).setOnClickListener(view -> {
                    Intent intent = new Intent(context, HouseSizeActivity.class);
                    context.startActivity(intent);
                });
                break;
            case HRDetailAdapterBean.Site:
                break;
            case HRDetailAdapterBean.State:
                RecyclerView recyclerViewState = helper.getView(R.id.rv_hs);
                List<String> stringsState = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    stringsState.add("");
                }
                HRDStateAdapter hrdStateAdapter = new HRDStateAdapter(R.layout.item_hrd_state, stringsState);
                recyclerViewState.setLayoutManager(new LinearLayoutManager(context));
                recyclerViewState.setAdapter(hrdStateAdapter);
                helper.getView(R.id.tv_more).setOnClickListener(view -> {
                    Intent intent = new Intent(context, HouseStateActivity.class);
                    context.startActivity(intent);
                });
                break;
            case HRDetailAdapterBean.Comment:
                RecyclerView recyclerViewComment = helper.getView(R.id.rv_hc);
                List<String> stringsComment = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    stringsComment.add("");
                }
                HRDCommentAdapter hrdCommentAdapter = new HRDCommentAdapter(R.layout.item_hrd_comment, stringsComment);
                recyclerViewComment.setLayoutManager(new LinearLayoutManager(context));
                recyclerViewComment.setAdapter(hrdCommentAdapter);
                helper.getView(R.id.tv_more).setOnClickListener(view -> {
                    Intent intent = new Intent(context, CommentActivity.class);
                    context.startActivity(intent);
                });
                break;
            case HRDetailAdapterBean.Recommend:
                RecyclerView recyclerViewRecommend = helper.getView(R.id.rv_hr);
                List<String> stringsRecommend = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    stringsRecommend.add("");
                }
                HRDRecommendAdapter hrdRecommendAdapter = new HRDRecommendAdapter(R.layout.item_home_resource, stringsRecommend);
                recyclerViewRecommend.setLayoutManager(new LinearLayoutManager(context));
                recyclerViewRecommend.setAdapter(hrdRecommendAdapter);
                break;
        }
    }

}