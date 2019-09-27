package com.techangkeji.module_information.ui.popup;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goldze.base.eventbus.PriceRxBusBean;
import com.goldze.base.listener.PopupSelectListener;
import com.techangkeji.model_information.R;
import com.techangkeji.module_hr.ui.adapter.PriceAdapter;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.view.MyVerticalDecoration;
import me.goldze.mvvmhabit.view.shape.RadiusTextView;
import razerdp.basepopup.BasePopupWindow;

public class CompensationPopupwindow extends BasePopupWindow {
    private PriceAdapter adapter;
    private RecyclerView rv;
    private EditText low, heiget;
    private RadiusTextView confirm;
    private View v_pa;
    private Context context;

    public CompensationPopupwindow(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_compensation);
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
        confirm.setOnClickListener(view ->{
            PriceRxBusBean priceRxBusBean=new PriceRxBusBean(heiget.getText().toString(),low.getText().toString());
            RxBus.getDefault().post(priceRxBusBean);
            dismiss();
        } );
    }

    private void initAdapter() {
        List<String> strings = new ArrayList<>();
        strings.add("不限");
        strings.add("面议");
        strings.add("10k以下");
        strings.add("10k-15k");
        strings.add("15k-20k");
        strings.add("20k-25k");
        strings.add("25k-30k");
        strings.add("30k以上");
        adapter = new PriceAdapter(R.layout.item_area, strings);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.addItemDecoration(new MyVerticalDecoration(context, ContextCompat.getColor(context, R.color.color_f6), 1, 0, 0, true));
        rv.setAdapter(adapter);
        adapter.setPopupSelectListener(position -> {
            PriceRxBusBean priceRxBusBean;
            switch (strings.get(position)) {
                case "不限":
                    priceRxBusBean = new PriceRxBusBean("-1", "-1");
                    break;
                case "面议":
                    priceRxBusBean = new PriceRxBusBean("0", "0");
                    break;
                case "10k以下":
                    priceRxBusBean = new PriceRxBusBean("0", "10000");
                    break;
                case "10k-15k":
                    priceRxBusBean = new PriceRxBusBean("10000", "15000");
                    break;
                case "15k-20k":
                    priceRxBusBean = new PriceRxBusBean("15000", "20000");
                    break;
                case "20k-25k":
                    priceRxBusBean = new PriceRxBusBean("20000", "25000");
                    break;
                case "25k-30k":
                    priceRxBusBean = new PriceRxBusBean("25000", "30000");
                    break;
                case "30k以上":
                    priceRxBusBean = new PriceRxBusBean("30000", "0");
                    break;
                default:
                    priceRxBusBean = new PriceRxBusBean("500", "");
                    break;
            }
            RxBus.getDefault().post(priceRxBusBean);
            dismiss();
        });
    }

}
