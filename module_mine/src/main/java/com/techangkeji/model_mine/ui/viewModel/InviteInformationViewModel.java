package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.techangkeji.model_mine.ui.activity.InviteReleaseActivity;
import com.techangkeji.model_mine.ui.activity.JobReleaseActivity;
import com.techangkeji.model_mine.ui.adapter.InviteInformationAdapter;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.RecruitmentBody;
import me.goldze.mvvmhabit.http.net.body.RecruitmentListBody;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.http.net.entity.RecruitmentListEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.RxUtils;

public class InviteInformationViewModel extends BaseViewModel {
    public ObservableList<RecruitmentListEntity.DataBean> dataBeans = new ObservableArrayList<>();
    public ObservableField<InviteInformationAdapter> adapter = new ObservableField<>();
    public int pageNum = 1;

    public InviteInformationViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description: 跳转发布招聘
     * author: Andy
     * date: 2019/9/27  22:42
     */
    public BindingCommand releaseCommand = new BindingCommand(() -> startActivity(InviteReleaseActivity.class));

    /**
     * description: 招聘列表
     * author: Andy
     * date: 2019/9/26  22:51
     */
    public void recruitmentsList() {
        RecruitmentListBody recruitmentBody = new RecruitmentListBody();
        recruitmentBody.setMax(20);
        recruitmentBody.setPage(pageNum);
        recruitmentBody.setRecruitmentHumenId(LocalDataHelper.getInstance().getUserInfo().getUserId());
        IdeaApi.getApiService()
                .recruitmentsList(recruitmentBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<RecruitmentListEntity>(this) {
                    @Override
                    public void onSuccess(RecruitmentListEntity response) {
                        dataBeans.addAll(response.getData());
                        adapter.get().notifyDataSetChanged();
                    }

                });
    }
}
