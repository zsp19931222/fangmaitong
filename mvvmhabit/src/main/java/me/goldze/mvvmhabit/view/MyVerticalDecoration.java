package me.goldze.mvvmhabit.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;


/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class MyVerticalDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private Context context;
    private int mDivider;
    private int marginStart = 0;
    private int marginEnd = 0;
    private boolean showHeadAndEnd=false;

    public MyVerticalDecoration(Context context, int color, int mDivider) {
        this.context = context;
        this.mDivider = mDivider;
        mPaint = new Paint();
        mPaint.setColor(color);
    }

    public MyVerticalDecoration(Context context, int color, int mDivider, int marginStart, int marginEnd) {
        this.context = context;
        this.mDivider = mDivider;
        this.marginStart = marginStart;
        this.marginEnd = marginEnd;
        mPaint = new Paint();
        mPaint.setColor(color);
    }
    public MyVerticalDecoration(Context context, int color, int mDivider, int marginStart, int marginEnd, boolean showHeadAndEnd) {
        this.context = context;
        this.mDivider = mDivider;
        this.marginStart = marginStart;
        this.marginEnd = marginEnd;
        this.showHeadAndEnd = showHeadAndEnd;
        mPaint = new Paint();
        mPaint.setColor(color);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();//获取item数目
        int startIndex=0;
        if (!showHeadAndEnd){
            startIndex=1;
        }
        for (int i = startIndex; i < childCount; i++) {
            // 获取每个Item的位置
            final View child = parent.getChildAt(i);
            // 获取布局参数
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            // 设置矩形(分割线)的宽度为10px

            // 根据子视图的位置 & 间隔区域，设置矩形（分割线）的2个顶点坐标(左上 & 右下)

            // 矩形左上顶点 = (ItemView的左边界,ItemView的下边界)
            // ItemView的左边界 = RecyclerView 的左边界 + paddingLeft距离 后的位置
            final int left = parent.getPaddingLeft();
            // ItemView的下边界：ItemView 的 bottom坐标 + 距离RecyclerView底部距离 +translationY
            final int top = child.getBottom() + params.bottomMargin +
                    Math.round(ViewCompat.getTranslationY(child));

            // 矩形右下顶点 = (ItemView的右边界,矩形的下边界)
            // ItemView的右边界 = RecyclerView 的右边界减去 paddingRight 后的坐标位置
            final int right = parent.getWidth() - parent.getPaddingRight();
            // 绘制分割线的下边界 = ItemView的下边界+分割线的高度
            final int bottom = top + SizeUtils.dp2px(mDivider);

            // 通过Canvas绘制矩形（分割线）
            c.drawRect(left + SizeUtils.dp2px(marginStart), top, right-SizeUtils.dp2px(marginEnd), bottom, mPaint);

        }
    }
}
