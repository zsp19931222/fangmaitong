package com.techangkeji.module.ui.popup;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.goldze.base.listener.PopupSelectListener;
import com.goldze.base.utils.SoftKeyBoardListener;
import com.goldze.base.utils.glide.GlideLoadUtils;
import com.techangkeji.module.R;
import com.techangkeji.module.ui.adapter.RemarkPopupAdapter;
import com.techangkeji.module.ui.bean.RemarkPopupBean;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.http.net.body.TcReviewBody;
import me.goldze.mvvmhabit.utils.ZLog;
import razerdp.basepopup.BasePopupWindow;

/**
 * description:
 * author:created by Andy on 2019/9/30 0030 09:54
 * email:zsp872126510@gmail.com
 */
public class RemarkPopupwindow extends BasePopupWindow {
    private EditText edit_text;

    public RemarkPopupwindow(Context context) {
        super(context);
        setWidth(ScreenUtils.getScreenWidth());
        RecyclerView recyclerView = findViewById(R.id.rv_pf);
        TextView tv_grade=findViewById(R.id.tv_grade);
        edit_text = findViewById(R.id.edit_text);
        setAdjustInputMethod(true);
        setAutoShowInputMethod(edit_text, true);
        List<RemarkPopupBean> list = new ArrayList<>();
        list.add(new RemarkPopupBean("价格", 0));
        list.add(new RemarkPopupBean("地段", 0));
        list.add(new RemarkPopupBean("交通", 0));
        list.add(new RemarkPopupBean("配套", 0));
        RemarkPopupAdapter remarkPopupAdapter = new RemarkPopupAdapter(R.layout.item_popup_remark_pf, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(remarkPopupAdapter);
        TextView tv_comment = findViewById(R.id.tv_comment);
        tv_comment.setOnClickListener(v -> {
            TcReviewBody tcReviewBody = new TcReviewBody();
            tcReviewBody.setContent(edit_text.getText().toString());
            tcReviewBody.setPriceStar(list.get(0).getScore() + "");
            tcReviewBody.setLotStar(list.get(1).getScore() + "");
            tcReviewBody.setTrafficStar(list.get(2).getScore() + "");
            tcReviewBody.setMatchingStar(list.get(3).getScore() + "");
            RxBus.getDefault().post(tcReviewBody);
            dismiss();
        });

        ImageView start1 = findViewById(R.id.start1);
        ImageView start2 = findViewById(R.id.start2);
        ImageView start3 = findViewById(R.id.start3);
        ImageView start4 = findViewById(R.id.start4);
        ImageView start5 = findViewById(R.id.start5);
        GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start1, 0);
        GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start2, 0);
        GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start3, 0);
        GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start4, 0);
        GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start5, 0);
        tv_grade.setText("（0分）");
        remarkPopupAdapter.setPopupSelectListener(position -> {
            int allPoint = list.get(0).getScore() + list.get(1).getScore() + list.get(2).getScore() + list.get(3).getScore();
            int average = allPoint / 4;
            if (average == 0) {
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start1, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start2, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start3, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start4, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start5, 0);
                tv_grade.setText("（0分）");
            } else if (average > 0 && average <= 1) {
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start_select, start1, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start2, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start3, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start4, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start5, 0);
                tv_grade.setText("（1分）");
            } else if (average > 1 && average <= 2) {
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start_select, start1, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start_select, start2, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start3, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start4, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start5, 0);
                tv_grade.setText("（2分）");

            } else if (average > 2 && average <= 3) {
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start_select, start1, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start_select, start2, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start_select, start3, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start4, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start5, 0);
                tv_grade.setText("（3分）");

            } else if (average > 3 && average <= 4) {
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start_select, start1, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start_select, start2, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start_select, start3, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start_select, start4, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start, start5, 0);
                tv_grade.setText("（4分）");

            } else {
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start_select, start1, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start_select, start2, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start_select, start3, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start_select, start4, 0);
                GlideLoadUtils.getInstance().glideLoad(context, R.mipmap.start_select, start5, 0);
                tv_grade.setText("（5分）");

            }
        });

    }

    @Override
    public void onDismiss() {
        super.onDismiss();
        KeyboardUtils.hideSoftInput(edit_text);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_remark);
    }
}
