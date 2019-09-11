package com.techangkeji.module_hr.ui.popup;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techangkeji.module_hr.R;
import com.techangkeji.module_hr.ui.adapter.PriceAdapter;
import com.techangkeji.module_hr.ui.adapter.TypeAdapter;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.view.MyVerticalDecoration;
import me.goldze.mvvmhabit.view.shape.RadiusTextView;
import razerdp.basepopup.BasePopupWindow;

public class PricePopupwindow extends BasePopupWindow {
    private PriceAdapter adapter;
    private RecyclerView rv;
    private EditText low, heiget;
    private RadiusTextView confirm;
    private View v_pa;
    private Context context;

    public PricePopupwindow(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_price);
    }

    @Override
    public BasePopupWindow setAdjustInputMethod(boolean needAdjust) {
        return super.setAdjustInputMethod(needAdjust);
    }

    private void init(Context context) {
        setBackgroundColor(Color.parseColor("#00000000"));
        rv = findViewById(R.id.rv_pp);
        v_pa = findViewById(R.id.v_fill);
        low = findViewById(R.id.et_pp_low);
        heiget = findViewById(R.id.et_pp_height);
        confirm = findViewById(R.id.tv_pp_confirm);
        initAdapter();
        v_pa.getBackground().setAlpha(125);
        v_pa.setOnClickListener(view1 -> dismiss());
    }

    private void initAdapter() {
        List<String> strings = new ArrayList<>();
        strings.add("不限");
        strings.add("100万以下");
        strings.add("100-200万");
        strings.add("200-300万");
        strings.add("300-400万");
        strings.add("400-500万");
        strings.add("500万以上");
        adapter = new PriceAdapter(R.layout.item_area, strings);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.addItemDecoration(new MyVerticalDecoration(context, ContextCompat.getColor(context, R.color.color_f6), 1, 0, 0, true));
        rv.setAdapter(adapter);
    }

}
