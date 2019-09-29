package com.techangkeji.module_hr.ui.popup;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goldze.base.eventbus.PriceRxBusBean;
import com.goldze.base.listener.PopupSelectListener;
import com.techangkeji.module_hr.R;
import com.techangkeji.module_hr.ui.adapter.PriceAdapter;
import com.techangkeji.module_hr.ui.adapter.TypeAdapter;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.bus.RxBus;
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
        setPopupWindowFullScreen(false);
        setBackgroundColor(Color.parseColor("#00000000"));
        rv = findViewById(R.id.rv_pp);
        v_pa = findViewById(R.id.v_fill);
        low = findViewById(R.id.et_pp_low);
        heiget = findViewById(R.id.et_pp_height);
        confirm = findViewById(R.id.tv_pp_confirm);
        initAdapter();
        v_pa.getBackground().setAlpha(125);
        v_pa.setOnClickListener(view1 -> dismiss());
        confirm.setOnClickListener(view -> {
            PriceRxBusBean priceRxBusBean = new PriceRxBusBean(heiget.getText().toString(), low.getText().toString());
            RxBus.getDefault().post(priceRxBusBean);
            dismiss();
        });
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
        adapter.setPopupSelectListener(position -> {
            PriceRxBusBean priceRxBusBean;
            switch (strings.get(position)) {
                case "不限":
                    priceRxBusBean = new PriceRxBusBean("", "");
                    break;
                case "100万以下":
                    priceRxBusBean = new PriceRxBusBean("0", "100");
                    break;
                case "100-200万":
                    priceRxBusBean = new PriceRxBusBean("100", "200");
                    break;
                case "200-300万":
                    priceRxBusBean = new PriceRxBusBean("200", "300");
                    break;
                case "300-400万":
                    priceRxBusBean = new PriceRxBusBean("300", "400");
                    break;
                case "400-500万":
                    priceRxBusBean = new PriceRxBusBean("400", "500");
                    break;
                default:
                    priceRxBusBean = new PriceRxBusBean("", "");
                    break;
            }
            RxBus.getDefault().post(priceRxBusBean);
            dismiss();
        });
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.addItemDecoration(new MyVerticalDecoration(context, ContextCompat.getColor(context, R.color.color_f6), 1, 0, 0, true));
        rv.setAdapter(adapter);
    }

}
