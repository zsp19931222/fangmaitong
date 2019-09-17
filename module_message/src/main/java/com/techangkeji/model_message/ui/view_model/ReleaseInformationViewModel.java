package com.techangkeji.model_message.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.body.ReleaseMovingBody;

/**
 * description:
 * author:created by Andy on 2019/9/17 0017 17:01
 * email:zsp872126510@gmail.com
 */
public class ReleaseInformationViewModel extends BaseViewModel {
    public ObservableField<String> imageUrls=new ObservableField<>("");
    public ObservableField<String> content=new ObservableField<>("");

    public ReleaseInformationViewModel(@NonNull Application application) {
        super(application);
    }


    /**
    * description:发布
    * author: Andy
    * date: 2019/9/17 0017 17:03
    */
    public BindingCommand releaseCommand=new BindingCommand(() -> {
//        ReleaseMovingBody releaseMovingBody=new ReleaseMovingBody()
    });
}
