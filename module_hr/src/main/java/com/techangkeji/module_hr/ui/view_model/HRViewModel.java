package com.techangkeji.module_hr.ui.view_model;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.techangkeji.module_hr.ui.popup.AreaPopupwindow;
import com.techangkeji.module_hr.ui.popup.PricePopupwindow;
import com.techangkeji.module_hr.ui.popup.SortPopupwindow;
import com.techangkeji.module_hr.ui.popup.TypePopupwindow;

import java.util.concurrent.atomic.AtomicReferenceArray;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class HRViewModel extends BaseViewModel {
    public ObservableField<Integer> areaShow = new ObservableField<>(View.VISIBLE);
    public ObservableField<Integer> typeShow = new ObservableField<>(View.GONE);
    public ObservableField<Integer> priceShow = new ObservableField<>(View.GONE);
    public ObservableField<Integer> screenShow = new ObservableField<>(View.GONE);
    public ObservableField<Integer> sortShow = new ObservableField<>(View.GONE);
    public ObservableField<Context> context = new ObservableField<>();
    public ObservableField<View> choiceView = new ObservableField<>();

    public HRViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand area = new BindingCommand(() -> {
        initShow();
        areaShow.set(View.VISIBLE);
        new AreaPopupwindow(context.get()).showPopupWindow(choiceView.get());
    });
    public BindingCommand type = new BindingCommand(() ->{
        initShow();
        typeShow.set(View.VISIBLE);
        new TypePopupwindow(context.get()).showPopupWindow(choiceView.get());
    });
    public BindingCommand price = new BindingCommand(() ->{
        initShow();
        priceShow.set(View.VISIBLE);
        new PricePopupwindow(context.get()).showPopupWindow(choiceView.get());
    });
    public BindingCommand screen = new BindingCommand(() ->{
        initShow();
        screenShow.set(View.VISIBLE);
    });
    public BindingCommand sort = new BindingCommand(() ->{
        initShow();
        sortShow.set(View.VISIBLE);
        new SortPopupwindow(context.get()).showPopupWindow(choiceView.get());

    });

    private void initShow(){
        areaShow.set(View.GONE);
        typeShow.set(View.GONE);
        priceShow.set(View.GONE);
        screenShow.set(View.GONE);
        sortShow.set(View.GONE);
    }

}
