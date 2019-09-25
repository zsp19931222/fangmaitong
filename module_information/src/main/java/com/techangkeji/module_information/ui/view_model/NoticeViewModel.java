package com.techangkeji.module_information.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.SPUtils;
import com.goldze.base.utils.DateUtil;

import java.util.HashMap;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.entity.information.PlacardListEntity;
import me.goldze.mvvmhabit.utils.RxUtils;

/**
 * description:
 * author:created by Andy on 2019/9/25 0025 15:33
 * email:zsp872126510@gmail.com
 */
public class NoticeViewModel extends BaseViewModel {
    public ObservableField<String> create_time=new ObservableField<>("");
    public ObservableField<String> show_img_url=new ObservableField<>("");
    public ObservableField<String> placard_title=new ObservableField<>("");
    public ObservableField<String> look_num=new ObservableField<>("");
    public ObservableField<String> content=new ObservableField<>("");
    public NoticeViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description:
     * author: Andy
     * date: 2019/9/25 0025 15:34
     */
    public void getPlacardInfo(String id) {
        Map<String,Object> map=new HashMap<>();
        map.put("id", id);
        IdeaApi.getApiService()
                .getPlacardInfo(map)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<PlacardListEntity>(this) {
                    @Override
                    public void onSuccess(PlacardListEntity response) {
                        for (PlacardListEntity.DataBean datum : response.getData()) {
                            create_time.set(DateUtil.getInstance().getData(datum.getCreate_time()));
                            show_img_url.set(datum.getShow_img_url());
                            placard_title.set(datum.getPlacard_title());
                            look_num.set(datum.getLook_num()+"");
                            content.set(datum.getContent());
                        }
                    }
                });
    }
}
