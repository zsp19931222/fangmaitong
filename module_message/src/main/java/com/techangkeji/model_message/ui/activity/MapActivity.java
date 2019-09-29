package com.techangkeji.model_message.ui.activity;


import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.clusterutil.clustering.ClusterItem;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.blankj.utilcode.util.SPUtils;
import com.goldze.base.eventbus.FilterRxBusBean;
import com.goldze.base.eventbus.HouseTypeRxBusBean;
import com.goldze.base.eventbus.PriceRxBusBean;
import com.goldze.base.eventbus.SortRxBusBean;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_message.BR;
import com.techangkeji.model_message.R;
import com.techangkeji.model_message.databinding.ActivityMapBinding;
import com.techangkeji.model_message.ui.view_model.MapViewModel;
import com.techangkeji.module_hr.ui.bean.AreaPopupBean;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/11 0011 11:01
 * email:zsp872126510@gmail.com
 */
@Route(path = ARouterPath.Message.MapActivity)
public class MapActivity extends BaseActivity<ActivityMapBinding, MapViewModel> implements BaiduMap.OnMapLoadedCallback {
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationClient mLocationClient;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_map;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.context = this;
        binding.title.setTitle("地图找房");
        mMapView = binding.mapView;
        mBaiduMap = mMapView.getMap();
        viewModel.mBaiduMap = mBaiduMap;
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setOnMapLoadedCallback(this);
        startLocation();
        viewModel.getMapBuilding();
        viewModel.getFeaturedLabel();
        viewModel.getBuildingTypeLabel();
        viewModel.choiceView.set(binding.llFhChoice);

        RxSubscriptions.add(RxBus.getDefault().toObservable(HouseTypeRxBusBean.class).subscribe(houseTypeRxBusBean -> {
            viewModel.houseType.set(houseTypeRxBusBean.getType());
            viewModel.getMapBuilding();

        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(PriceRxBusBean.class).subscribe(priceRxBusBean -> {
            viewModel.priceMin.set(priceRxBusBean.getMin());
            viewModel.priceMax.set(priceRxBusBean.getMax());
            viewModel.getMapBuilding();

        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(SortRxBusBean.class).subscribe(sortRxBusBean -> {
            viewModel.lat.set("");
            viewModel.lon.set("");
            viewModel.hotSort.set("");
            viewModel.openingTimeSort.set("");
            viewModel.priceSort.set("");
            ZLog.d(sortRxBusBean.getSort());
            switch (sortRxBusBean.getSort()) {
                case "距离最近":
                    viewModel.lat.set(SPUtils.getInstance().getString("latitude"));
                    viewModel.lon.set(SPUtils.getInstance().getString("longitude"));
                    break;
                case "人气最高":
                    viewModel.hotSort.set("1");
                    break;
                case "均价从高到低":
                    viewModel.priceSort.set("1");
                    break;
                case "均价从低到高":
                    viewModel.priceSort.set("0");
                    break;
                case "开盘时间从近到远":
                    viewModel.openingTimeSort.set("1");
                    break;
                case "开盘时间从远到近":
                    viewModel.openingTimeSort.set("0");
                    break;
            }
            viewModel.getMapBuilding();
        }));

        RxSubscriptions.add(RxBus.getDefault().toObservable(FilterRxBusBean.class).subscribe(filterRxBusBean -> {
            viewModel.decoration.set(filterRxBusBean.getDecoration());
            viewModel.specialLabel.set(filterRxBusBean.getSpecialLabel());
            viewModel.propertyType.set(filterRxBusBean.getPropertyType());
            viewModel.areaMin.set(filterRxBusBean.getAreaMin());
            viewModel.areaMax.set(filterRxBusBean.getAreaMax());
            viewModel.openType.set(filterRxBusBean.getOpenType());
            viewModel.getMapBuilding();
        }));
    }

    @Override
    public void onMapLoaded() {
        MapStatus ms = new MapStatus.Builder().zoom(9).build();
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(ms));
    }

    //ClusterItem接口的实现类
    public static class MyItem implements ClusterItem {
        LatLng mPosition;
        private String area;

        public MyItem(LatLng mPosition, String area) {
            this.mPosition = mPosition;
            this.area = area;
        }

        @Override
        public LatLng getPosition() {
            return mPosition;
        }

        @Override
        public String gerArea() {
            return area;
        }

        @Override
        public BitmapDescriptor getBitmapDescriptor() {
            return BitmapDescriptorFactory
                    .fromResource(R.mipmap.dinwei);
        }
    }

    /**
     * description:
     * author: Andy
     * date: 2019/9/11 0011 11:22
     */
    private void startLocation() {
        //定位初始化
        mLocationClient = new LocationClient(this);


        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setTimeOut(10 * 1000);
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        //设置locationClientOption
        mLocationClient.setLocOption(option);


        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);

        //开启地图定位图层
        mLocationClient.start();
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();

            moveMap(location);
            mBaiduMap.setMyLocationData(locData);
            mLocationClient.stop();
        }
    }

    /**
     * description: 移动地图到定位位置
     * author: Andy
     * date: 2019/9/11 0011 11:30
     */
    private void moveMap(BDLocation location) {
        //设定中心点坐标
        LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
        //改变地图状态
        mBaiduMap.animateMapStatus(u);
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

}
