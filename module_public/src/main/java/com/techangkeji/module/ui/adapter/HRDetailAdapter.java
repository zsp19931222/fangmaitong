package com.techangkeji.module.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.collection.LruCache;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.goldze.base.constant.RxBusMessageEventConstants;
import com.goldze.base.router.ARouterPath;
import com.goldze.base.utils.BannerSetting;
import com.goldze.base.utils.ShareUtil;
import com.goldze.base.utils.glide.GlideLoadUtils;
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

import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 17:01
 * email:zsp872126510@gmail.com
 */
public class HRDetailAdapter extends BaseQuickAdapter<HRDetailAdapterBean, BaseViewHolder> {
    private Context context;
    private HRDetailViewModel viewModel;
    private LinearLayout ll_vha;
    private LinearLayout ll_vhn;

    /**
     * 用于对外暴露convert方法,构造缓存视图(截屏用)
     *
     * @param viewHolder
     * @param t
     */
    public void startConvert(BaseViewHolder viewHolder, HRDetailAdapterBean t) {
        convert(viewHolder, t);
    }

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

    private boolean isBannerSetting = false;

    @Override
    protected void convert(BaseViewHolder helper, HRDetailAdapterBean item) {
        switch (helper.getItemViewType()) {
            case HRDetailAdapterBean.Banner:
                ImageView collectIv = helper.getView(R.id.tv_hrd_collect);
                if (viewModel.isCollect) {
                    GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.collect, collectIv, 0);
                    collectIv.setOnClickListener(v -> viewModel.deleteCollection());
                } else {
                    GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(), R.mipmap.no_collect, collectIv, 0);
                    collectIv.setOnClickListener(v -> viewModel.addCollection());
                }
                ConvenientBanner banner = helper.getView(R.id.banner);
                List<Object> images = new ArrayList<>();
                for (HouseResourceReleaseBannerBean houseResourceReleaseBannerBean : viewModel.bannerPathList) {
                    images.add(houseResourceReleaseBannerBean.getImagePath());
                }
                if (images.size() > 0 && !isBannerSetting) {
                    isBannerSetting = true;
                    BannerSetting.getInstance().setBanner(context, banner, images);
                }
                helper.getView(R.id.tv_hrd_inform).setOnClickListener(view -> {
                    ARouter.getInstance().build(ARouterPath.Home.InformActivity).withString("listingId", viewModel.id + "").withString("listingName", viewModel.listingName.get()).navigation();
                });
                helper.setText(R.id.tv_hrd_name, viewModel.listingName.get());
                helper.setText(R.id.tv_hrd_time, viewModel.openTime.get());
                helper.setText(R.id.tv_hrd_price, "均价 " + viewModel.averagePrice.get() + "元/m²");
                helper.setText(R.id.tv_hrd_area, viewModel.address.get());
                helper.setText(R.id.tv_hrd_company, viewModel.developerName.get());
                helper.getView(R.id.tv_hrd_area).setOnClickListener(v -> {
                    Bundle bundle = new Bundle();
                    bundle.putString("longitude", viewModel.lon.get());
                    bundle.putString("latitude", viewModel.lat.get());
                    ARouter.getInstance().build(ARouterPath.Public.Location1Activity).withBundle("bundle", bundle).navigation();
                });
                break;
            case HRDetailAdapterBean.Notice:
                ll_vhn = helper.getView(R.id.ll_vhn);
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
                ll_vha = helper.getView(R.id.ll_vha);
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
                GlideLoadUtils.getInstance().glideLoad(context, viewModel.baiduImageUrl.get(), helper.getView(R.id.iv_baiduMap), 0);
                break;
            case HRDetailAdapterBean.State:
                RecyclerView recyclerViewState = helper.getView(R.id.rv_hs);
                HRDStateAdapter hrdStateAdapter = new HRDStateAdapter(R.layout.item_hrd_state, viewModel.stateList);
                viewModel.stateAdapter.set(hrdStateAdapter);
                recyclerViewState.setLayoutManager(new LinearLayoutManager(context));
                recyclerViewState.setAdapter(hrdStateAdapter);
                helper.getView(R.id.tv_more).setOnClickListener(view -> {
                    Intent intent = new Intent(context, HouseStateActivity.class);
                    intent.putExtra("listingName", viewModel.listingName.get());
                    intent.putExtra("id", viewModel.id);
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
                HRDRecommendAdapter hrdRecommendAdapter = new HRDRecommendAdapter(R.layout.item_public_home_resource, viewModel.recommendBuildingList);
                viewModel.recommendAdapter.set(hrdRecommendAdapter);
                recyclerViewRecommend.setLayoutManager(new LinearLayoutManager(context));
                recyclerViewRecommend.setAdapter(hrdRecommendAdapter);

                helper.getView(R.id.tv_hr_share).setOnClickListener(view -> {
                            setVisibility(false,ll_vhn);
                            setVisibility(false,ll_vha);
                            screenShotRecycleView(viewModel.recyclerView.get(), bitmap -> ShareUtil.getInstance().share(context, bitmap));
                        }
                );
                helper.getView(R.id.tv_more).setOnClickListener(view -> {
                    viewModel.finish();
                    new Handler().postDelayed(() -> RxBus.getDefault().post(RxBusMessageEventConstants.ZZFY), 500);
                });
                break;
        }
    }

    //防止隐藏item出现空白
    private void setVisibility(boolean isVisible, View itemView) {
        RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        if (isVisible) {
            param.height = LinearLayout.LayoutParams.WRAP_CONTENT;// 这里注意使用自己布局的根布局类型
            param.width = LinearLayout.LayoutParams.MATCH_PARENT;// 这里注意使用自己布局的根布局类型
            itemView.setVisibility(View.VISIBLE);
        } else {
            itemView.setVisibility(View.GONE);
            param.height = 0;
            param.width = 0;
        }
        itemView.setLayoutParams(param);
    }

    /**
    * description:
    * author: Andy
    * date: 2019/10/9 0009 15:54
    */
    public void setShow(){
        setVisibility(true,ll_vhn);
        setVisibility(true,ll_vha);
    }

    /**
     * description: 异步截图保证所有图片都能显示
     * author: Andy
     * date: 2019/10/9 0009 14:27
     */
    private void screenShotRecycleView(final RecyclerView mRecyclerView, final RecycleViewRecCallback callBack) {
        if (mRecyclerView == null) {
            return;
        }
        HRDetailAdapter adapter = (HRDetailAdapter) mRecyclerView.getAdapter();
        final Paint paint = new Paint();
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;
        LruCache<String, Bitmap> bitmaCache = new LruCache<>(cacheSize);
        final int oneScreenHeight = mRecyclerView.getMeasuredHeight();
        int shotHeight = 0;
        if (adapter != null && adapter.getData().size() > 0) {
            int headerSize = adapter.getHeaderLayoutCount();
            int dataSize = adapter.getData().size();
            for (int i = 0; i < headerSize + dataSize; i++) {
                BaseViewHolder holder = adapter.createViewHolder(mRecyclerView, adapter.getItemViewType(i));
                ZLog.d(holder + "----->" + i);
                if (i >= headerSize)
                    adapter.startConvert(holder, adapter.getData().get(i - headerSize));
                holder.itemView.measure(
                        View.MeasureSpec.makeMeasureSpec(mRecyclerView.getWidth(), View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(), holder.itemView.getMeasuredHeight());
                holder.itemView.setDrawingCacheEnabled(true);
                holder.itemView.buildDrawingCache();
                Bitmap drawingCache = holder.itemView.getDrawingCache();
                //holder.itemView.destroyDrawingCache();//释放缓存占用的资源
                if (drawingCache != null) {
                    bitmaCache.put(String.valueOf(i), drawingCache);
                }
                shotHeight += holder.itemView.getHeight();
                if (shotHeight > 12000) {
                    //设置截图最大值
                    if (callBack != null)
                        callBack.onRecFinished(null);
                    return;
                }
            }
            //添加底部高度(加载更多或loading布局高度,此处为固定值:)
            final int footHight = SizeUtils.dp2px(42);
            shotHeight += footHight;

            //返回到顶部
            while (mRecyclerView.canScrollVertically(-1)) {
                mRecyclerView.scrollBy(0, -oneScreenHeight);
            }

            //绘制截图的背景
            final Bitmap bigBitmap = Bitmap.createBitmap(mRecyclerView.getMeasuredWidth(), shotHeight, Bitmap.Config.ARGB_8888);
            final Canvas bigCanvas = new Canvas(bigBitmap);
            Drawable lBackground = mRecyclerView.getBackground();
            if (lBackground instanceof ColorDrawable) {
                ColorDrawable lColorDrawable = (ColorDrawable) lBackground;
                int lColor = lColorDrawable.getColor();
                bigCanvas.drawColor(lColor);
            }


            final int[] drawOffset = {0};
            final Canvas canvas = new Canvas();
            if (shotHeight <= oneScreenHeight) {
                //仅有一页
                Bitmap bitmap = Bitmap.createBitmap(mRecyclerView.getWidth(), mRecyclerView.getHeight(), Bitmap.Config.ARGB_8888);
                canvas.setBitmap(bitmap);
                mRecyclerView.draw(canvas);
                if (callBack != null)
                    callBack.onRecFinished(bitmap);
            } else {
                //超过一页
                final int finalShotHeight = shotHeight;
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if ((drawOffset[0] + oneScreenHeight < finalShotHeight)) {
                            //超过一屏
                            Bitmap bitmap = Bitmap.createBitmap(mRecyclerView.getWidth(), mRecyclerView.getHeight(), Bitmap.Config.ARGB_8888);
                            canvas.setBitmap(bitmap);
                            mRecyclerView.draw(canvas);
                            bigCanvas.drawBitmap(bitmap, 0, drawOffset[0], paint);
                            drawOffset[0] += oneScreenHeight;
                            mRecyclerView.scrollBy(0, oneScreenHeight);
                            try {
                                bitmap.recycle();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            mRecyclerView.postDelayed(this, 10);
                        } else {
                            //不足一屏时的处理
                            int leftHeight = finalShotHeight - drawOffset[0] - footHight;
                            mRecyclerView.scrollBy(0, leftHeight);
                            int top = oneScreenHeight - (finalShotHeight - drawOffset[0]);
                            if (top > 0 && leftHeight > 0) {
                                Bitmap bitmap = Bitmap.createBitmap(mRecyclerView.getWidth(), mRecyclerView.getHeight(), Bitmap.Config.ARGB_8888);
                                canvas.setBitmap(bitmap);
                                mRecyclerView.draw(canvas);
                                //截图,只要补足的那块图
                                bitmap = Bitmap.createBitmap(bitmap, 0, top, bitmap.getWidth(), leftHeight, null, false);
                                bigCanvas.drawBitmap(bitmap, 0, drawOffset[0], paint);
                                try {
                                    bitmap.recycle();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            if (callBack != null)
                                callBack.onRecFinished(bigBitmap);
                        }
                    }
                }, 10);
            }
        }
    }


    public interface RecycleViewRecCallback {
        void onRecFinished(Bitmap bitmap);
    }

}