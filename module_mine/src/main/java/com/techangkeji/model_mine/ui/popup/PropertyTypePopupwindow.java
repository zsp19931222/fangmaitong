package com.techangkeji.model_mine.ui.popup;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goldze.base.bean.FeaturedLabelBean;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.ui.adapter.PropertyTypeAdapter;

import java.util.List;

import razerdp.basepopup.BasePopupWindow;

public class PropertyTypePopupwindow extends BasePopupWindow {

    public PropertyTypePopupwindow(Context context, List<FeaturedLabelBean> list) {
        super(context);
        setBackgroundColor(Color.parseColor("#00000000"));
        RecyclerView recyclerView=findViewById(R.id.rv);
        PropertyTypeAdapter propertyTypeAdapter=new PropertyTypeAdapter(R.layout.item_property_type,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(propertyTypeAdapter);
        propertyTypeAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            for (FeaturedLabelBean featuredLabelBean : list) {
                featuredLabelBean.setSelect(false);
            }
            list.get(position).setSelect(true);
            propertyTypeAdapter.notifyDataSetChanged();
            selectListener.select(position);
            dismiss();
        });
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_property_type);
    }

    public interface OnSelectListener {
        void select(int position);
    }

    ;
    public OnSelectListener selectListener;

    public void setSelectListener(OnSelectListener selectListener) {
        this.selectListener = selectListener;
    }

}
