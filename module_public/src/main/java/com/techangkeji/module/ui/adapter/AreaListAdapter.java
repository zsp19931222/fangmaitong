package com.techangkeji.module.ui.adapter;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.techangkeji.module.R;
import com.techangkeji.module.ui.bean.AreaItemBean;
import com.techangkeji.module.ui.bean.AreaLevel0Bean;
import com.techangkeji.module.ui.bean.AreaLevel1Bean;
import com.techangkeji.module.ui.view_model.AreaSelectViewModel;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.litepal.DistrictLitePal;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/20 23:36
 * email:zsp872126510@gmail.com
 */
public class AreaListAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public AreaListAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(0, R.layout.area_level0);
        addItemType(1, R.layout.area_level1);
    }

    private List<AreaItemBean> areaItemBeans = new ArrayList<>();

    @Override

    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case 0:
                AreaLevel0Bean areaLevel0Bean = (AreaLevel0Bean) item;
                helper.setText(R.id.tv_ah, areaLevel0Bean.getHeader());
                break;
            case 1:
                AreaLevel1Bean areaLevel1Bean = (AreaLevel1Bean) item;
                RecyclerView recyclerView1 = helper.getView(R.id.rv_ah1);
                ItemAreaAdapter itemAreaAdapter1 = new ItemAreaAdapter(R.layout.item_area_select, areaLevel1Bean.getCityBeanList());
                recyclerView1.setLayoutManager(new GridLayoutManager(helper.itemView.getContext(), 4));
                recyclerView1.setAdapter(itemAreaAdapter1);

                RecyclerView recyclerView2 = helper.getView(R.id.rv_ah2);
                ItemAreaAdapter1 itemAreaAdapter2 = new ItemAreaAdapter1(R.layout.item_area_select, areaItemBeans);
                recyclerView2.setLayoutManager(new GridLayoutManager(helper.itemView.getContext(), 4));
                recyclerView2.setAdapter(itemAreaAdapter2);

                itemAreaAdapter1.setOnCityIdListener((id, position) -> {
                    ZLog.d(id);
                    for (AreaItemBean areaItemBean : areaLevel1Bean.getCityBeanList()) {
                        areaItemBean.setSelect(false);
                    }
                    areaLevel1Bean.getCityBeanList().get(position).setSelect(true);
                    areaItemBeans.clear();
                    List<DistrictLitePal> newsList = LitePal.where("cityId = ?", id + "").find(DistrictLitePal.class);
                    for (DistrictLitePal districtLitePal : newsList) {
                        areaItemBeans.add(new AreaItemBean(districtLitePal.getDistrictid(), districtLitePal.getCityId(), districtLitePal.getAreaName(), false));
                    }
                    itemAreaAdapter2.notifyDataSetChanged();
                    itemAreaAdapter1.notifyDataSetChanged();
                });
                break;
        }
    }
}
