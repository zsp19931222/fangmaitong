package com.techangkeji.model_message.ui.view_model;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.clusterutil.clustering.ClusterManager;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.model.LatLng;
import com.techangkeji.model_message.ui.activity.MapActivity;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.http.net.entity.MapBuildingEntity;
import me.goldze.mvvmhabit.utils.RxUtils;

/**
 * description:
 * author:created by Andy on 2019/9/11 0011 10:59
 * email:zsp872126510@gmail.com
 */
public class MapViewModel extends BaseViewModel {
    public ClusterManager<MapActivity.MyItem> mClusterManager;
    public BaiduMap mBaiduMap;
    public Context context;

    public MapViewModel(@NonNull Application application) {
        super(application);
    }

    public void getMapBuilding() {
        IdeaApi.getApiService().getMapBuilding()
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
}
