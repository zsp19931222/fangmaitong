package com.baidu.mapapi.overlayutil;

import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.search.poi.PoiResult;
import com.techangkeji.model_message.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于显示poi的overly
 */
public class PoiOverlay extends OverlayManager {

    private static final int MAX_POI_SIZE = 10;

    private PoiResult mPoiResult = null;
    private int numType;

    public int getNumType() {
        return numType;
    }

    public void setNumType(int numType) {
        this.numType = numType;
    }

    /**
     * 构造函数
     *
     * @param baiduMap   该 PoiOverlay 引用的 BaiduMap 对象
     */
    public PoiOverlay(BaiduMap baiduMap) {
        super(baiduMap);
    }

    /**
     * 设置POI数据
     *
     * @param poiResult    设置POI数据
     * @param numType
     */
    public void setData(PoiResult poiResult, int numType) {
        this.mPoiResult = poiResult;
        this.numType=numType;
    }

    @Override
    public final List<OverlayOptions> getOverlayOptions() {
        if (mPoiResult == null || mPoiResult.getAllPoi() == null) {
            return null;
        }

        List<OverlayOptions> markerList = new ArrayList<>();
        int markerSize = 0;

        for (int i = 0; i < mPoiResult.getAllPoi().size() && markerSize < MAX_POI_SIZE; i++) {
            if (mPoiResult.getAllPoi().get(i).location == null) {
                continue;
            }
            markerSize++;
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            //自己修改，判断最后一个，最后一个为自己添加的坐标地点
            if (i==mPoiResult.getAllPoi().size()-1){
                markerList.add(new MarkerOptions()
                        //动态切换不同的图标信息
//                .icon(BitmapDescriptorFactory.fromAssetWithDpi("Icon_mark" + markerSize + ".png"))
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.dinwei))
                        .extraInfo(bundle)
                        .position(mPoiResult.getAllPoi().get(i).location));
            }else{
                //判断要显示那种图标
                if (numType==1){//A也就是地铁
                    markerList.add(new MarkerOptions()
                            //动态切换不同的图标信息
//                .icon(BitmapDescriptorFactory.fromAssetWithDpi("Icon_mark" + markerSize + ".png"))
                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.dinwei))
                            .extraInfo(bundle)
                            .position(mPoiResult.getAllPoi().get(i).location));
                }else if (numType==2){//B也就是餐饮
                    markerList.add(new MarkerOptions()
                            //动态切换不同的图标信息
//                .icon(BitmapDescriptorFactory.fromAssetWithDpi("Icon_mark" + markerSize + ".png"))
                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.dinwei))
                            .extraInfo(bundle)
                            .position(mPoiResult.getAllPoi().get(i).location));
                }else if (numType==3){//C 也就是购物
                    markerList.add(new MarkerOptions()
                            //动态切换不同的图标信息
//                .icon(BitmapDescriptorFactory.fromAssetWithDpi("Icon_mark" + markerSize + ".png"))
                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.dinwei))
                            .extraInfo(bundle)
                            .position(mPoiResult.getAllPoi().get(i).location));
                }

            }
        }

        return markerList;
    }

    /**
     * 获取该PoiOverlay的poi数据
     *
     * @return     POI数据
     */
    public PoiResult getPoiResult() {
        return mPoiResult;
    }

    /**
     * 覆写此方法以改变默认点击行为
     *
     * @param i    被点击的poi在
     *             {@link PoiResult#getAllPoi()} 中的索引
     * @return     true--事件已经处理，false--事件未处理
     */
    public boolean onPoiClick(int i) {
//        if (mPoiResult.getAllPoi() != null
//                && mPoiResult.getAllPoi().get(i) != null) {
//            Toast.makeText(BMapManager.getInstance().getContext(),
//                    mPoiResult.getAllPoi().get(i).name, Toast.LENGTH_LONG)
//                    .show();
//        }
        return false;
    }

    @Override
    public final boolean onMarkerClick(Marker marker) {
        if (!mOverlayList.contains(marker)) {
            return false;
        }

        if (marker.getExtraInfo() != null) {
            return onPoiClick(marker.getExtraInfo().getInt("index"));
        }

        return false;
    }

    @Override
    public boolean onPolylineClick(Polyline polyline) {
        return false;
    }
}