package com.goldze.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.goldze.base.R;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/8/6 0006 17:48
 * email:zsp872126510@gmail.com
 */
public class TitleVIew extends RelativeLayout {
    private RelativeLayout title_left_rv;
    private ImageView title_left_iv;
    private TextView title_tv;
    private TextView title_right_tv;

    public TitleVIew(Context context) {
        super(context);
    }

    public TitleVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        try {
            BaseActivity baseActivity = (BaseActivity) context;
            LayoutInflater.from(context).inflate(R.layout.view_title, this, true);
            title_left_rv = findViewById(R.id.title_left_rv);
            title_left_iv = findViewById(R.id.title_left_iv);
            title_tv = findViewById(R.id.title_tv);
            title_right_tv = findViewById(R.id.title_right_tv);
            title_left_rv.setOnClickListener(view -> baseActivity.finish());
        } catch (Exception e) {
            ZLog.d(e);
        }
    }

    public void setTitle(String title) {
        title_tv.setText(title);
    }

    public void setRightText(String text) {
        title_right_tv.setText(text);
    }

    public void setRightOnclickListener(OnClickListener rightOnclickListener) {
        title_right_tv.setOnClickListener(rightOnclickListener);

    }
}
