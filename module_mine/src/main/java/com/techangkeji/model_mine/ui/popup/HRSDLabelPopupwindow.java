package com.techangkeji.model_mine.ui.popup;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.goldze.base.bean.FeaturedLabelBean;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.ui.adapter.HRSDLabelAdapter;
import com.techangkeji.model_mine.ui.viewModel.HouseResourceReleaseViewModel;

import me.goldze.mvvmhabit.view.shape.RadiusTextView;
import razerdp.basepopup.BasePopupWindow;

/**
 * description:
 * author:created by Andy on 2019/9/12 0012 11:28
 * email:zsp872126510@gmail.com
 */
public class HRSDLabelPopupwindow extends BasePopupWindow {

    public HRSDLabelPopupwindow(Context context, HouseResourceReleaseViewModel viewModel) {
        super(context);
        RecyclerView recyclerView = findViewById(R.id.rv);
        RadiusTextView tv_phl_delete = findViewById(R.id.tv_phl_delete);
        RadiusTextView tv_phl_select = findViewById(R.id.tv_phl_select);
        HRSDLabelAdapter hrsdLabelAdapter = new HRSDLabelAdapter(R.layout.item_label_feature, viewModel.featuredLabelList);
        //设置布局管理器
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context);
        //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical 和 horizontal。
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);//主轴为水平方向，起点在左端。
        //flexWrap 默认情况下 Flex 跟 LinearLayout 一样，都是不带换行排列的，但是flexWrap属性可以支持换行排列。
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);//按正常方向换行
        //justifyContent 属性定义了项目在主轴上的对齐方式。
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);//交叉轴的起点对齐。
        recyclerView.setLayoutManager(flexboxLayoutManager);
        recyclerView.setAdapter(hrsdLabelAdapter);
        tv_phl_delete.setOnClickListener(view -> dismiss());
        tv_phl_select.setOnClickListener(v -> {
            viewModel.featuredLabelListConfirm.clear();
            viewModel.labelIdsStringBuilder.get().setLength(0);
            viewModel.featuredLabelListConfirm.addAll(viewModel.featuredLabelList);
            viewModel.labelList.clear();
            for (FeaturedLabelBean featuredLabelBean : viewModel.featuredLabelListConfirm) {
                if (featuredLabelBean.isSelect()) {
                    viewModel.labelIdsStringBuilder.get().append(featuredLabelBean.getId()).append(",");
                    viewModel.labelList.add(featuredLabelBean.getLabel_name());
                }
            }
            viewModel.adapterObservableField.get().notifyDataSetChanged();
            dismiss();
        });
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_hsrd_label);
    }
}
