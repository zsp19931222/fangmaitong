package com.goldze.base.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.goldze.base.R;
import com.luck.picture.lib.permissions.RxPermissions;

import io.reactivex.observers.DefaultObserver;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.utils.ToastUtil;
import me.goldze.mvvmhabit.utils.ZLog;

import static com.goldze.base.constant.TipsConstants.GET_PERMISSIONS_FAILED;

/**
 * description:定位
 * author:created by Andy on 2019/9/20 21:01
 * email:zsp872126510@gmail.com
 */
public class LocationUtil {
    public static final int OPEN_GPS_REQUEST_CODE = 10;
    private LocationClient mLocationClient = null;
    private BDAbstractLocationListener myListener = new MyLocationListener();

    private static final LocationUtil ourInstance = new LocationUtil();

    public static LocationUtil getInstance() {
        return ourInstance;
    }

    private LocationUtil() {
    }
    /**
     * @description: 开启定位
     * @author: Andy
     * @date: 2019/7/4 0004 10:59
     */
    public void startLocation(FragmentActivity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//android8.0以上需要用户手动去打开位置信息权限
            openGPSSettings(context);
        }
        try {
            new RxPermissions(context)
                    .request(Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(new DefaultObserver<Boolean>() {
                        @Override
                        public void onNext(Boolean aBoolean) {
                            if (aBoolean) {
                                start(context);
                            } else {
                                ToastUtil.errorToast(context, GET_PERMISSIONS_FAILED, false);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            ToastUtil.errorToast(context, GET_PERMISSIONS_FAILED, false);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } catch (Exception e) {//递归防止有时候进来报错从而没有开启定位
            stopLocate();
        }

    }
    /**
     * description: 开启定位（地图用）
     * author: Andy
     * date: 2019/7/4 0004 15:36
     */

    public void startLocation(FragmentActivity context,MapView mapView,BaiduMap mBaiduMap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//android8.0以上需要用户手动去打开位置信息权限
            openGPSSettings(context);
        }
        try {
            new RxPermissions(context)
                    .request(Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(new DefaultObserver<Boolean>() {
                        @Override
                        public void onNext(Boolean aBoolean) {
                            if (aBoolean) {
                                start(context,mapView,mBaiduMap);
                            } else {
                                ToastUtil.errorToast(context, GET_PERMISSIONS_FAILED, false);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            ToastUtil.errorToast(context, GET_PERMISSIONS_FAILED, false);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } catch (Exception e) {//递归防止有时候进来报错从而没有开启定位
            stopLocate();
        }

    }

    private void stopLocate() {
        mLocationClient.unRegisterLocationListener(myListener);
    }

    /**
     * description: 定位回调监听
     * author: Andy
     * date: 2019/7/4 0004 15:28
     */
    private class MyLocationListener extends BDAbstractLocationListener {

        @SuppressLint("SetTextI18n")
        @Override
        public void onReceiveLocation(BDLocation location) {
            try {
                ZLog.d("onReceiveLocation: " + location.getLocType());
                if (location.getLocType() == 161 || location.getLocType() == 61) {//定位成功
                    double latitude = location.getLatitude();    //获取纬度信息
                    double longitude = location.getLongitude();    //获取经度信息
                    String country = location.getCountry();    //获取国家
                    String province = location.getProvince();    //获取省份
                    String city = location.getCity();    //获取城市
                    String district = location.getDistrict();    //获取区县
                    String street = location.getStreet();    //获取街道信息
                    String streetnum = location.getStreetNumber();//获取街道号码
                    RxBus.getDefault().post(new BaiduLocationBean(true, latitude, longitude, country, province, city, district, street, streetnum));
                } else {
                    RxBus.getDefault().post(new BaiduLocationBean(false, 0, 0, "", "", "", "", "", ""));
                }
            } catch (Exception e) {
                RxBus.getDefault().post(new BaiduLocationBean(false, 0, 0, "", "", "", "", "", ""));
            }
            stopLocate();
        }
    }
    /**
     * description:定位回调监听（地图用）
     * author: Andy
     * date: 2019/7/4 0004 15:23
     */
    public class MyMapLocationListener extends BDAbstractLocationListener {
        private MapView mapView;
        private BaiduMap mBaiduMap;

        public MyMapLocationListener(MapView mapView, BaiduMap mBaiduMap) {
            this.mapView = mapView;
            this.mBaiduMap = mBaiduMap;
        }

        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection())
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();
            mBaiduMap.setMyLocationData(locData);
        }
    }

    /**
     * @description: 8.0以上需要用户手动去开启“位置信息”GPS
     * @author: Andy
     * @date: 2019/7/4 0004 11:13
     */
    private void openGPSSettings(FragmentActivity fragmentActivity) {
        if (!checkGPSIsOpen(fragmentActivity)) {
            //没有打开则弹出对话框
            new AlertDialog.Builder(fragmentActivity)
                    .setTitle(R.string.notifyTitle)
                    .setMessage(R.string.gpsNotifyMsg)
                    // 拒绝, 退出应用
                    .setNegativeButton(R.string.cancel,
                            (dialog, which) -> fragmentActivity.finish())

                    .setPositiveButton(R.string.setting,
                            (dialog, which) -> {
                                //跳转GPS设置界面
                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                fragmentActivity.startActivityForResult(intent, OPEN_GPS_REQUEST_CODE);
                            })

                    .setCancelable(false)
                    .show();

        }
    }

    /**
     * @description: 检查是否打开GPS
     * @author: Andy
     * @date: 2019/7/4 0004 11:13
     */
    private boolean checkGPSIsOpen(Context context) {
        boolean isOpen;
        LocationManager locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            isOpen = locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
            return isOpen;
        }
        return false;
    }

    /**
     * @description: 开启定位
     * @author: Andy
     * @date: 2019/7/4 0004 11:23
     */
    private void start(Context context) {
        mLocationClient = new LocationClient(context);     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
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
        mLocationClient.setLocOption(option);
        //开启定位
        mLocationClient.start();
    }
    /**
     * description: 开启定位（地图用）
     * author: Andy
     * date: 2019/7/4 0004 15:29
     */
    private void start(Context context, MapView mapView, BaiduMap mBaiduMap) {
        //定位初始化
        mLocationClient = new LocationClient(context);


        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);


        //设置locationClientOption
        mLocationClient.setLocOption(option);


        //注册LocationListener监听器
        MyMapLocationListener myLocationListener = new MyMapLocationListener(mapView,mBaiduMap);
        mLocationClient.registerLocationListener(myLocationListener);

        //开启地图定位图层
        mLocationClient.start();
    }
}
