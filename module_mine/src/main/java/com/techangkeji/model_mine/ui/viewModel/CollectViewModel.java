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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.techangkeji.model_mine.ui.adapter.CollectAdapter;
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
import me.goldze.mvvmhabit.http.net.entity.FeaturedLabelEntity;
import me.goldze.mvvmhabit.http.net.entity.RecommendBuildingEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.RxUtils;
import razerdp.basepopup.BasePopupWindow;

/**
 * description:
 * author:created by Andy on 2019/9/28 15:15
 * email:zsp872126510@gmail.com
 */
public class CollectViewModel extends BaseViewModel {
    public ObservableList<RecommendBuildingEntity.DataBean> list = new ObservableArrayList<>();
    public CollectAdapter collectAdapter;
    public int pageNum=1;

    public ObservableField<SmartRefreshLayout> srl = new ObservableField<>();
    public ObservableField<Integer> areaShow = new ObservableField<>(View.VISIBLE);
    public ObservableField<Integer> typeShow = new ObservableField<>(View.GONE);
    public ObservableField<Integer> priceShow = new ObservableField<>(View.GONE);
    public ObservableField<Integer> screenShow = new ObservableField<>(View.GONE);
    public ObservableField<Integer> sortShow = new ObservableField<>(View.GONE);
    public ObservableField<Context> context = new ObservableField<>();
    public ObservableField<View> choiceView = new ObservableField<>();


    public ObservableList<FeaturedLabelBean> featuredLabelList = new ObservableArrayList();//特色标签
    public ObservableList<FeaturedLabelBean> buildLabeList = new ObservableArrayList();//物业类型

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

    public CollectViewModel(@NonNull Application application) {
        super(application);
    }
    private void initShow() {
        areaShow.set(View.INVISIBLE);
        typeShow.set(View.INVISIBLE);
        priceShow.set(View.INVISIBLE);
        screenShow.set(View.INVISIBLE);
        sortShow.set(View.INVISIBLE);
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
    /**
     * description: 收藏列表
     * author: Andy
     * date: 2019/9/28  15:15
     */
    public void collectList() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", LocalDataHelper.getInstance().getUserInfo().getUserId());
        IdeaApi.getApiService().myCollectionList(map)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<RecommendBuildingEntity>(this) {
                    @Override
                    public void onSuccess(RecommendBuildingEntity response) {
                        list.addAll(response.getData());
                        collectAdapter.notifyDataSetChanged();
                    }
                });

    }

    /**
     * description: 取消收藏
     * author: Andy
     * date: 2019/9/28  15:09
     */
    public void deleteCollection(int position) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", LocalDataHelper.getInstance().getUserInfo().getUserId());
        map.put("listingId", list.get(position).getId());
        IdeaApi.getApiService().deleteCollection(map)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity>(this) {
                    @Override
                    public void onSuccess(SuccessEntity response) {
                        list.remove(position);
                        collectAdapter.notifyDataSetChanged();
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

        FilterPopupwindow filterPopupwindow = new FilterPopupwindow(context.get(), featuredLabelList, buildLabeList,true);
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
}
