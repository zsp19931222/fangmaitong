package com.techangkeji.module_main.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.utils.IsNullUtil;


/**
 * 首页底部 tab（仿微信效果）
 */
public class TabGroupView extends LinearLayout implements ViewPager.OnPageChangeListener {

    private List<TabView> mTabViews;
    private ViewPager mViewPager;

    public TabGroupView(Context context) {
        this(context, null);
    }

    public TabGroupView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置默认为水平布局
        setOrientation(HORIZONTAL);
        mTabViews = new ArrayList<>();
    }


    /**
     * 绑定ViewPager
     */
    public void setViewPager(ViewPager vp) {
        this.mViewPager = vp;
        mViewPager.addOnPageChangeListener(this);
    }

    /**
     * 清除选中状态
     */
    private void clearSelected() {
        for (TabView tabLayout : mTabViews) {
            tabLayout.setChecked(false);
        }
    }

    /**
     * 设置选中某一个
     *
     * @param item
     */
    public void setCurrentItem(int item) {
        clearSelected();
        TabView tabLayout = mTabViews.get(item);
        tabLayout.setChecked(true);
    }

    /**
     * 设置是否有更新
     *
     * @param position
     * @param hasNew
     */
    public void setHasNew(int position, boolean hasNew) {
        TabView tabView = (TabView) getChildAt(position);
        if (tabView != null) {
            tabView.setHasNew(hasNew);
        }
    }

    /**
     * 设置未读数
     *
     * @param position
     * @param count
     */
    public void setUnreadCount(int position, int count) {
        TabView tabView = (TabView) getChildAt(position);
        if (tabView != null) {
            tabView.setUnreadCount(count);
        }
    }

    /**
     * description: 设置是否有新消息
     * author: Andy
     * date: 2019/8/23 0023 11:31
     */
    public void setHasNewMessage(int position, boolean has) {
        TabView tabView = (TabView) getChildAt(position);
        if (IsNullUtil.getInstance().isEmpty(tabView)) return;
        tabView.hasNewMessage(has);
    }

    /**
     * 当viewpager滑动设置tab图片文字透明度
     *
     * @param position       当前界面索引
     * @param positionOffset 滑动的百分比
     */
    public void onScrolling(int position, float positionOffset) {
        if (positionOffset > 0) {
            TabView tabView = mTabViews.get(position);
            tabView.onScrolling(1 - positionOffset);
            if (position + 1 < mTabViews.size()) {
                TabView nextTabView = mTabViews.get(position + 1);
                nextTabView.onScrolling(positionOffset);
            }
        }
    }

    /**
     * 设置只能水平布局
     *
     * @param orientation
     */
    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(HORIZONTAL);
    }

    private OnItemClickListener onItemClickListener;

    /**
     * 设置监听事件
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        initListener();
    }

    /**
     * 设置监听器
     */
    private void initListener() {
        int count = this.getChildCount();
        for (int i = 0; i < count; i++) {
            final TabView tabView = (TabView) this.getChildAt(i);
            mTabViews.add(tabView);
            final int finalI = i;
            tabView.setOnClickListener(v -> {
                clearSelected();
                tabView.setChecked(true);
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(tabView, finalI);
                }
            });
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        this.onScrolling(position, positionOffset);
    }

    @Override
    public void onPageSelected(int position) {
        this.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    /**
     * 点击事件
     */
    public interface OnItemClickListener {
        void onClick(TabView tabLayout, int position);
    }
}
