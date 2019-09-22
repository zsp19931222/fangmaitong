package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.goldze.base.utils.ParameterLogUtil;
import com.techangkeji.model_mine.ui.activity.HouseResourceActivity;
import com.techangkeji.model_mine.ui.activity.HouseResourceReleaseActivity;
import com.techangkeji.model_mine.ui.adapter.HouseResourceAdapter;
import com.techangkeji.module_hr.ui.bean.AreaPopupBean;
import com.techangkeji.module_hr.ui.popup.AreaPopupwindow;
import com.techangkeji.module_hr.ui.popup.FilterPopupwindow;
import com.techangkeji.module_hr.ui.popup.HouseTypePopupwindow;
import com.techangkeji.module_hr.ui.popup.PricePopupwindow;
import com.techangkeji.module_hr.ui.popup.SortPopupwindow;
import com.techangkeji.module_hr.ui.popup.TypePopupwindow;

import org.litepal.LitePal;

import java.util.HashMap;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.entity.AreaListEntity;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.http.net.entity.BuildingListEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.litepal.CityLitePal;
import me.goldze.mvvmhabit.litepal.DistrictLitePal;
import me.goldze.mvvmhabit.litepal.ProvinceLitePal;
import me.goldze.mvvmhabit.litepal.util.SaveAreaListUtil;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ZLog;

public class HouseResourceViewModel extends BaseViewModel {
    public ObservableField<Integer> areaShow = new ObservableField<>(View.VISIBLE);
    public ObservableField<Integer> typeShow = new ObservableField<>(View.GONE);
    public ObservableField<Integer> priceShow = new ObservableField<>(View.GONE);
    public ObservableField<Integer> screenShow = new ObservableField<>(View.GONE);
    public ObservableField<Integer> sortShow = new ObservableField<>(View.GONE);
    public ObservableField<Context> context = new ObservableField<>();
    public ObservableField<View> choiceView = new ObservableField<>();
    public ObservableList<AreaListEntity.DataBean> areaList = new ObservableArrayList<>();
    public ObservableList<BuildingListEntity.DataBean> buildingList = new ObservableArrayList<>();
    public ObservableField<HouseResourceAdapter> adapter = new ObservableField<>();

    public HouseResourceViewModel(@NonNull Application application) {
        super(application);
    }

    //发布房源
    public BindingCommand houseResourceReleaseCommand = new BindingCommand(() -> startActivity(HouseResourceReleaseActivity.class));

    public ObservableField<String> areaCode = new ObservableField<>("9");//区域编号
    public ObservableField<String> decoration = new ObservableField<>("");//装修
    public ObservableField<String> endTime = new ObservableField<>("");//结束时间
    public ObservableField<String> hotSort = new ObservableField<>("");//人气排序(1:从高到低)
    public ObservableField<String> isOpening = new ObservableField<>("");//开盘(0:默认排序,1:已开盘)
    public ObservableField<String> lat = new ObservableField<>("");//纬度(距离最近使用,默认null)
    public ObservableField<String> level = new ObservableField<>("");//区域等级
    public ObservableField<String> lon = new ObservableField<>("");//经度(距离最近使用,默认null)
    public ObservableField<String> openingTimeSort = new ObservableField<>("");//开盘时间排序(0:从远到近，1:从近到远)
    public ObservableField<String> priceMax = new ObservableField<>("");//价格最大值
    public ObservableField<String> priceMin = new ObservableField<>("");//价格最小值
    public ObservableField<String> priceSort = new ObservableField<>("");//均价排序(0:从低到高，1:从高到低)
    public ObservableField<String> propertyType = new ObservableField<>("");//物业类型
    public ObservableField<String> specialLabel = new ObservableField<>("");//特色标签
    public ObservableField<String> startTime = new ObservableField<>("");//开始时间
    public ObservableField<String> type = new ObservableField<>("");//1:住房,2:商业地产

    /**
     * description:获取房源列表
     * author: Andy
     * date: 2019/9/22  14:43
     */
    public void getData() {
        buildingList.clear();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("areaId", areaCode.get());
        parameter.put("decoration", decoration.get());
        parameter.put("endTime", endTime.get());
        parameter.put("hotSort", hotSort.get());
        parameter.put("isOpening", isOpening.get());
        parameter.put("lat", lat.get());
        parameter.put("level", level.get());
        parameter.put("lon", lon.get());
        parameter.put("openingTimeSort", openingTimeSort.get());
        parameter.put("priceMax", priceMax.get());
        parameter.put("priceMin", priceMin.get());
        parameter.put("priceSort", priceSort.get());
        parameter.put("propertyType", propertyType.get());
        parameter.put("specialLabel", specialLabel.get());
        parameter.put("startTime", startTime.get());
        parameter.put("type", type.get());
        ParameterLogUtil.getInstance().parameterLog(parameter);
        IdeaApi.getApiService()
                .getBuildingList(parameter)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<BuildingListEntity>() {
                    @Override
                    public void onSuccess(BuildingListEntity response) {
                        buildingList.addAll(response.getData());
                        adapter.get().notifyDataSetChanged();
                    }

                });
    }

    /**
     * description: 删除房源
     * author: Andy
     * date: 2019/9/22  17:26
     */
    public void delete(int position) {
        IdeaApi.getApiService()
                .deleteBuilding(buildingList.get(position).getId())
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<SuccessEntity>() {
                    @Override
                    public void onSuccess(SuccessEntity response) {
                        buildingList.remove(position);
                        adapter.get().notifyDataSetChanged();
                    }

                });
    }

    /**
     * description: 获取地区
     * author: Andy
     * date: 2019/9/22  14:55
     */
    public void getAreaList() {
        IdeaApi.getApiService()
                .listAllArea()
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable1 -> showDialog())
                .subscribe(new DefaultObserver<AreaListEntity>(this) {
                    @Override
                    public void onSuccess(AreaListEntity response) {
                        areaList.addAll(response.getData());
                        SaveAreaListUtil.getInstance().save(response);
                    }
                });
    }

    public BindingCommand areaCommand = new BindingCommand(() -> {
        initShow();
        areaShow.set(View.VISIBLE);
        new AreaPopupwindow(context.get(), areaList).showPopupWindow(choiceView.get());
    });
    public BindingCommand typeCommand = new BindingCommand(() -> {
        initShow();
        typeShow.set(View.VISIBLE);
        new HouseTypePopupwindow(context.get()).showPopupWindow(choiceView.get());
    });
    public BindingCommand priceCommand = new BindingCommand(() -> {
        initShow();
        priceShow.set(View.VISIBLE);
        PricePopupwindow pricePopupwindow = new PricePopupwindow(context.get());
        pricePopupwindow.setAdjustInputMethod(false);
        pricePopupwindow.showPopupWindow(choiceView.get());
    });
    public BindingCommand screenCommand = new BindingCommand(() -> {
        initShow();
        screenShow.set(View.VISIBLE);
        new FilterPopupwindow(context.get()).showPopupWindow(choiceView.get());
    });
    public BindingCommand sortCommand = new BindingCommand(() -> {
        initShow();
        sortShow.set(View.VISIBLE);
        new SortPopupwindow(context.get()).showPopupWindow(choiceView.get());

    });

    private void initShow() {
        areaShow.set(View.INVISIBLE);
        typeShow.set(View.INVISIBLE);
        priceShow.set(View.INVISIBLE);
        screenShow.set(View.INVISIBLE);
        sortShow.set(View.INVISIBLE);
    }
}
