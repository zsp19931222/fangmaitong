package com.techangkeji.module.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseBannerBean;
import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseSizeBean;
import com.techangkeji.module.R;
import com.techangkeji.module.ui.activity.CommentActivity;
import com.techangkeji.module.ui.activity.HouseSizeActivity;
import com.techangkeji.module.ui.activity.HouseStateActivity;
import com.techangkeji.module.ui.bean.HRDetailAdapterBean;
import com.techangkeji.module.ui.view_model.HRDetailViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 17:01
 * email:zsp872126510@gmail.com
 */
public class HRDetailAdapter extends BaseQuickAdapter<HRDetailAdapterBean, BaseViewHolder> {
    private Context context;
    private HRDetailViewModel viewModel;

    public HRDetailAdapter(@Nullable List<HRDetailAdapterBean> data, Context context, HRDetailViewModel viewModel) {
        super(data);
        this.context = context;
        this.viewModel = viewModel;
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
                for (HouseResourceReleaseBannerBean houseResourceReleaseBannerBean : viewModel.bannerPathList) {
                    images.add(houseResourceReleaseBannerBean.getImagePath());
                }
                BannerSetting.getInstance().setBanner(context, banner, images);
                helper.getView(R.id.tv_hrd_inform).setOnClickListener(view -> {
                    ARouter.getInstance().build(ARouterPath.Home.InformActivity).withString("listingId", viewModel.id + "").withString("listingName", viewModel.listingName.get()).navigation();
                });
                helper.setText(R.id.tv_hrd_name, viewModel.listingName.get());
                helper.setText(R.id.tv_hrd_time, viewModel.openTime.get());
                helper.setText(R.id.tv_hrd_price, "均价 " + viewModel.averagePrice.get() + "元/m²");
                helper.setText(R.id.tv_hrd_area, viewModel.address.get());
                helper.setText(R.id.tv_hrd_company, viewModel.developerName.get());
                helper.getView(R.id.tv_hrd_area).setOnClickListener(v -> {
                    Bundle bundle=new Bundle();
                    bundle.putString("longitude",viewModel.lon.get());
                    bundle.putString("latitude",viewModel.lat.get());
                    ARouter.getInstance().build(ARouterPath.Public.Location1Activity).withBundle("bundle",bundle).navigation();
                });
                break;
            case HRDetailAdapterBean.Notice:
                helper.setText(R.id.tv_yjgz, viewModel.commissionRule.get());
                helper.setText(R.id.tv_dkgz, viewModel.lookRule.get());
                if (LocalDataHelper.getInstance().getUserInfo().getRealNameAuthenticate() == 1) {//实名认证通过
                    helper.getView(R.id.smrz_yes).setVisibility(View.VISIBLE);
                    helper.getView(R.id.smrz_no).setVisibility(View.GONE);
                } else {
                    helper.getView(R.id.smrz_yes).setVisibility(View.GONE);
                    helper.getView(R.id.smrz_no).setVisibility(View.VISIBLE);
                }
                break;
            case HRDetailAdapterBean.Attache:
                RecyclerView recyclerViewAttache = helper.getView(R.id.rv_ha);
                HRDAttacheAdapter hrdAttacheAdapter = new HRDAttacheAdapter(R.layout.item_hrd_attache, viewModel.linkManList);
                recyclerViewAttache.setLayoutManager(new LinearLayoutManager(context));
                recyclerViewAttache.setAdapter(hrdAttacheAdapter);
                if (LocalDataHelper.getInstance().getUserInfo().getRealNameAuthenticate() == 1) {//实名认证通过
                    recyclerViewAttache.setVisibility(View.VISIBLE);
                    helper.getView(R.id.smrz_no).setVisibility(View.GONE);
                } else {
                    recyclerViewAttache.setVisibility(View.GONE);
                    helper.getView(R.id.smrz_no).setVisibility(View.VISIBLE);
                }
                break;
            case HRDetailAdapterBean.Detail:
                RecyclerView recyclerViewV = helper.getView(R.id.rv_hrdhd_horizontal);
                HRDDetailHorizontalAdapter horizontalAdapter = new HRDDetailHorizontalAdapter(R.layout.item_hrd_house_detail_horizontal, viewModel.labelList);
                recyclerViewV.setLayoutManager(new GridLayoutManager(context, 5));
                recyclerViewV.setAdapter(horizontalAdapter);

                List<HRDDetailVerticalAdapter.HRDDetailVerticalBean> strings1 = new ArrayList<>();
                strings1.add(new HRDDetailVerticalAdapter.HRDDetailVerticalBean("交房时间", viewModel.handTime.get()));
                strings1.add(new HRDDetailVerticalAdapter.HRDDetailVerticalBean("售楼处地址", viewModel.officeAddress.get()));
                strings1.add(new HRDDetailVerticalAdapter.HRDDetailVerticalBean("物业类型", viewModel.propertiesType.get()));
                strings1.add(new HRDDetailVerticalAdapter.HRDDetailVerticalBean("建筑类别", viewModel.buildType.get()));
                strings1.add(new HRDDetailVerticalAdapter.HRDDetailVerticalBean("装修状态", viewModel.decorationType.get()));
                strings1.add(new HRDDetailVerticalAdapter.HRDDetailVerticalBean("住户数", viewModel.resident.get()));
                strings1.add(new HRDDetailVerticalAdapter.HRDDetailVerticalBean("容积率", viewModel.volumeRate.get()));
                strings1.add(new HRDDetailVerticalAdapter.HRDDetailVerticalBean("车位数", viewModel.carNum.get()));
                strings1.add(new HRDDetailVerticalAdapter.HRDDetailVerticalBean("产权年限", viewModel.propertyYear.get()));
                strings1.add(new HRDDetailVerticalAdapter.HRDDetailVerticalBean("预售许可证号", viewModel.license.get()));
                strings1.add(new HRDDetailVerticalAdapter.HRDDetailVerticalBean("物业公司", viewModel.propertyCompany.get()));
                strings1.add(new HRDDetailVerticalAdapter.HRDDetailVerticalBean("物业公司", viewModel.propertyMoney.get() + "元"));
                RecyclerView recyclerViewH = helper.getView(R.id.rv_hrdhd_vertical);
                HRDDetailVerticalAdapter verticalAdapter = new HRDDetailVerticalAdapter(R.layout.item_hrd_house_detail_vertical, strings1);
                recyclerViewH.setLayoutManager(new LinearLayoutManager(context));
                recyclerViewH.setAdapter(verticalAdapter);
                break;
            case HRDetailAdapterBean.Size:
                RecyclerView recyclerViewHHS = helper.getView(R.id.rv_hhs);
                HRDSizeAdapter hrdSizeAdapter = new HRDSizeAdapter(R.layout.item_hrd_house_size, viewModel.sizeList);
                recyclerViewHHS.setLayoutManager(new GridLayoutManager(context, 4));
                recyclerViewHHS.setAdapter(hrdSizeAdapter);
                helper.getView(R.id.tv_more).setOnClickListener(view -> {
                    Intent intent = new Intent(context, HouseSizeActivity.class);
                    ArrayList<HouseResourceReleaseSizeBean> sizeBeans = new ArrayList<>(viewModel.sizeList);
                    intent.putExtra("sizeList", sizeBeans);
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