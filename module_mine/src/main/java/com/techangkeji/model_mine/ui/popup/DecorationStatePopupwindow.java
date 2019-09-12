package com.techangkeji.model_mine.ui.popup;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.techangkeji.model_mine.R;

import razerdp.basepopup.BasePopupWindow;

public class DecorationStatePopupwindow extends BasePopupWindow {

    public DecorationStatePopupwindow(Context context, String s) {
        super(context);
        setBackgroundColor(Color.parseColor("#00000000"));
        TextView tv_pt1 = findViewById(R.id.tv_pt1);
        TextView tv_pt2 = findViewById(R.id.tv_pt2);
        TextView tv_pt3 = findViewById(R.id.tv_pt3);


        View v_pt1 = findViewById(R.id.v_pt1);
        View v_pt2 = findViewById(R.id.v_pt2);
        View v_pt3 = findViewById(R.id.v_pt3);

        if (s.equals(tv_pt1.getText().toString())){
            v_pt1.setVisibility(View.VISIBLE);
            tv_pt1.setTextColor(ContextCompat.getColor(context,R.color.color_FF8C00));
        }
        if (s.equals(tv_pt2.getText().toString())){
            v_pt2.setVisibility(View.VISIBLE);
            tv_pt2.setTextColor(ContextCompat.getColor(context,R.color.color_FF8C00));
        }
        if (s.equals(tv_pt3.getText().toString())){
            v_pt3.setVisibility(View.VISIBLE);
            tv_pt3.setTextColor(ContextCompat.getColor(context,R.color.color_FF8C00));
        }

        tv_pt1.setOnClickListener(view -> {
            select(tv_pt1);
        });
        tv_pt2.setOnClickListener(view -> {
            select(tv_pt2);
        });
        tv_pt3.setOnClickListener(view -> {
            select(tv_pt3);
        });
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_decoration_state);
    }

    public interface OnSelectListener {
        void select(String s);
    }

    ;
    public OnSelectListener selectListener;

    public void setSelectListener(OnSelectListener selectListener) {
        this.selectListener = selectListener;
    }

    private void select(TextView view){
        selectListener.select(view.getText().toString());
        dismiss();
    }

}
