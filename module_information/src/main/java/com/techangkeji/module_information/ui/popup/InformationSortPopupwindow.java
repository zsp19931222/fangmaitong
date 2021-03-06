package com.techangkeji.module_information.ui.popup;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goldze.base.eventbus.PopupwindowRxBusBean;
import com.goldze.base.eventbus.SortRxBusBean;
import com.techangkeji.model_information.R;
import com.techangkeji.module_information.ui.adapter.InformationSortAdapter;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.bus.RxBus;
import razerdp.basepopup.BasePopupWindow;

public class InformationSortPopupwindow extends BasePopupWindow {
    private RecyclerView rv;
    private View v_pa;
    private Context context;

    public InformationSortPopupwindow(Context context) {
        super(context);
        this.context = context;
        init(context);
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
        initAdapter();
        v_pa.getBackground().setAlpha(125);
        v_pa.setOnClickListener(view1 -> dismiss());
    }

    private void initAdapter() {
        List<PopupwindowRxBusBean> strings = new ArrayList<>();
        strings.add(new PopupwindowRxBusBean("默认排序", ""));
        strings.add(new PopupwindowRxBusBean("发布时间从近到远", "1"));
        strings.add(new PopupwindowRxBusBean("发布时间从远到近", "2"));
        strings.add(new PopupwindowRxBusBean("查看从多到少", "3"));
        strings.add(new PopupwindowRxBusBean("查看从少到多", "4"));
        strings.add(new PopupwindowRxBusBean("点赞从多到少", "5"));
        strings.add(new PopupwindowRxBusBean("点赞从少到多", "6"));
        strings.add(new PopupwindowRxBusBean("评论从多到少", "7"));
        strings.add(new PopupwindowRxBusBean("评论从少到多", "8"));
        InformationSortAdapter sortAdapter = new InformationSortAdapter(R.layout.item_popup, strings);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(sortAdapter);
        sortAdapter.setSelectListener(position -> {
            String s;
            if (position==0){
                s="";
            }else {
                s=""+position;
            }
            RxBus.getDefault().post(new SortRxBusBean(s));
            dismiss();
        });
    }

    public class InformationSortRxBean {
        private String selectPosition;

        public InformationSortRxBean(String selectPosition) {
            this.selectPosition = selectPosition;
        }

        public String getSelectPosition() {
            return selectPosition;
        }

        public void setSelectPosition(String selectPosition) {
            this.selectPosition = selectPosition;
        }
    }
}

