package com.techangkeji.module_hr.ui.popup;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techangkeji.module_hr.R;
import com.techangkeji.module_hr.ui.adapter.SortAdapter;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.view.MyVerticalDecoration;
import razerdp.basepopup.BasePopupWindow;

public class SortPopupwindow extends BasePopupWindow {
    private SortAdapter adapter;
    private RecyclerView rv;
    private View v_pa;
    private Context context;

    public SortPopupwindow(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_type);
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
        strings.add("默认排序");
        strings.add("距离最近");
        strings.add("人气最高");
        strings.add("均价从高到低");
        strings.add("均价从低到高");
        strings.add("开盘时间从近到远");
        strings.add("开盘时间从远到近");
        adapter = new SortAdapter(R.layout.item_area, strings);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.addItemDecoration(new MyVerticalDecoration(context, ContextCompat.getColor(context, R.color.color_f6), 1, 0, 0, true));
        rv.setAdapter(adapter);
    }

}
