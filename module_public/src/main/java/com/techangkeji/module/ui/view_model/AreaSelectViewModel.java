package com.techangkeji.module.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.techangkeji.module.ui.adapter.AreaListAdapter;
import com.techangkeji.module.ui.bean.AreaItemBean;
import com.techangkeji.module.ui.bean.AreaLevel0Bean;
import com.techangkeji.module.ui.bean.AreaLevel1Bean;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.entity.AreaListEntity;
import me.goldze.mvvmhabit.litepal.DistrictLitePal;
import me.goldze.mvvmhabit.litepal.UserInfoLitePal;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.RxUtils;

/**
 * description:
 * author:created by Andy on 2019/9/20 23:28
 * email:zsp872126510@gmail.com
 */
public class AreaSelectViewModel extends BaseViewModel {
    public ObservableList<MultiItemEntity> areaListBeans = new ObservableArrayList<>();
    public ObservableField<AreaListAdapter> adapter = new ObservableField<>();
    public ObservableField<String> city=new ObservableField<>("");

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
                        LitePal.deleteAll(DistrictLitePal.class);
                        for (AreaListEntity.DataBean datum : response.getData()) {
                            AreaLevel0Bean areaLevel0Bean = new AreaLevel0Bean(datum.getAreaName());
                            List<AreaItemBean> cityBeanList = new ArrayList<>();
                            AreaLevel1Bean areaLevel1Bean = null;
                            for (AreaListEntity.DataBean.ChildBeanX childBeanX : datum.getChild()) {
                                cityBeanList.add(new AreaItemBean(childBeanX.getId(), childBeanX.getParentId(), childBeanX.getAreaName(), false));
                                for (AreaListEntity.DataBean.ChildBeanX.ChildBean childBean : childBeanX.getChild()) {
                                    DistrictLitePal districtLitePal = new DistrictLitePal();
                                    districtLitePal.setAreaName(childBean.getAreaName());
                                    districtLitePal.setCityId(childBean.getParentId());
                                    districtLitePal.setDistrictid(childBean.getId());
                                    districtLitePal.setCityName(childBeanX.getAreaName());
                                    districtLitePal.save();
                                }
                                areaLevel1Bean = new AreaLevel1Bean(cityBeanList);
                            }
                            areaLevel0Bean.addSubItem(areaLevel1Bean);
                            areaListBeans.add(areaLevel0Bean);
                        }
                        adapter.get().notifyDataSetChanged();
                        adapter.get().expandAll();
                    }
                });
    }
}
