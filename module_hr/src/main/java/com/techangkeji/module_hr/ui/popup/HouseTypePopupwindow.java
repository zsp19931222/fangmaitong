package com.techangkeji.module_hr.ui.popup;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techangkeji.module_hr.R;
import com.techangkeji.module_hr.ui.adapter.HouseTypeAdapter;
import com.techangkeji.module_hr.ui.adapter.TypeAdapter;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.view.MyVerticalDecoration;
import razerdp.basepopup.BasePopupWindow;

public class HouseTypePopupwindow extends BasePopupWindow {
    private HouseTypeAdapter adapter;
    private RecyclerView rv;
    private View v_pa;
    private Context context;

    public HouseTypePopupwindow(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_house_type);
    }

    private void init(Context context) {
        setPopupWindowFullScreen(false);
        setBackgroundColor(Color.parseColor("#00000000"));
        rv = findViewById(R.id.rv_it);
        v_pa = findViewById(R.id.v_fill);
        initAdapter();
        v_pa.getBackground().setAlpha(125);
        v_pa.setOnClickListener(view1 -> dismiss());
    }

    private void initAdapter() {
        List<String> strings = new ArrayList<>();
        strings.add("不限");
        strings.add("一室");
        strings.add("两室");
        strings.add("三室");
        strings.add("四室");
        strings.add("五室及以上");
        adapter = new HouseTypeAdapter(R.layout.item_area, strings);
        adapter.setSelectListener(position -> {
            RxBus.getDefault().post(strings.get(position));
            dismiss();
        });
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.addItemDecoration(new MyVerticalDecoration(context, ContextCompat.getColor(context, R.color.color_f6), 1, 0, 0, true));
        rv.setAdapter(adapter);
    }

}
