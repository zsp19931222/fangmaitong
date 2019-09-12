package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.techangkeji.model_mine.ui.adapter.HouseResourceReleaseAdapter;
import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseBannerBean;

import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * description:
 * author:created by Andy on 2019/9/12 0012 09:25
 * email:zsp872126510@gmail.com
 */
public class HouseResourceReleaseViewModel extends BaseViewModel {
    public ObservableList<HouseResourceReleaseBannerBean> bannerPathList=new ObservableArrayList<>();
    public ObservableList<String> labelList=new ObservableArrayList<>();
    public ObservableField<HouseResourceReleaseAdapter> adapterObservableField=new ObservableField<>();
    public HouseResourceReleaseViewModel(@NonNull Application application) {
        super(application);
    }
}
