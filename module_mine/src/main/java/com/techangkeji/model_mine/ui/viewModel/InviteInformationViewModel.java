package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.techangkeji.model_mine.ui.activity.InviteReleaseActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class InviteInformationViewModel extends BaseViewModel {
    public InviteInformationViewModel(@NonNull Application application) {
        super(application);
    }
    public BindingCommand releaseCommand=new BindingCommand(() -> startActivity(InviteReleaseActivity.class));

}
