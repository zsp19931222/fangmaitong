package com.techangkeji.model_message.ui.activity;


import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.clusterutil.clustering.ClusterItem;
import com.baidu.mapapi.clusterutil.clustering.ClusterManager;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_message.BR;
import com.techangkeji.model_message.R;
import com.techangkeji.model_message.databinding.ActivityMapBinding;
import com.techangkeji.model_message.ui.view_model.MapViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;

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
    ClusterManager<MyItem> mClusterManager;

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
        mMapView = binding.mapView;
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setOnMapLoadedCallback(this);
        startLocation();
        ClusterManager();
    }

    /**
     * description:
     * author: Andy
     * date: 2019/9/11 0011 11:37
     */
    private void ClusterManager() {
        // 定义点聚合管理类ClusterManager
        mClusterManager = new ClusterManager<>(this, mBaiduMap);
        // 添加Marker点
        addMarkers();
        // 设置地图监听，当地图状态发生改变时，进行点聚合运算
        mBaiduMap.setOnMapStatusChangeListener(mClusterManager);
        // 设置maker点击时的响应
        mBaiduMap.setOnMarkerClickListener(mClusterManager);

        mClusterManager.setOnClusterClickListener(cluster -> {
            Toast.makeText(MapActivity.this,
                    "有" + cluster.getSize() + "个点", Toast.LENGTH_SHORT).show();
            return false;
        });
        mClusterManager.setOnClusterItemClickListener(item -> {
            Toast.makeText(MapActivity.this,
                    "点击单个Item", Toast.LENGTH_SHORT).show();

            return false;
        });
    }

    public void addMarkers() {
        // 添加Marker点
        LatLng llA = new LatLng(29.517126, 106.46362);
        LatLng llB = new LatLng(29.522233, 106.467519);
        LatLng llC = new LatLng(29.52514, 106.467105);
        LatLng llD = new LatLng(29.517126, 106.401394);
        LatLng llE = new LatLng(29.517126, 106.331394);
        LatLng llF = new LatLng(29.517126, 106.441394);
        LatLng llG = new LatLng(29.517126, 106.411394);

        List<MyItem> items = new ArrayList<>();
        items.add(new MyItem(llA, "九龙坡\n1.1万/m\n2个"));
        items.add(new MyItem(llB, "九龙坡\n1.1万/m\n4个"));
        items.add(new MyItem(llC, "九龙坡\n1.1万/m\n6个"));
//        items.add(new MyItem(llD));
//        items.add(new MyItem(llE));
//        items.add(new MyItem(llF));
//        items.add(new MyItem(llG));
        mClusterManager.addItems(items);
    }

    @Override
    public void onMapLoaded() {
        MapStatus ms = new MapStatus.Builder().zoom(9).build();
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(ms));
    }

    //ClusterItem接口的实现类
    public class MyItem implements ClusterItem {
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
