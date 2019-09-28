package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.techangkeji.model_mine.ui.adapter.ReportAdapter;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.AppReportListBody;
import me.goldze.mvvmhabit.http.net.entity.AppReportListEntity;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/28 16:40
 * email:zsp872126510@gmail.com
 */
public class ReportViewModel extends BaseViewModel {
    public int page = 1;
    public ReportAdapter reportAdapter;
    public ObservableList<AppReportListEntity.DataBean> dataBeans = new ObservableArrayList<>();
    public ObservableField<SmartRefreshLayout> srl = new ObservableField<>();
    private AppReportListBody appReportListBody = new AppReportListBody();

    public ReportViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onResume() {
        super.onResume();
        appReportList();
    }

    /**
     * description:
     * author: Andy
     * date: 2019/9/28  21:36
     */
    public void appReportList() {
        if (page == 1) {
            dataBeans.clear();
        }
        appReportListBody.setMax(20);
        appReportListBody.setPage(page);
        ZLog.d(appReportListBody.getPage());
        IdeaApi.getApiService()
                .appReportList(appReportListBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<AppReportListEntity>(srl.get(), this) {
                    @Override
                    public void onSuccess(AppReportListEntity response) {
                        dataBeans.addAll(response.getData());
                        reportAdapter.notifyDataSetChanged();
                    }
                });
    }
}
