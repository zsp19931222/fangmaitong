package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.techangkeji.model_mine.ui.activity.HouseResourceActivity;
import com.techangkeji.model_mine.ui.activity.HouseResourceReleaseActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class HouseResourceViewModel extends BaseViewModel {
    public HouseResourceViewModel(@NonNull Application application) {
        super(application);
    }
    //发布房源
    public BindingCommand houseResourceReleaseCommand = new BindingCommand(() -> startActivity(HouseResourceReleaseActivity.class));
}
