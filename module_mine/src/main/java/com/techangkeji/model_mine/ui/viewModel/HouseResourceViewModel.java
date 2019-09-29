package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.blankj.utilcode.util.SPUtils;
import com.goldze.base.bean.FeaturedLabelBean;
import com.goldze.base.utils.ParameterLogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.techangkeji.model_mine.ui.activity.HouseResourceReleaseActivity;
import com.techangkeji.model_mine.ui.adapter.HouseResourceAdapter;
import com.techangkeji.module_hr.ui.popup.AreaPopupwindow;
import com.techangkeji.module_hr.ui.popup.FilterPopupwindow;
import com.techangkeji.module_hr.ui.popup.HouseTypePopupwindow;
import com.techangkeji.module_hr.ui.popup.PricePopupwindow;
import com.techangkeji.module_hr.ui.popup.SortPopupwindow;

import java.util.HashMap;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.entity.AreaListEntity;
import me.goldze.mvvmhabit.http.net.entity.BuildingListEntity;
import me.goldze.mvvmhabit.http.net.entity.FeaturedLabelEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.litepal.util.SaveAreaListUtil;
import me.goldze.mvvmhabit.utils.RxUtils;
import razerdp.basepopup.BasePopupWindow;

public class HouseResourceViewModel extends BaseViewModel {
    public ObservableField<Integer> areaShow = new ObservableField<>(View.VISIBLE);
    public ObservableField<Integer> typeShow = new ObservableField<>(View.GONE);
    public ObservableField<Integer> priceShow = new ObservableField<>(View.GONE);
    public ObservableField<Integer> screenShow = new ObservableField<>(View.GONE);
    public ObservableField<Integer> sortShow = new ObservableField<>(View.GONE);
    public ObservableField<Context> context = new ObservableField<>();
    public ObservableField<View> choiceView = new ObservableField<>();
    public ObservableList<BuildingListEntity.DataBean> buildingList = new ObservableArrayList<>();
    public ObservableField<HouseResourceAdapter> adapter = new ObservableField<>();
    public ObservableList<FeaturedLabelBean> featuredLabelList = new ObservableArrayList();//特色标签
    public ObservableList<FeaturedLabelBean> buildLabeList = new ObservableArrayList();//物业类型

    public ObservableField<SmartRefreshLayout> srl = new ObservableField<>();

    public HouseResourceViewModel(@NonNull Application application) {
        super(application);

    }

    //发布房源
    public BindingCommand houseResourceReleaseCommand = new BindingCommand(() -> startActivity(HouseResourceReleaseActivity.class));

    public ObservableField<String> areaCode = new ObservableField<>(SPUtils.getInstance().getString("areaId"));//区域编号
    public ObservableField<String> decoration = new ObservableField<>("");//装修(1-精装，2-简装，3-毛坯)
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
    public ObservableField<String> houseType = new ObservableField<>("");//户型
    public ObservableField<String> areaMin = new ObservableField<>("");//最小面积
    public ObservableField<String> areaMax = new ObservableField<>("");//最大面积
    public ObservableField<String> openType = new ObservableField<>("");//开盘时间（1-本月，2-下月，3-半年内，4-已开盘）

    /**
     * description:获取房源列表
     * author: Andy
     * date: 2019/9/22  14:43
     */
    public void getData(int pageNum) {
        if (pageNum == 1) {
            buildingList.clear();
        }
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
        parameter.put("areaMin", areaMin.get());
        parameter.put("areaMax", areaMax.get());
        parameter.put("openType", openType.get());
        parameter.put("houseType", houseType.get());
        parameter.put("pageSize", "20");
        parameter.put("pageNum", pageNum + "");
        parameter.put("id", LocalDataHelper.getInstance().getUserInfo().getUserId());
        ParameterLogUtil.getInstance().parameterLog(parameter);
        IdeaApi.getApiService()
                .myBuildingList(parameter)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<BuildingListEntity>(srl.get(), this) {
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
        new AreaPopupwindow(context.get()).showPopupWindow(choiceView.get());
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

        FilterPopupwindow filterPopupwindow = new FilterPopupwindow(context.get(), featuredLabelList, buildLabeList);
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
