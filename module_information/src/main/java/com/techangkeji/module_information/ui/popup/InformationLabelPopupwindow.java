package com.techangkeji.module_information.ui.popup;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goldze.base.eventbus.PopupwindowRxBusBean;
import com.goldze.base.eventbus.SortRxBusBean;
import com.techangkeji.model_information.R;
import com.techangkeji.module_information.ui.adapter.InformationLabelAdapter;
import com.techangkeji.module_information.ui.adapter.InformationSortAdapter;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.http.net.entity.FeaturedLabelEntity;
import razerdp.basepopup.BasePopupWindow;

public class InformationLabelPopupwindow extends BasePopupWindow {
    private RecyclerView rv;
    private View v_pa;
    private Context context;

    public InformationLabelPopupwindow(Context context, List<FeaturedLabelEntity.DataBean> list) {
        super(context);
        this.context = context;
        init(context);
        initAdapter(list);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_information_sort);
    }

    private void init(Context context) {
        setPopupWindowFullScreen(false);
        setBackgroundColor(Color.parseColor("#00000000"));
        rv = findViewById(R.id.rv);
        v_pa = findViewById(R.id.v_fill);
        v_pa.getBackground().setAlpha(125);
        v_pa.setOnClickListener(view1 -> dismiss());
    }

    private void initAdapter(List<FeaturedLabelEntity.DataBean> list) {
        InformationLabelAdapter sortAdapter = new InformationLabelAdapter(R.layout.item_popup, list);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(sortAdapter);
        sortAdapter.setSelectListener(position -> {
            RxBus.getDefault().post(list.get(position));
            dismiss();
        });
    }
}

