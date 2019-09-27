package com.techangkeji.module_hr.ui.popup;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techangkeji.module_hr.R;
import com.techangkeji.module_hr.ui.adapter.AreaAdapter;
import com.techangkeji.module_hr.ui.bean.AreaPopupBean;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.http.net.entity.AreaListEntity;
import me.goldze.mvvmhabit.litepal.CityLitePal;
import me.goldze.mvvmhabit.litepal.DistrictLitePal;
import me.goldze.mvvmhabit.litepal.ProvinceLitePal;
import me.goldze.mvvmhabit.view.MyVerticalDecoration;
import razerdp.basepopup.BasePopupWindow;

public class AreaPopupwindow extends BasePopupWindow {
    private AreaAdapter adapter1, adapter2, adapter3;
    private RecyclerView rv_pa_1, rv_pa_2, rv_pa_3;
    private View v_pa;
    private Context context;
    private List<AreaPopupBean> areaLevel1List = new ArrayList<>();
    private List<AreaPopupBean> areaLevel2List = new ArrayList<>();
    private List<AreaPopupBean> areaLevel3List = new ArrayList<>();

    public AreaPopupwindow(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_area);
    }

    private void init(Context context) {
        setPopupWindowFullScreen(false);
        setBackgroundColor(Color.parseColor("#00000000"));
        rv_pa_1 = findViewById(R.id.rv_pa_1);
        rv_pa_2 = findViewById(R.id.rv_pa_2);
        rv_pa_3 = findViewById(R.id.rv_pa_3);
        v_pa = findViewById(R.id.v_fill);
        initAdapter1();
        initAdapter2();
        initAdapter3();
        v_pa.getBackground().setAlpha(125);
        v_pa.setOnClickListener(view1 -> dismiss());
    }

    private void initAdapter1() {
        List<ProvinceLitePal> provinceLitePals = LitePal.findAll(ProvinceLitePal.class);
        for (ProvinceLitePal provinceLitePal : provinceLitePals) {
            areaLevel1List.add(new AreaPopupBean(1, provinceLitePal.getAreaName(), provinceLitePal.getProvinceid() + "", false));
        }
        adapter1 = new AreaAdapter(R.layout.item_area, areaLevel1List);
        adapter1.setOnSelectListener((position, level) -> {
            for (AreaPopupBean areaPopupBean : areaLevel1List) {
                areaPopupBean.setSelect(false);
            }
            areaLevel1List.get(position).setSelect(true);
            adapter1.notifyDataSetChanged();

            areaLevel2List.clear();
            List<CityLitePal> cityLitePals = LitePal.where("provinceid = ?", areaLevel1List.get(position).getId() + "").find(CityLitePal.class);
            for (CityLitePal cityLitePal : cityLitePals) {
                areaLevel2List.add(new AreaPopupBean(2, cityLitePal.getCityName(), cityLitePal.getCityId() + "", false));
            }
            adapter2.notifyDataSetChanged();
            areaLevel3List.clear();
            adapter3.notifyDataSetChanged();
        });
        rv_pa_1.setLayoutManager(new LinearLayoutManager(context));
        rv_pa_1.addItemDecoration(new MyVerticalDecoration(context, ContextCompat.getColor(context, R.color.color_f6), 1, 0, 0, true));
        rv_pa_1.setAdapter(adapter1);
    }

    private void initAdapter2() {
        adapter2 = new AreaAdapter(R.layout.item_area, areaLevel2List);
        adapter2.setOnSelectListener((position, level) -> {
            for (AreaPopupBean areaPopupBean : areaLevel2List) {
                areaPopupBean.setSelect(false);
            }
            areaLevel2List.get(position).setSelect(true);
            adapter2.notifyDataSetChanged();

            areaLevel3List.clear();
            List<DistrictLitePal> districtLitePals = LitePal.where("cityId = ?", areaLevel2List.get(position).getId() + "").find(DistrictLitePal.class);
            for (DistrictLitePal districtLitePal : districtLitePals) {
                areaLevel3List.add(new AreaPopupBean(3, districtLitePal.getAreaName(), districtLitePal.getDistrictid() + "", false));
            }
            adapter3.notifyDataSetChanged();
        });
        rv_pa_2.setLayoutManager(new LinearLayoutManager(context));
        rv_pa_2.addItemDecoration(new MyVerticalDecoration(context, ContextCompat.getColor(context, R.color.color_f6), 1, 0, 0, true));
        rv_pa_2.setAdapter(adapter2);
    }

    private void initAdapter3() {
        adapter3 = new AreaAdapter(R.layout.item_area, areaLevel3List);
        adapter3.setOnSelectListener((position, level) -> dismiss());
        rv_pa_3.setLayoutManager(new LinearLayoutManager(context));
        rv_pa_3.addItemDecoration(new MyVerticalDecoration(context, ContextCompat.getColor(context, R.color.color_f6), 1, 0, 0, true));
        rv_pa_3.setAdapter(adapter3);
        adapter3.setOnSelectListener((position, level) -> {
            RxBus.getDefault().post(new AreaPopupBean(level,areaLevel3List.get(position).getName(),areaLevel3List.get(position).getId(),false));
            dismiss();
        });
    }
}
