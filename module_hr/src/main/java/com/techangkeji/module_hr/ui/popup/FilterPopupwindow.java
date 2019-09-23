package com.techangkeji.module_hr.ui.popup;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.goldze.base.bean.FeaturedLabelBean;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.techangkeji.module_hr.R;
import com.techangkeji.module_hr.ui.adapter.FilterAdapter;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

public class FilterPopupwindow extends BasePopupWindow {
    private FilterAdapter adapter1,adapter4, adapter2, adapter3,adapter5;
    private RecyclerView rv_pf1, rv_pf2, rv_pf3,rv_pf4,rv_pf5;
    private View v_pa;
    private Context context;
    private List<FeaturedLabelBean> featuredLabelBeans = new ArrayList<>();
    private List<FeaturedLabelBean> buildLabelBeans = new ArrayList<>();
    private List<FeaturedLabelBean> sizeLabelBeans=new ArrayList<>();
    private List<FeaturedLabelBean> decorationLabelBeans=new ArrayList<>();
    private List<FeaturedLabelBean> openTimeLabelBeans=new ArrayList<>();
    private TextView reset,confirm;

    public FilterPopupwindow(Context context, List<FeaturedLabelBean> featuredLabelBeans,List<FeaturedLabelBean> buildLabelBeans) {
        super(context);
        this.context = context;
        init(context);
        this.featuredLabelBeans.addAll(featuredLabelBeans);
        this.buildLabelBeans.addAll(buildLabelBeans);
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
        rv_pf4 = findViewById(R.id.rv_pf4);
        rv_pf5 = findViewById(R.id.rv_pf5);
        v_pa = findViewById(R.id.v_fill);
        initAdapter1();
        initAdapter2();
        initAdapter3();
        initAdapter4();
        initAdapter5();
        v_pa.getBackground().setAlpha(125);
        v_pa.setOnClickListener(view1 -> dismiss());
        reset=findViewById(R.id.tv_reset);
        confirm=findViewById(R.id.tv_confirm);

    }

    private void initAdapter1() {
        adapter1 = new FilterAdapter(R.layout.item_filter, featuredLabelBeans);
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
        decorationLabelBeans.add(new FeaturedLabelBean("精装",false));
        decorationLabelBeans.add(new FeaturedLabelBean("简装",false));
        decorationLabelBeans.add(new FeaturedLabelBean("毛坯",false));
        adapter2 = new FilterAdapter(R.layout.item_filter, decorationLabelBeans);
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
        List<String> strings = new ArrayList<>();
        strings.add("本月");
        strings.add("下月");
        strings.add("半年内");
        strings.add("已开盘");
        openTimeLabelBeans.add(new FeaturedLabelBean("本月",false));
        openTimeLabelBeans.add(new FeaturedLabelBean("下月",false));
        openTimeLabelBeans.add(new FeaturedLabelBean("半年内",false));
        openTimeLabelBeans.add(new FeaturedLabelBean("已开盘",false));
        adapter3 = new FilterAdapter(R.layout.item_filter, openTimeLabelBeans);
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
    private void initAdapter4() {
        adapter4 = new FilterAdapter(R.layout.item_filter, buildLabelBeans);
        //设置布局管理器
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context);
        //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical 和 horizontal。
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);//主轴为水平方向，起点在左端。
        //flexWrap 默认情况下 Flex 跟 LinearLayout 一样，都是不带换行排列的，但是flexWrap属性可以支持换行排列。
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);//按正常方向换行
        //justifyContent 属性定义了项目在主轴上的对齐方式。
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);//交叉轴的起点对齐。
        rv_pf4.setLayoutManager(flexboxLayoutManager);
        rv_pf4.setAdapter(adapter4);
    }
    private void initAdapter5() {
        sizeLabelBeans.add(new FeaturedLabelBean("50以下",false));
        sizeLabelBeans.add(new FeaturedLabelBean("50-70",false));
        sizeLabelBeans.add(new FeaturedLabelBean("70-90",false));
        sizeLabelBeans.add(new FeaturedLabelBean("90-120",false));
        sizeLabelBeans.add(new FeaturedLabelBean("120-150",false));
        sizeLabelBeans.add(new FeaturedLabelBean("150-200",false));
        sizeLabelBeans.add(new FeaturedLabelBean("200以上",false));
        adapter5 = new FilterAdapter(R.layout.item_filter, sizeLabelBeans);
        //设置布局管理器
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context);
        //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical 和 horizontal。
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);//主轴为水平方向，起点在左端。
        //flexWrap 默认情况下 Flex 跟 LinearLayout 一样，都是不带换行排列的，但是flexWrap属性可以支持换行排列。
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);//按正常方向换行
        //justifyContent 属性定义了项目在主轴上的对齐方式。
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);//交叉轴的起点对齐。
        rv_pf5.setLayoutManager(flexboxLayoutManager);
        rv_pf5.setAdapter(adapter5);
    }
}
