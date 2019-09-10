package com.techangkeji.module_hr.ui.popup;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techangkeji.module_hr.R;
import com.techangkeji.module_hr.ui.adapter.AreaAdapter;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.view.MyVerticalDecoration;
import razerdp.basepopup.BasePopupWindow;

public class AreaPopupwindow extends BasePopupWindow {
    private AreaAdapter adapter1, adapter2, adapter3;
    private RecyclerView rv_pa_1, rv_pa_2, rv_pa_3;
    private View v_pa;
    private Context context;

    public AreaPopupwindow(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_area);
    }

    private void init(Context context) {
        setBackgroundColor(Color.parseColor("#00000000"));
        rv_pa_1 = findViewById(R.id.rv_pa_1);
        rv_pa_2 = findViewById(R.id.rv_pa_2);
        rv_pa_3 = findViewById(R.id.rv_pa_3);
        v_pa = findViewById(R.id.v_fill);
        initAdapter1();
        initAdapter2();
        initAdapter3();
        v_pa.getBackground().setAlpha(125);
        v_pa.setOnClickListener(view1 -> dismiss());
    }

    private void initAdapter1() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("");
        }
        adapter1 = new AreaAdapter(R.layout.item_area, strings);
        rv_pa_1.setLayoutManager(new LinearLayoutManager(context));
        rv_pa_1.addItemDecoration(new MyVerticalDecoration(context, ContextCompat.getColor(context, R.color.color_f6), 1, 0, 0, true));
        rv_pa_1.setAdapter(adapter1);
    }

    private void initAdapter2() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("");
        }
        adapter2 = new AreaAdapter(R.layout.item_area, strings);
        rv_pa_2.setLayoutManager(new LinearLayoutManager(context));
        rv_pa_2.addItemDecoration(new MyVerticalDecoration(context, ContextCompat.getColor(context, R.color.color_f6), 1, 0, 0, true));
        rv_pa_2.setAdapter(adapter2);
    }

    private void initAdapter3() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("");
        }
        adapter3 = new AreaAdapter(R.layout.item_area, strings);
        rv_pa_3.setLayoutManager(new LinearLayoutManager(context));
        rv_pa_3.addItemDecoration(new MyVerticalDecoration(context, ContextCompat.getColor(context, R.color.color_f6), 1, 0, 0, true));
        rv_pa_3.setAdapter(adapter3);
    }
}
