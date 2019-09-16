package com.techangkeji.model_home.ui.view_midel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.techangkeji.model_home.ui.activity.HousePledgeActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * description:
 * author:created by Andy on 2019/9/16 0016 18:11
 * email:zsp872126510@gmail.com
 */
public class HousingInformationViewModel extends BaseViewModel {
    public HousingInformationViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand housePledgeCommand=new BindingCommand(() -> {
        startActivity(HousePledgeActivity.class);
    });
}
