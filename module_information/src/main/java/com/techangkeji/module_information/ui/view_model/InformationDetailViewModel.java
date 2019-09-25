package com.techangkeji.module_information.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.goldze.base.utils.DateUtil;

import java.util.HashMap;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.information.NewsListEntity;
import me.goldze.mvvmhabit.http.net.entity.information.PlacardListEntity;
import me.goldze.mvvmhabit.utils.RxUtils;

/**
 * description:
 * author:created by Andy on 2019/9/25 0025 17:42
 * email:zsp872126510@gmail.com
 */
public class InformationDetailViewModel extends BaseViewModel {
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> time = new ObservableField<>("");
    public ObservableField<String> label = new ObservableField<>("");
    public ObservableField<String> look = new ObservableField<>("");
    public ObservableField<String> like = new ObservableField<>("");
    public ObservableField<String> comment = new ObservableField<>("");
    public ObservableField<String> content = new ObservableField<>("");

    public InformationDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void getNewsInfo(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        IdeaApi.getApiService()
                .getNewsInfo(map)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity<NewsListEntity.DataBean>>(this) {
                    @Override
                    public void onSuccess(SuccessEntity<NewsListEntity.DataBean> response) {
                        name.set(response.getContent().getNews_title());
                        time.set(response.getContent().getCreate_time());
                        StringBuilder stringBuilder = new StringBuilder();
                        for (String s : response.getContent().getLabel()) {
                            stringBuilder.append(s).append(" ");
                        }
                        label.set(stringBuilder.toString());
                        look.set(response.getContent().getLook_num() + "");
                        like.set(response.getContent().getLike_num() + "");
                        comment.set(response.getContent().getComment_num() + "");
                        content.set(response.getContent().getContent() + "");
                    }
                });
    }
}
