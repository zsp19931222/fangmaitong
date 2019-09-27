package com.techangkeji.module_information.ui.view_model;

import android.app.Application;
import android.content.Context;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.techangkeji.module_hr.ui.popup.AreaPopupwindow;
import com.techangkeji.module_information.ui.adapter.InviteAdapter;
import com.techangkeji.module_information.ui.popup.CompensationPopupwindow;
import com.techangkeji.module_information.ui.popup.InformationSortPopupwindow;
import com.techangkeji.module_information.ui.popup.InviteSortPopupwindow;
import com.techangkeji.module_information.ui.popup.JobStatePopupwindow;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.RecruitmentListBody;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.http.net.entity.RecruitmentListEntity;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/26 0026 13:49
 * email:zsp872126510@gmail.com
 */
public class InviteViewModel extends BaseViewModel {
    public ObservableField<Context> context = new ObservableField<>();
    public ObservableField<LinearLayout> choiceView = new ObservableField<>();
    public ObservableField<SmartRefreshLayout> srl = new ObservableField<>();
    public int pageNum = 1;
    public ObservableList<RecruitmentListEntity.DataBean> dataBeans = new ObservableArrayList<>();
    public ObservableField<InviteAdapter> adapter = new ObservableField<>();
    public RecruitmentListBody recruitmentBody = new RecruitmentListBody();

    public InviteViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description: 招聘列表
     * author: Andy
     * date: 2019/9/26 0026 13:50
     */
    public void recruitmentsList() {
        if (pageNum == 1) {
            dataBeans.clear();
        }
        recruitmentBody.setMax(30);
        recruitmentBody.setPage(pageNum);
        ZLog.d(recruitmentBody);
        IdeaApi.getApiService()
                .recruitmentsList(recruitmentBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog()).subscribe(new DefaultObserver<RecruitmentListEntity>(srl.get(), this) {
            @Override
            public void onSuccess(RecruitmentListEntity response) {
                dataBeans.addAll(response.getData());
                adapter.get().notifyDataSetChanged();
            }

        });
    }

    public BindingCommand areaCommand = new BindingCommand(() -> {
        new AreaPopupwindow(context.get()).showPopupWindow(choiceView.get());
    });

    public BindingCommand compensationCommand = new BindingCommand(() -> new CompensationPopupwindow(context.get()).showPopupWindow(choiceView.get()));

    public BindingCommand jobStateCommand = new BindingCommand(() -> new JobStatePopupwindow(context.get()).showPopupWindow(choiceView.get()));
    public BindingCommand sortCommand = new BindingCommand(() -> {
        new InviteSortPopupwindow(context.get()).showPopupWindow(choiceView.get());
    });
}
