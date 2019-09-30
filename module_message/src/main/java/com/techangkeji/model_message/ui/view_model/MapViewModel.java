package com.techangkeji.model_message.ui.view_model;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.clusterutil.clustering.ClusterManager;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.model.LatLng;
import com.goldze.base.bean.FeaturedLabelBean;
import com.goldze.base.utils.ParameterLogUtil;
import com.techangkeji.model_message.ui.activity.MapActivity;
import com.techangkeji.module_hr.ui.popup.AreaPopupwindow;
import com.techangkeji.module_hr.ui.popup.FilterPopupwindow;
import com.techangkeji.module_hr.ui.popup.HouseTypePopupwindow;
import com.techangkeji.module_hr.ui.popup.PricePopupwindow;
import com.techangkeji.module_hr.ui.popup.SortPopupwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.http.net.entity.FeaturedLabelEntity;
import me.goldze.mvvmhabit.http.net.entity.MapBuildingEntity;
import me.goldze.mvvmhabit.utils.RxUtils;
import razerdp.basepopup.BasePopupWindow;

/**
 * description:
 * author:created by Andy on 2019/9/11 0011 10:59
 * email:zsp872126510@gmail.com
 */
public class MapViewModel extends BaseViewModel {
    public ClusterManager<MapActivity.MyItem> mClusterManager;
    public BaiduMap mBaiduMap;
    public Context context;

    public ObservableField<Integer> areaShow = new ObservableField<>(View.VISIBLE);
    public ObservableField<Integer> typeShow = new ObservableField<>(View.GONE);
    public ObservableField<Integer> priceShow = new ObservableField<>(View.GONE);
    public ObservableField<Integer> screenShow = new ObservableField<>(View.GONE);
    public ObservableField<Integer> sortShow = new ObservableField<>(View.GONE);

    public ObservableField<String> houseType = new ObservableField<>("");//户型
    public ObservableField<String> priceMax = new ObservableField<>("");//价格最大值
    public ObservableField<String> priceMin = new ObservableField<>("");//价格最小值
    public ObservableField<String> lat = new ObservableField<>("");//纬度(距离最近使用,默认null)
    public ObservableField<String> lon = new ObservableField<>("");//经度(距离最近使用,默认null)
    public ObservableField<String> hotSort = new ObservableField<>("");//人气排序(1:从高到低)
    public ObservableField<String> priceSort = new ObservableField<>("");//均价排序(0:从低到高，1:从高到低)
    public ObservableField<String> openingTimeSort = new ObservableField<>("");//开盘时间排序(0:从远到近，1:从近到远)
    public ObservableField<String> decoration = new ObservableField<>("");//装修(1-精装，2-简装，3-毛坯)
    public ObservableField<String> specialLabel = new ObservableField<>("");//特色标签
    public ObservableField<String> propertyType = new ObservableField<>("");//物业类型
    public ObservableField<String> areaMin = new ObservableField<>("");//最小面积
    public ObservableField<String> areaMax = new ObservableField<>("");//最大面积
    public ObservableField<String> openType = new ObservableField<>("");//开盘时间（1-本月，2-下月，3-半年内，4-已开盘）

    public MapViewModel(@NonNull Application application) {
        super(application);
    }

    public void getMapBuilding() {
        Map<String,Object> map=new HashMap<>();
        map.put("houseType",houseType.get());
        map.put("priceMax",priceMax.get());
        map.put("priceMin",priceMin.get());
        map.put("lat",lat.get());
        map.put("lon",lon.get());
        map.put("hotSort",hotSort.get());
        map.put("priceSort",priceSort.get());
        map.put("openingTimeSort",openingTimeSort.get());
        map.put("decoration",decoration.get());
        map.put("specialLabel",specialLabel.get());
        map.put("propertyType",propertyType.get());
        map.put("areaMin",areaMin.get());
        map.put("areaMax",areaMax.get());
        map.put("openType",openType.get());
        ParameterLogUtil.getInstance().parameterLog(map);
        IdeaApi.getApiService().getMapBuilding(map)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<MapBuildingEntity>() {
                    @Override
                    public void onSuccess(MapBuildingEntity response) {
                        ClusterManager(response);
                    }

                });
    }


    /**
     * description:
     * author: Andy
     * date: 2019/9/11 0011 11:37
     */
    private void ClusterManager(MapBuildingEntity response) {
        // 定义点聚合管理类ClusterManager
        mClusterManager = new ClusterManager<>(context, mBaiduMap);
        // 添加Marker点
        addMarkers(response);
        // 设置地图监听，当地图状态发生改变时，进行点聚合运算
        mBaiduMap.setOnMapStatusChangeListener(mClusterManager);
        // 设置maker点击时的响应
        mBaiduMap.setOnMarkerClickListener(mClusterManager);

//        mClusterManager.setOnClusterClickListener(cluster -> {
//            Toast.makeText(MapActivity.this,
//                    "有" + cluster.getSize() + "个点", Toast.LENGTH_SHORT).show();
//            return false;
//        });
//        mClusterManager.setOnClusterItemClickListener(item -> {
//            Toast.makeText(MapActivity.this,
//                    "点击单个Item", Toast.LENGTH_SHORT).show();
//
//            return false;
//        });
    }

    public void addMarkers(MapBuildingEntity response) {
        List<MapActivity.MyItem> items = new ArrayList<>();
        for (MapBuildingEntity.DataBean datum : response.getData()) {
            LatLng latLng = new LatLng(Double.valueOf(datum.getLat()), Double.valueOf(datum.getLon()));

            items.add(new MapActivity.MyItem(latLng, datum.getAreaName() + "\n" + datum.getCount()+"套"));
        }
        mClusterManager.addItems(items);
    }

    public ObservableField<View> choiceView = new ObservableField<>();
    public ObservableList<FeaturedLabelBean> featuredLabelList = new ObservableArrayList();//特色标签
    public ObservableList<FeaturedLabelBean> buildLabeList = new ObservableArrayList();//物业类型

    /**
     * description: 特色标签
     * author: Andy
     * date: 2019/9/21  23:56
     */
    public void getFeaturedLabel() {
        IdeaApi.getApiService()
                .getFeaturedLabel()
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<FeaturedLabelEntity>() {
                    @Override
                    public void onSuccess(FeaturedLabelEntity response) {
                        for (FeaturedLabelEntity.DataBean datum : response.getData()) {
                            featuredLabelList.add(new FeaturedLabelBean(datum.getId(), datum.getLabel_name(), false));
                        }
                    }

                });
    }

    /**
     * description:
     * 建筑类型标签
     * author: Andy
     * date: 2019/9/21  23:56
     */
    public void getBuildingTypeLabel() {
        IdeaApi.getApiService()
                .getBuildLabel()
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<FeaturedLabelEntity>() {
                    @Override
                    public void onSuccess(FeaturedLabelEntity response) {
                        for (FeaturedLabelEntity.DataBean datum : response.getData()) {
                            buildLabeList.add(new FeaturedLabelBean(datum.getId(), datum.getLabel_name(), false));
                        }
                    }

                });
    }

    public BindingCommand areaCommand = new BindingCommand(() -> {
        initShow();
        areaShow.set(View.VISIBLE);
        new AreaPopupwindow(context).showPopupWindow(choiceView.get());
    });
    public BindingCommand typeCommand = new BindingCommand(() -> {
        initShow();
        typeShow.set(View.VISIBLE);
        new HouseTypePopupwindow(context).showPopupWindow(choiceView.get());
    });
    public BindingCommand priceCommand = new BindingCommand(() -> {
        initShow();
        priceShow.set(View.VISIBLE);
        PricePopupwindow pricePopupwindow = new PricePopupwindow(context);
        pricePopupwindow.setAdjustInputMethod(false);
        pricePopupwindow.showPopupWindow(choiceView.get());
    });
    public BindingCommand screenCommand = new BindingCommand(() -> {
        initShow();
        screenShow.set(View.VISIBLE);

        FilterPopupwindow filterPopupwindow = new FilterPopupwindow(context, featuredLabelList, buildLabeList,true);
        filterPopupwindow.showPopupWindow(choiceView.get());
        filterPopupwindow.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                for (FeaturedLabelBean featuredLabelBean : featuredLabelList) {
                    featuredLabelBean.setSelect(false);
                }
                for (FeaturedLabelBean featuredLabelBean : buildLabeList) {
                    featuredLabelBean.setSelect(false);
                }
            }
        });
    });
    public BindingCommand sortCommand = new BindingCommand(() -> {
        initShow();
        sortShow.set(View.VISIBLE);
        new SortPopupwindow(context).showPopupWindow(choiceView.get());

    });

    private void initShow() {
        areaShow.set(View.INVISIBLE);
        typeShow.set(View.INVISIBLE);
        priceShow.set(View.INVISIBLE);
        screenShow.set(View.INVISIBLE);
        sortShow.set(View.INVISIBLE);
    }
}
