package com.techangkeji.module.ui.popup;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techangkeji.module.R;
import com.techangkeji.module.ui.adapter.RemarkPopupAdapter;
import com.techangkeji.module.ui.bean.RemarkPopupBean;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

/**
 * description:
 * author:created by Andy on 2019/9/30 0030 09:54
 * email:zsp872126510@gmail.com
 */
public class RemarkPopupwindow extends BasePopupWindow {
    public RemarkPopupwindow(Context context) {
        super(context);
        RecyclerView recyclerView = findViewById(R.id.rv_pf);
        List<RemarkPopupBean> list = new ArrayList<>();
        list.add(new RemarkPopupBean("价格", 0));
        list.add(new RemarkPopupBean("地段", 0));
        list.add(new RemarkPopupBean("交通", 0));
        list.add(new RemarkPopupBean("配套", 0));
        RemarkPopupAdapter remarkPopupAdapter = new RemarkPopupAdapter(R.layout.item_popup_remark_pf, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(remarkPopupAdapter);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_remark);
    }
}
