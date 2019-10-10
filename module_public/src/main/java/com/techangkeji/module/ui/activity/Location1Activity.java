package com.techangkeji.module.ui.activity;

import android.os.Bundle;

import androidx.databinding.ObservableField;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.PoiChildrenInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.module.BR;
import com.techangkeji.module.R;
import com.techangkeji.module.databinding.ActivityLocation1Binding;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/26 22:01
 * email:zsp872126510@gmail.com
 */
@Route(path = ARouterPath.Public.Location1Activity)
public class Location1Activity extends BaseActivity<ActivityLocation1Binding, BaseViewModel> {
    public String longitude = "";
    public String latitude = "";
    private BaiduMap baiduMap;
    private SuggestionSearch suggestionSearch;
    private List<SuggestionResult.SuggestionInfo> suggestionInfoList = new ArrayList<>();
    private LatLng latLng;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_location1;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        try {
            binding.title.setTitle("楼盘位置");
            Bundle bundle = getIntent().getBundleExtra("bundle");
            longitude = bundle.getString("longitude");
            latitude = bundle.getString("latitude");
            baiduMap = binding.map.getMap();
            latLng = new LatLng(Double.valueOf(latitude), Double.valueOf(longitude));
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(latLng).zoom(18.0f);
            baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            MarkerOptions options = new MarkerOptions();

            // 图标
            options.icon(BitmapDescriptorFactory.fromResource(R.mipmap.dinwei));
            options.position(latLng);
            OverlayOptions ooCircle = new CircleOptions().fillColor(0x384d73b3)
                    .center(latLng).stroke(new Stroke(3, 0x784d73b3))
                    .radius(1000);
            // Bundle结束
            baiduMap.addOverlay(options);//显示在地图上
//            baiduMap.addOverlay(ooCircle);//圈范围
            binding.lp.setOnClickListener(v -> initPOI("楼盘"));
            binding.dt.setOnClickListener(v -> initPOI("地铁"));
            binding.cy.setOnClickListener(v -> initPOI("餐饮"));
            binding.gw.setOnClickListener(v -> initPOI("购物"));
        } catch (Exception e) {

        }

    }

    PoiSearch mPoiSearch;

    private void initPOI(String search) {
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(poiListener);
        //设置矩形检索区域
        LatLngBounds searchBounds = new LatLngBounds.Builder()
                .include(new LatLng(Double.valueOf(latitude) - 0.01, Double.valueOf(longitude) - 0.012))
                .include(new LatLng(Double.valueOf(latitude) + 0.01, Double.valueOf(longitude) + 0.012))
                .build();

        //在searchBounds区域内检索餐厅
        mPoiSearch.searchInBound(new PoiBoundSearchOption()
                .bound(searchBounds)
                .keyword(search));

    }


    OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {

        public void onGetPoiResult(PoiResult poiResult) {
            //获取POI检索结果
            ZLog.d(poiResult.getAllPoi());
            //获取POI检索结果
            // 获取POI检索结果
            if (poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
                //只把当前的添加进去
                if (baiduMap != null) {
                    baiduMap.clear();
                    PoiOverlay ov = new MyPoiOverlay(baiduMap);
                    baiduMap.setOnMarkerClickListener(ov);
                    //把当前定位点添加到集合的最后一个
                    PoiInfo poiInfo = new PoiInfo();
                    poiInfo.setLocation(latLng);
                    poiInfo.setName("这是坐标点");
                    PoiResult poiResult1 = new PoiResult();
                    List<PoiInfo> poiInfoList = new ArrayList<>();
                    poiInfoList.add(poiInfo);
                    poiResult1.setPoiInfo(poiInfoList);
                    //在这里处理显示那种图标的问题
                    ov.setData(poiResult1, 1);
                    //添加PoiOverlay到地图中
                    ov.addToMap();
                    ov.zoomToSpan();
                }


                return;
            }
            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                /**
                 * PoiInfo中包含了经纬度、城市、地址信息、poi名称、uid、邮编、电话等等信息；
                 有了这些，你是不是可以可以在这里画一个自定义的图层了，然后添加点击事件，做一些操作了呢
                 */
                List<PoiInfo> poiInfos = poiResult.getAllPoi();//poi列表
                baiduMap.clear();
                //创建PoiOverlay
                PoiOverlay overlay = new MyPoiOverlay(baiduMap);
                //设置overlay可以处理标注点击事件
                baiduMap.setOnMarkerClickListener(overlay);
                //设置PoiOverlay数据

                //把当前定位点添加到集合的最后一个
                PoiInfo poiInfo = new PoiInfo();
                poiInfo.setLocation(latLng);
                poiInfo.setName("这是坐标点");
                PoiResult poiResult1 = new PoiResult();
                poiInfos.add(poiInfo);
                poiResult1.setPoiInfo(poiInfos);

                //在这里处理显示那种图标的问题
                overlay.setData(poiResult1, 1);

                //添加PoiOverlay到地图中

                overlay.addToMap();
                overlay.zoomToSpan();
                return;
            }
        }

        public void onGetPoiDetailResult(PoiDetailResult result) {
            //获取Place详情页检索结果
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
            ZLog.d("onGetPoiDetailResult", poiDetailSearchResult.getPoiDetailInfoList());
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }
    };

    private class MyPoiOverlay extends PoiOverlay {

        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override

        public boolean onPoiClick(int index) {
            super.onPoiClick(index);
            return true;
        }
    }
}
