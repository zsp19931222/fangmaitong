package com.techangkeji.model_mine.ui.popup;

import android.content.Context;
import android.view.View;

import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.ui.viewModel.HouseResourceReleaseViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.view.shape.RadiusTextView;
import razerdp.basepopup.BasePopupWindow;

/**
 * description:
 * author:created by Andy on 2019/9/12 0012 11:28
 * email:zsp872126510@gmail.com
 */
public class HRSDLabelPopupwindow extends BasePopupWindow {
    private boolean tv_phl_car_lookABoolean = false;
    private boolean tv_phl_video_lookABoolean = false;
    private boolean tv_phl_soloABoolean = false;
    private boolean tv_phl_readyABoolean = false;
    private boolean tv_phl_brandABoolean = false;
    private HouseResourceReleaseViewModel viewModel;
    private List<String> selectList = new ArrayList<>();

    public HRSDLabelPopupwindow(Context context, HouseResourceReleaseViewModel viewModel) {
        super(context);
        this.viewModel = viewModel;
        RadiusTextView tv_phl_car_look = findViewById(R.id.tv_phl_car_look);
        RadiusTextView tv_phl_video_look = findViewById(R.id.tv_phl_video_look);
        RadiusTextView tv_phl_solo = findViewById(R.id.tv_phl_solo);
        RadiusTextView tv_phl_ready = findViewById(R.id.tv_phl_ready);
        RadiusTextView tv_phl_brand = findViewById(R.id.tv_phl_brand);
        RadiusTextView tv_phl_delete = findViewById(R.id.tv_phl_delete);
        RadiusTextView tv_phl_select = findViewById(R.id.tv_phl_select);
        tv_phl_delete.setOnClickListener(view -> dismiss());
        for (String s : viewModel.labelList) {
            if (tv_phl_car_look.getText().equals(s)){
                tv_phl_car_lookABoolean=true;
                selectList.add(s);
            }
            if (tv_phl_video_look.getText().equals(s)){
                tv_phl_video_lookABoolean=true;
                selectList.add(s);
            }
            if (tv_phl_solo.getText().equals(s)){
                tv_phl_soloABoolean=true;
                selectList.add(s);
            }
            if (tv_phl_ready.getText().equals(s)){
                tv_phl_readyABoolean=true;
                selectList.add(s);
            }
            if (tv_phl_brand.getText().equals(s)){
                tv_phl_brandABoolean=true;
                selectList.add(s);
            }
            tv_phl_car_look.setSelected(tv_phl_car_lookABoolean);
            tv_phl_video_look.setSelected(tv_phl_video_lookABoolean);
            tv_phl_solo.setSelected(tv_phl_soloABoolean);
            tv_phl_ready.setSelected(tv_phl_readyABoolean);
            tv_phl_brand.setSelected(tv_phl_brandABoolean);
        }
        tv_phl_car_look.setOnClickListener(view -> {
            if (tv_phl_car_lookABoolean) {
                tv_phl_car_lookABoolean = false;
                String s = (String) tv_phl_car_look.getText();
                selectList.remove(s);
                tv_phl_car_look.setSelected(tv_phl_car_lookABoolean);
            } else {
                tv_phl_car_lookABoolean = true;
                String s = (String) tv_phl_car_look.getText();
                selectList.add(s);
                tv_phl_car_look.setSelected(tv_phl_car_lookABoolean);
            }
        });
        tv_phl_video_look.setOnClickListener(view -> {
            if (tv_phl_video_lookABoolean) {
                tv_phl_video_lookABoolean = false;
                String s = (String) tv_phl_video_look.getText();
                selectList.remove(s);
                tv_phl_video_look.setSelected(tv_phl_video_lookABoolean);
            } else {
                tv_phl_video_lookABoolean = true;
                String s = (String) tv_phl_video_look.getText();
                selectList.add(s);
                tv_phl_video_look.setSelected(tv_phl_video_lookABoolean);
            }
        });
        tv_phl_solo.setOnClickListener(view -> {
            if (tv_phl_soloABoolean) {
                tv_phl_soloABoolean = false;
                String s = (String) tv_phl_solo.getText();
                selectList.remove(s);
                tv_phl_solo.setSelected(tv_phl_soloABoolean);
            } else {
                tv_phl_soloABoolean = true;
                String s = (String) tv_phl_solo.getText();
                selectList.add(s);
                tv_phl_solo.setSelected(tv_phl_soloABoolean);
            }
        });
        tv_phl_ready.setOnClickListener(view -> {
            if (tv_phl_readyABoolean) {
                tv_phl_readyABoolean = false;
                String s = (String) tv_phl_ready.getText();
                selectList.remove(s);
                tv_phl_ready.setSelected(tv_phl_readyABoolean);
            } else {
                tv_phl_readyABoolean = true;
                String s = (String) tv_phl_ready.getText();
                selectList.add(s);
                tv_phl_ready.setSelected(tv_phl_readyABoolean);
            }
        });
        tv_phl_brand.setOnClickListener(view -> {
            if (tv_phl_brandABoolean) {
                tv_phl_brandABoolean = false;
                String s = (String) tv_phl_brand.getText();
                selectList.remove(s);
                tv_phl_brand.setSelected(tv_phl_brandABoolean);
            } else {
                tv_phl_brandABoolean = true;
                String s = (String) tv_phl_brand.getText();
                selectList.add(s);
                tv_phl_brand.setSelected(tv_phl_brandABoolean);
            }
        });
        tv_phl_select.setOnClickListener(view -> {
            viewModel.labelList.clear();
            viewModel.labelList.addAll(selectList);
            viewModel.adapterObservableField.get().notifyDataSetChanged();
            dismiss();
        });
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_hsrd_label);
    }

    /**
     * description: 是否选中
     * author: Andy
     * date: 2019/9/12 0012 11:47
     */


}
