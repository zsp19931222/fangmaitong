package com.techangkeji.module.ui.activity;

import android.os.Bundle;

import androidx.databinding.ObservableField;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
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
        // Bundle结束
        baiduMap.addOverlay(options);//显示在地图上
        binding.dt.setOnClickListener(v -> {
            initSuggestionSearch("地铁");
        });
        initPOI();
    }

    /**
     * description:
     * author: Andy
     * date: 2019/9/21  20:10
     */
    private void initSuggestionSearch(String search) {
        suggestionSearch = SuggestionSearch.newInstance();
        suggestionSearch.setOnGetSuggestionResultListener(suggestionResult -> {
            if (IsNullUtil.getInstance().isEmpty(suggestionResult.getAllSuggestions())) return;
            ZLog.d(suggestionResult.getAllSuggestions());
        });
        suggestionSearch.requestSuggestion(new SuggestionSearchOption().city("北京").location(latLng).keyword(search));
    }


    PoiSearch mPoiSearch;

    private void initPOI() {
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(poiListener);
        mPoiSearch.searchNearby(new PoiNearbySearchOption()
                .keyword("地铁")
                .location(latLng)
                .radius(5000)
                .pageNum(10));
    }


    OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {

        public void onGetPoiResult(PoiResult result) {
            //获取POI检索结果
            ZLog.d(result.getAllPoi());
        }

        public void onGetPoiDetailResult(PoiDetailResult result) {
            //获取Place详情页检索结果
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
            ZLog.d(poiDetailSearchResult.getPoiDetailInfoList());
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }
    };
}
