package com.techangkeji.module_hr.ui.popup;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goldze.base.listener.PopupSelectListener;
import com.techangkeji.module_hr.R;
import com.techangkeji.module_hr.ui.adapter.AreaAdapter;
import com.techangkeji.module_hr.ui.adapter.TypeAdapter;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.view.MyVerticalDecoration;
import razerdp.basepopup.BasePopupWindow;

public class TypePopupwindow extends BasePopupWindow {
    private TypeAdapter adapter;
    private RecyclerView rv;
    private View v_pa;
    private Context context;

    public TypePopupwindow(Context context,List<String> strings) {
        super(context);
        this.context = context;
        init(context);
        initAdapter(strings);

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
        v_pa.getBackground().setAlpha(125);
        v_pa.setOnClickListener(view1 -> dismiss());
    }

    private void initAdapter(List<String> strings) {
        adapter = new TypeAdapter(R.layout.item_area, strings);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.addItemDecoration(new MyVerticalDecoration(context, ContextCompat.getColor(context, R.color.color_f6), 1, 0, 0, true));
        rv.setAdapter(adapter);
        adapter.setSelectListener(position -> {
            RxBus.getDefault().post(new TypeRxBean(strings.get(position)));
            dismiss();
        });
    }

    public class TypeRxBean{
        private String string;

        public TypeRxBean(String string) {
            this.string = string;
        }

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }
    }
}
