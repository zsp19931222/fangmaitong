package com.goldze.base.utils;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.goldze.base.R;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/7/8 0008 13:44
 * email:zsp872126510@gmail.com
 */
public class BannerSetting {
    private static final BannerSetting ourInstance = new BannerSetting();

    public static BannerSetting getInstance() {
        return ourInstance;
    }

    private BannerSetting() {

    }

    /**
     * description:
     * author: Andy
     * date: 2019/7/8 0008 10:17
     */
    public void setBanner(Context context, ConvenientBanner banner, List<Object> images) {
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new LocalImageHolderView(itemView, context);
            }

            @Override
            public int getLayoutId() {
                return R.layout.home_banner_image;
            }
        }, images)
                //设置自动切换
                .setCanLoop(true)
                //设置指示器是否可见
                .setPointViewVisible(true)
                //设置自动切换（同时设置了切换时间间隔）
                .startTurning(5000)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
//                .setPageIndicator(new int[]{R.mipmap.banner_select, R.mipmap.banner_selected})
                //设置指示器的方向（左、中、右）
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(position -> {
                })
        ;
    }

    public class LocalImageHolderView extends Holder<Object> {
        private ImageView imageView;
        private Context context;

        public LocalImageHolderView(View itemView, Context context) {
            super(itemView);
            this.context = context;
        }

        @Override
        protected void initView(View itemView) {
            imageView = itemView.findViewById(R.id.banner_image);
        }

        @Override
        public void updateUI(Object data) {
            if (!isDestroy((Activity) context)) {
                Glide.with(context).load(data).into(imageView);
            }
        }

        //判断Activity是否Destroy
        public boolean isDestroy(Activity activity) {
            return activity == null || activity.isFinishing() || activity.isDestroyed();
        }
    }
}
