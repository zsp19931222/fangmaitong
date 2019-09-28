package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.techangkeji.model_mine.ui.activity.SubmitFeedBackActivity;
import com.techangkeji.model_mine.ui.adapter.ReportAdapter;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.AppReportListBody;
import me.goldze.mvvmhabit.http.net.entity.AppReportListEntity;
import me.goldze.mvvmhabit.utils.RxUtils;

/**
 * description:
 * author:created by Andy on 2019/9/28 16:37
 * email:zsp872126510@gmail.com
 */
public class FeedBackViewModel extends BaseViewModel {
    public int page = 1;
    public ReportAdapter reportAdapter;
    public ObservableList<AppReportListEntity.DataBean> dataBeans = new ObservableArrayList<>();
    public ObservableField<SmartRefreshLayout> srl = new ObservableField<>();
    private AppReportListBody appReportListBody = new AppReportListBody();

    public FeedBackViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand newFeedBackCommand = new BindingCommand(() -> startActivity(SubmitFeedBackActivity.class));


    @Override
    public void onResume() {
        super.onResume();
        appFeedbackList();
    }

    /**
     * description:
     * author: Andy
     * date: 2019/9/28  23:03
     */
    public void appFeedbackList() {
        appReportListBody.setPage(page);
        appReportListBody.setMax(20);
        IdeaApi.getApiService()
                .appFeedbackList(appReportListBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<AppReportListEntity>(this) {
            @Override
            public void onSuccess(AppReportListEntity response) {
                dataBeans.addAll(response.getData());
                reportAdapter.notifyDataSetChanged();
            }

        });
    }
}
