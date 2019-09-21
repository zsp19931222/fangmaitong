package com.techangkeji.module.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.goldze.base.eventbus.LocationRxBusBean;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.RxBus;

/**
 * description:
 * author:created by Andy on 2019/9/21 19:20
 * email:zsp872126510@gmail.com
 */
public class LocationViewModel extends BaseViewModel {
    public ObservableField<String> nowAddress = new ObservableField<>("");
    public ObservableField<String> searchText = new ObservableField<>("");
    public ObservableField<String> number = new ObservableField<>("");
    public ObservableField<String> longitude = new ObservableField<>("");
    public ObservableField<String> latitude = new ObservableField<>("");
    public ObservableField<Integer> addressType=new ObservableField<>(-1);

    public LocationViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand confirmCommand = new BindingCommand(() ->
    {
        LocationRxBusBean locationRxBusBean=new LocationRxBusBean(nowAddress.get() + number.get(),addressType.get(),longitude.get(),latitude.get());
        RxBus.getDefault().post(locationRxBusBean);
        finish();
    }
    );
}
