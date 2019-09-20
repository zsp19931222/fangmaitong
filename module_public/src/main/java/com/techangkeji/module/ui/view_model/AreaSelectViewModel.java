package com.techangkeji.module.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.techangkeji.module.ui.adapter.AreaListAdapter;
import com.techangkeji.module.ui.bean.AreaItemBean;
import com.techangkeji.module.ui.bean.AreaListBean;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.entity.AreaListEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/20 23:28
 * email:zsp872126510@gmail.com
 */
public class AreaSelectViewModel extends BaseViewModel {
    public ObservableList<AreaListBean> areaListBeans = new ObservableArrayList<>();
    public ObservableField<AreaListAdapter> adapter=new ObservableField<>();

    public AreaSelectViewModel(@NonNull Application application) {
        super(application);
    }

    public void getData() {
        IdeaApi.getApiService()
                .listAllArea()
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable1 -> showDialog())
                .subscribe(new DefaultObserver<AreaListEntity>(this) {
                    @Override
                    public void onSuccess(AreaListEntity response) {
                        for (AreaListEntity.DataBean datum : response.getData()) {
                            List<AreaItemBean> areaItemBeansLevel1 = new ArrayList<>();
                            for (AreaListEntity.DataBean.ChildBeanX childBeanX : datum.getChild()) {
                                areaItemBeansLevel1.add(new AreaItemBean(childBeanX.getId(), childBeanX.getParentId(), childBeanX.getAreaName(), false));
                                List<AreaItemBean> areaItemBeansLevel2 = new ArrayList<>();
                                for (AreaListEntity.DataBean.ChildBeanX.ChildBean childBean : childBeanX.getChild()) {
                                    areaItemBeansLevel2.add(new AreaItemBean(childBean.getId(), childBean.getParentId(), childBean.getAreaName(), false));
                                    areaListBeans.add(new AreaListBean(areaItemBeansLevel2));
                                }
                            }
                            ZLog.d(datum.getAreaName());
                            areaListBeans.add(new AreaListBean(true, datum.getAreaName(), areaItemBeansLevel1));
                        }
                        adapter.get().notifyDataSetChanged();
                    }
                });
    }
}
