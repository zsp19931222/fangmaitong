package com.techangkeji.module_information.ui.view_model;

import android.app.Application;
import android.content.Context;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.techangkeji.module_hr.ui.popup.AreaPopupwindow;
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
import me.goldze.mvvmhabit.utils.RxUtils;

/**
 * description:
 * author:created by Andy on 2019/9/26 0026 13:49
 * email:zsp872126510@gmail.com
 */
public class InviteViewModel extends BaseViewModel {
    public ObservableField<Context> context = new ObservableField<>();
    public ObservableField<LinearLayout> choiceView = new ObservableField<>();
    public ObservableField<String> moneyDown = new ObservableField<>("");
    public ObservableField<String> moneyUp = new ObservableField<>("");
    public ObservableField<String> workNature = new ObservableField<>("");

    public InviteViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description: 招聘列表
     * author: Andy
     * date: 2019/9/26 0026 13:50
     */
    public void recruitmentsList() {
        RecruitmentListBody recruitmentListBody = new RecruitmentListBody();
        IdeaApi.getApiService()
                .recruitmentsList(recruitmentListBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog()).subscribe(new DefaultObserver(this) {
            @Override
            public void onSuccess(BaseEntity response) {

            }

            @Override
            public void onNext(Object o) {

            }
        });
    }

    public BindingCommand areaCommand = new BindingCommand(() -> {
        new AreaPopupwindow(context.get()).showPopupWindow(choiceView.get());
    });

    public BindingCommand compensationCommand = new BindingCommand(() -> new CompensationPopupwindow(context.get()).showPopupWindow(choiceView.get()));

    public BindingCommand jobStateCommand=new BindingCommand(() -> new JobStatePopupwindow(context.get()).showPopupWindow(choiceView.get()));
    public BindingCommand sortCommand=new BindingCommand(() -> {
        new InviteSortPopupwindow(context.get()).showPopupWindow(choiceView.get());
    });
}
