package com.techangkeji.module_hr.ui.view_model;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.techangkeji.module_hr.ui.popup.AreaPopupwindow;
import com.techangkeji.module_hr.ui.popup.FilterPopupwindow;
import com.techangkeji.module_hr.ui.popup.PricePopupwindow;
import com.techangkeji.module_hr.ui.popup.SortPopupwindow;
import com.techangkeji.module_hr.ui.popup.TypePopupwindow;

import java.util.ArrayList;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.entity.AreaListEntity;

public class HRViewModel extends BaseViewModel {
    public ObservableField<Integer> areaShow = new ObservableField<>(View.VISIBLE);
    public ObservableField<Integer> typeShow = new ObservableField<>(View.GONE);
    public ObservableField<Integer> priceShow = new ObservableField<>(View.GONE);
    public ObservableField<Integer> screenShow = new ObservableField<>(View.GONE);
    public ObservableField<Integer> sortShow = new ObservableField<>(View.GONE);
    public ObservableField<Context> context = new ObservableField<>();
    public ObservableField<View> choiceView = new ObservableField<>();
    public ObservableList<AreaListEntity.DataBean> areaList = new ObservableArrayList<>();


    public HRViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand area = new BindingCommand(() -> {
        initShow();
        areaShow.set(View.VISIBLE);
        new AreaPopupwindow(context.get(),areaList).showPopupWindow(choiceView.get());
    });
    public BindingCommand type = new BindingCommand(() -> {
        initShow();
        typeShow.set(View.VISIBLE);
        new TypePopupwindow(context.get()).showPopupWindow(choiceView.get());
    });
    public BindingCommand price = new BindingCommand(() -> {
        initShow();
        priceShow.set(View.VISIBLE);
        PricePopupwindow pricePopupwindow = new PricePopupwindow(context.get());
        pricePopupwindow.setAdjustInputMethod(false);
        pricePopupwindow.showPopupWindow(choiceView.get());
    });
    public BindingCommand screen = new BindingCommand(() -> {
        initShow();
        screenShow.set(View.VISIBLE);
        new FilterPopupwindow(context.get(),new ArrayList<>(),new ArrayList<>()).showPopupWindow(choiceView.get());
    });
    public BindingCommand sort = new BindingCommand(() -> {
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
