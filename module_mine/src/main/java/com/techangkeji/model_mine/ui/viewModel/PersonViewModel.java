package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.techangkeji.model_mine.ui.activity.ChangeNickNameActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class PersonViewModel extends BaseViewModel {
    public PersonViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand changeNameCommand = new BindingCommand(() -> startActivity(ChangeNickNameActivity.class));
}
