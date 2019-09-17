package com.techangkeji.model_login.ui.popupwindow;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.techangkeji.model_login.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * description:注册成功提示
 * author:created by Andy on 2019/9/17 0017 12:07
 * email:zsp872126510@gmail.com
 */
public class PerfectionUserInfoPopup extends BasePopupWindow {
    public PerfectionUserInfoPopup(Context context) {
        super(context);
        TextView confirm = findViewById(R.id.tv_ppu_confirm);
        confirm.setOnClickListener(view -> {
            onConfirmListener.confirm();
            dismiss();
        });
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_perfection_userinfo);
    }

    public interface OnConfirmListener {
        void confirm();
    }

    private OnConfirmListener onConfirmListener;

    public void setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
    }
}
