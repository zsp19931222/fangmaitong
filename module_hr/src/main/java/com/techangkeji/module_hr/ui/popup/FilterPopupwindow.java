package com.techangkeji.module_hr.ui.popup;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.techangkeji.module_hr.R;
import com.techangkeji.module_hr.ui.adapter.AreaAdapter;
import com.techangkeji.module_hr.ui.adapter.FilterAdapter;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.view.MyVerticalDecoration;
import razerdp.basepopup.BasePopupWindow;

public class FilterPopupwindow extends BasePopupWindow {
    private FilterAdapter adapter1, adapter2, adapter3;
    private RecyclerView rv_pf1, rv_pf2, rv_pf3;
    private View v_pa;
    private Context context;

    public FilterPopupwindow(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_filter);
    }

    private void init(Context context) {
        setPopupWindowFullScreen(false);
        setBackgroundColor(Color.parseColor("#00000000"));
        rv_pf1 = findViewById(R.id.rv_pf1);
        rv_pf2 = findViewById(R.id.rv_pf2);
        rv_pf3 = findViewById(R.id.rv_pf3);
        v_pa = findViewById(R.id.v_fill);
        initAdapter1();
        initAdapter2();
        initAdapter3();
        v_pa.getBackground().setAlpha(125);
        v_pa.setOnClickListener(view1 -> dismiss());
    }

    private void initAdapter1() {
        List<String> strings=new ArrayList<>();
        strings.add("专车看房");
        strings.add("视频看房");
        strings.add("小户型");
        strings.add("现房");
        strings.add("品牌开发商");
        adapter1=new FilterAdapter(R.layout.item_filter,strings);
        //设置布局管理器
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context);
        //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical 和 horizontal。
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);//主轴为水平方向，起点在左端。
        //flexWrap 默认情况下 Flex 跟 LinearLayout 一样，都是不带换行排列的，但是flexWrap属性可以支持换行排列。
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);//按正常方向换行
        //justifyContent 属性定义了项目在主轴上的对齐方式。
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);//交叉轴的起点对齐。
        rv_pf1.setLayoutManager(flexboxLayoutManager);
        rv_pf1.setAdapter(adapter1);
    }

    private void initAdapter2() {
        List<String> strings=new ArrayList<>();
        strings.add("精装");
        strings.add("简装");
        strings.add("毛坯");
        adapter2=new FilterAdapter(R.layout.item_filter,strings);
        //设置布局管理器
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context);
        //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical 和 horizontal。
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);//主轴为水平方向，起点在左端。
        //flexWrap 默认情况下 Flex 跟 LinearLayout 一样，都是不带换行排列的，但是flexWrap属性可以支持换行排列。
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);//按正常方向换行
        //justifyContent 属性定义了项目在主轴上的对齐方式。
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);//交叉轴的起点对齐。
        rv_pf2.setLayoutManager(flexboxLayoutManager);
        rv_pf2.setAdapter(adapter2);
    }

    private void initAdapter3() {
        List<String> strings=new ArrayList<>();
        strings.add("本月");
        strings.add("下月");
        strings.add("半年内");
        strings.add("已开盘");
        adapter3=new FilterAdapter(R.layout.item_filter,strings);
        //设置布局管理器
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context);
        //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical 和 horizontal。
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);//主轴为水平方向，起点在左端。
        //flexWrap 默认情况下 Flex 跟 LinearLayout 一样，都是不带换行排列的，但是flexWrap属性可以支持换行排列。
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);//按正常方向换行
        //justifyContent 属性定义了项目在主轴上的对齐方式。
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);//交叉轴的起点对齐。
        rv_pf3.setLayoutManager(flexboxLayoutManager);
        rv_pf3.setAdapter(adapter3);
    }
}
