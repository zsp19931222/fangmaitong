package com.techangkeji.model_mine.ui.popup;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.techangkeji.model_mine.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * description:选择性别
 * author:created by Andy on 2019/9/16 21:09
 * email:zsp872126510@gmail.com
 */
public class SexPopupwindow extends BasePopupWindow {
    public SexPopupwindow(Context context) {
        super(context);
        TextView man = findViewById(R.id.tv_sex_man);
        TextView woman = findViewById(R.id.tv_sex_woman);
        man.setOnClickListener(v -> {
            onSelectSexListener.sex("男");
            dismiss();
        });
        woman.setOnClickListener(v -> {
            onSelectSexListener.sex("女");
            dismiss();
        });
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_sex);
    }

    public interface OnSelectSexListener {
        void sex(String sex);
    }

    public void setOnSelectSexListener(OnSelectSexListener onSelectSexListener) {
        this.onSelectSexListener = onSelectSexListener;
    }

    public OnSelectSexListener onSelectSexListener;
}
