package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * description:
 * author:created by Andy on 2019/9/28 16:32
 * email:zsp872126510@gmail.com
 */
public class SubmitFeedBackViewModel extends BaseViewModel {
    public ObservableField<String> title=new ObservableField<>("");
    public ObservableField<String> content=new ObservableField<>("");
    public SubmitFeedBackViewModel(@NonNull Application application) {
        super(application);
    }
    public BindingCommand submitCommand=new BindingCommand(() -> {});
}
