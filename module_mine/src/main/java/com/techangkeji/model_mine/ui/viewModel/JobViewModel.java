package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.blankj.utilcode.util.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.techangkeji.model_mine.ui.activity.InviteReleaseActivity;
import com.techangkeji.model_mine.ui.activity.JobReleaseActivity;
import com.techangkeji.model_mine.ui.adapter.InviteInformationAdapter;
import com.techangkeji.model_mine.ui.adapter.JobAdapter;

import java.util.HashMap;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.RecruitmentListBody;
import me.goldze.mvvmhabit.http.net.body.TcJobHuntingListBody;
import me.goldze.mvvmhabit.http.net.entity.JobHuntingEntity;
import me.goldze.mvvmhabit.http.net.entity.RecruitmentListEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtil;
import me.goldze.mvvmhabit.utils.ZLog;

public class JobViewModel extends BaseViewModel {
    public ObservableList<JobHuntingEntity.DataBean> dataBeans = new ObservableArrayList<>();
    public ObservableField<SmartRefreshLayout> srl = new ObservableField<>();
    public JobAdapter jobAdapter;

    public JobViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description: 跳转发布求职
     * author: Andy
     * date: 2019/9/27  22:42
     */
    public BindingCommand releaseCommand = new BindingCommand(() -> {
        if (LocalDataHelper.getInstance().getUserInfo().getIdentity() == 4) {//总代的身份
            if (LocalDataHelper.getInstance().getUserInfo().getRealNameAuthenticate() != 1) {
                ToastUtil.normalToast(BaseApplication.getInstance().getApplicationContext(), "需实名认证");
                return;
            }
        }

        startActivity(JobReleaseActivity.class);
    });

    @Override
    public void onResume() {
        super.onResume();
        recruitmentsList();
    }

    /**
     * description: 求职列表
     * author: Andy
     * date: 2019/9/26  22:51
     */
    private RecruitmentListBody myMovingListBody = new RecruitmentListBody();
    public int pageNum = 1;

    public void recruitmentsList() {
        if (pageNum == 1) {
            dataBeans.clear();
        }
        myMovingListBody.setPage(pageNum);
        myMovingListBody.setMax(20);
        ZLog.d(myMovingListBody);
        IdeaApi.getApiService()
                .getTcJobHuntingList(myMovingListBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<JobHuntingEntity>(srl.get(), this) {
                    @Override
                    public void onSuccess(JobHuntingEntity response) {
                        dataBeans.addAll(response.getData());
                        jobAdapter.notifyDataSetChanged();
                    }
                });
    }
    /**
     * description: 删除
     * author: Andy
     * date: 2019/10/9  23:17
     */
    public void delete(int position){
        IdeaApi.getApiService()
                .deleteTcJobHuntings(dataBeans.get(position).getId())
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity>(this) {
                    @Override
                    public void onSuccess(SuccessEntity response) {
                        ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "删除成功");
                        dataBeans.remove(position);
                        jobAdapter.notifyDataSetChanged();
                    }

                });
    }
}
