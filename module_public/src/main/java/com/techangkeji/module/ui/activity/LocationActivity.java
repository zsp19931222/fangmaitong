package com.techangkeji.module.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.module.BR;
import com.techangkeji.module.R;
import com.techangkeji.module.databinding.ActivityLocationBinding;
import com.techangkeji.module.ui.adapter.LocationAdapter;
import com.techangkeji.module.ui.view_model.LocationViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/21 18:43
 * email:zsp872126510@gmail.com
 */
@Route(path = ARouterPath.Public.MoreAddressActivity)
public class LocationActivity extends BaseActivity<ActivityLocationBinding, LocationViewModel> {
    private BaiduMap baiduMap;
    private GeoCoder geoCoder;
    private SuggestionSearch suggestionSearch;
    private LocationAdapter locationAdapter;
    private List<SuggestionResult.SuggestionInfo> suggestionInfoList = new ArrayList<>();

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_location;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("位置");
        viewModel.addressType.set(getIntent().getExtras().getInt("addressType"));
        //实例化一个地理编码查询对象
        geoCoder = GeoCoder.newInstance();
        baiduMap = binding.map.getMap();

        initRecycleView();
        nearbyPoiSearch();
        binding.etLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                initSuggestionSearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        RxSubscriptions.add(RxBus.getDefault().toObservable(SuggestionResult.SuggestionInfo.class).subscribe(suggestionInfo -> {
            LatLng latLng = suggestionInfo.getPt();
            initGeoCoder(latLng);
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(latLng).zoom(18.0f);
            baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            binding.etLocation.setText("");
            suggestionInfoList.clear();
            locationAdapter.notifyDataSetChanged();
        }));
    }

    /**
     * description:
     * author: Andy
     * date: 2019/9/21  20:09
     */
    private void initRecycleView() {
        locationAdapter = new LocationAdapter(R.layout.item_location, suggestionInfoList);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(locationAdapter);
    }

    /**
     * description:
     * author: Andy
     * date: 2019/9/21  20:09
     */
    public void nearbyPoiSearch() {
        baiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            //地图状态开始改变。
            public void onMapStatusChangeStart(MapStatus status) {

            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

            }

            //地图状态改变结束
            public void onMapStatusChangeFinish(MapStatus status) {
                //改变结束之后，获取地图可视范围的中心点坐标
                LatLng latLng = status.target;
                //拿到经纬度之后，就可以反地理编码获取地址信息了
                ZLog.d(latLng.latitude);
                ZLog.d(latLng.longitude);
                initGeoCoder(latLng);

            }

            //地图状态变化中
            public void onMapStatusChange(MapStatus status) {
            }
        });
    }

    /**
     * description:
     * author: Andy
     * date: 2019/9/21  20:10
     */
    private void initGeoCoder(LatLng latLng) {
        //设置反地理编码位置坐标
        ReverseGeoCodeOption op = new ReverseGeoCodeOption();
        op.location(latLng);
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                //获取点击的坐标地址
                ZLog.d(reverseGeoCodeResult.getAddress());
                if (IsNullUtil.getInstance().isEmpty(reverseGeoCodeResult.getAddress())) return;
                viewModel.nowAddress.set(reverseGeoCodeResult.getAddress());
                viewModel.longitude.set(reverseGeoCodeResult.getLocation().longitude+"");
                viewModel.latitude.set(reverseGeoCodeResult.getLocation().latitude+"");
            }
        });
        //发起反地理编码请求(经纬度->地址信息)
        geoCoder.reverseGeoCode(op);
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
            suggestionInfoList.clear();
            suggestionInfoList.addAll(suggestionResult.getAllSuggestions());
            locationAdapter.notifyDataSetChanged();
        });
        suggestionSearch.requestSuggestion(new SuggestionSearchOption().city("全国").keyword(search));
    }
}
