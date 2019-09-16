package com.techangkeji.model_home.ui.popupwindow;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.techangkeji.model_home.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * description:
 * author:created by Andy on 2019/9/16 0016 17:13
 * email:zsp872126510@gmail.com
 */
public class InformPopup extends BasePopupWindow {
    private TextView tv_pi1;
    private TextView tv_pi2;
    private TextView tv_pi3;
    private TextView tv_pi4;
    private TextView tv_pi5;
    private TextView tv_pi6;
    public InformPopup(Context context) {
        super(context);
        setPopupWindowFullScreen(false);
        setBackgroundColor(Color.parseColor("#00000000"));
        tv_pi1=findViewById(R.id.tv_pi1);
        tv_pi2=findViewById(R.id.tv_pi2);
        tv_pi3=findViewById(R.id.tv_pi3);
        tv_pi4=findViewById(R.id.tv_pi4);
        tv_pi5=findViewById(R.id.tv_pi5);
        tv_pi6=findViewById(R.id.tv_pi6);

        tv_pi1.setOnClickListener(view -> {
            onSelectString.select(tv_pi1.getText().toString());
            dismiss();
        });
        tv_pi2.setOnClickListener(view -> {
            onSelectString.select(tv_pi2.getText().toString());
            dismiss();
        });
        tv_pi3.setOnClickListener(view -> {
            onSelectString.select(tv_pi3.getText().toString());
            dismiss();
        });
        tv_pi4.setOnClickListener(view -> {
            onSelectString.select(tv_pi4.getText().toString());
            dismiss();
        });
        tv_pi5.setOnClickListener(view -> {
            onSelectString.select(tv_pi5.getText().toString());
            dismiss();
        });
        tv_pi6.setOnClickListener(view -> {
            onSelectString.select(tv_pi6.getText().toString());
            dismiss();
        });
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_inform);
    }

    public interface OnSelectString{
        void select(String select);
    }

    public OnSelectString onSelectString;

    public void setOnSelectString(OnSelectString onSelectString) {
        this.onSelectString = onSelectString;
    }
}
