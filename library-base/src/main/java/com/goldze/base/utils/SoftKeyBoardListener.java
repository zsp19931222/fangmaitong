package com.goldze.base.utils;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/10/8 0008 11:32
 * email:zsp872126510@gmail.com
 */
public class SoftKeyBoardListener {
    private static final SoftKeyBoardListener ourInstance = new SoftKeyBoardListener();

    public static SoftKeyBoardListener getInstance() {
        return ourInstance;
    }

    private SoftKeyBoardListener() {
    }

    private View rootView;//activity的根视图
    int rootViewVisibleHeight;//纪录根视图的显示高度
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    private OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener;

    private SoftKeyBoardListener(View view) {
        //获取activity的根视图
        rootView = view;
        //监听视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变
        onGlobalLayoutListener = () -> {
            //获取当前根视图在屏幕上显示的大小
            Rect r = new Rect();
            rootView.getWindowVisibleDisplayFrame(r);

            int visibleHeight = r.height();
            ZLog.d(visibleHeight);
            ZLog.d(rootViewVisibleHeight);
            if (rootViewVisibleHeight == 0) {
                if (onSoftKeyBoardChangeListener != null) {
                    onSoftKeyBoardChangeListener.keyBoardShow(rootViewVisibleHeight - visibleHeight);
                }
                rootViewVisibleHeight = visibleHeight;
                return;
            }

            //根视图显示高度没有变化，可以看作软键盘显示／隐藏状态没有改变
            if (rootViewVisibleHeight == visibleHeight) {
                return;
            }

            //根视图显示高度变小超过200，可以看作软键盘显示了
            if (rootViewVisibleHeight - visibleHeight > 200) {
                if (onSoftKeyBoardChangeListener != null) {
                    onSoftKeyBoardChangeListener.keyBoardShow(rootViewVisibleHeight - visibleHeight);
                }
                rootViewVisibleHeight = visibleHeight;
                return;
            }

            //根视图显示高度变大超过200，可以看作软键盘隐藏了
            if (visibleHeight - rootViewVisibleHeight > 200) {
                if (onSoftKeyBoardChangeListener != null) {
                    onSoftKeyBoardChangeListener.keyBoardHide(visibleHeight - rootViewVisibleHeight);
                }
                rootViewVisibleHeight = visibleHeight;
                return;
            }

        };
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    private void setOnSoftKeyBoardChangeListener(OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        this.onSoftKeyBoardChangeListener = onSoftKeyBoardChangeListener;
    }

    public interface OnSoftKeyBoardChangeListener {
        void keyBoardShow(int height);

        void keyBoardHide(int height);
    }

    private SoftKeyBoardListener softKeyBoardListener;

    public void setListener(View view, OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        if (softKeyBoardListener==null) {
            softKeyBoardListener = new SoftKeyBoardListener(view);
            softKeyBoardListener.setOnSoftKeyBoardChangeListener(onSoftKeyBoardChangeListener);
        }
    }

}
