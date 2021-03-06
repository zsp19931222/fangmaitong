package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.alibaba.android.arouter.launcher.ARouter;
import com.goldze.base.constant.TipsConstants;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_mine.ui.bean.SelectFriendBean;
import com.techangkeji.model_mine.ui.popup.JobStatePopupwindow;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.RecruitmentBody;
import me.goldze.mvvmhabit.http.net.body.RecruitmentListBody;
import me.goldze.mvvmhabit.http.net.body.TcJobHuntingBody;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtil;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/27 22:26
 * email:zsp872126510@gmail.com
 */
public class JobReleaseViewModel extends BaseViewModel {
    public ObservableField<String> title = new ObservableField<>("");
    public ObservableField<String> year = new ObservableField<>("");
    public ObservableField<String> address = new ObservableField<>("");
    public ObservableField<String> synopsis = new ObservableField<>("");
    public ObservableField<String> job = new ObservableField<>("");
    public ObservableField<String> phone = new ObservableField<>(IsNullUtil.getInstance().StringNull(LocalDataHelper.getInstance().getUserInfo().getPhone()));
    public ObservableField<String> headUrl = new ObservableField<>(IsNullUtil.getInstance().StringNull(LocalDataHelper.getInstance().getUserInfo().getHeadUrl()));
    public ObservableField<String> province = new ObservableField<>("");
    public ObservableField<String> city = new ObservableField<>("");
    public ObservableField<String> district = new ObservableField<>("");
    public ObservableField<Long> id = new ObservableField<>();

    public ObservableField<String> btn = new ObservableField<>("");
    public ObservableField<Context> context = new ObservableField<>();
    public ObservableField<TextView> tv_ir_job = new ObservableField<>();

    public BindingCommand addressCommand = new BindingCommand(() -> {
        ARouter.getInstance().build(ARouterPath.Public.MoreAddressActivity).withInt("addressType", 1).navigation();
    });

    public JobReleaseViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description: 发布
     * author: Andy
     * date: 2019/9/27  22:32
     */
    public BindingCommand submitCommand = new BindingCommand(() -> {
        if (IsNullUtil.getInstance().isEmpty(title.get()) ||
                IsNullUtil.getInstance().isEmpty(address.get()) ||
                IsNullUtil.getInstance().isEmpty(synopsis.get()) ||
                IsNullUtil.getInstance().isEmpty(year.get()) ||
                IsNullUtil.getInstance().isEmpty(job.get())) {
            ToastUtil.errorToast(context.get(), TipsConstants.PARAMETER_ERROR, false);
        } else {
            TcJobHuntingBody recruitmentBody = new TcJobHuntingBody();
            recruitmentBody.setCity(city.get());
            recruitmentBody.setDistrict(district.get());
            recruitmentBody.setProvince(province.get());
            recruitmentBody.setWorkAddress(address.get());
            recruitmentBody.setHuntingTitle(title.get());
            recruitmentBody.setWorkContent(synopsis.get());
            recruitmentBody.setWorkNature(job.get());
            recruitmentBody.setWorkYear(year.get());

            recruitmentBody.setHuntingHumenId(LocalDataHelper.getInstance().getUserInfo().getUserId());
            recruitmentBody.setContactId((int) LocalDataHelper.getInstance().getUserInfo().getUserId());
            if ("重新提交".equals(btn.get())) {//代表是修改
                recruitmentBody.setId(id.get());
                IdeaApi.getApiService()
                        .changeTcJobHuntings(recruitmentBody)
                        .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                        .compose(RxUtils.schedulersTransformer())
                        .doOnSubscribe(disposable -> showDialog())
                        .subscribe(new DefaultObserver<SuccessEntity>(this) {
                            @Override
                            public void onSuccess(SuccessEntity response) {
                                ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "修改成功");
                                finish();
                            }

                        });
            } else {
                IdeaApi.getApiService()
                        .tcJobHuntings(recruitmentBody)
                        .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                        .compose(RxUtils.schedulersTransformer())
                        .doOnSubscribe(disposable -> showDialog())
                        .subscribe(new DefaultObserver<SuccessEntity>(this) {
                            @Override
                            public void onSuccess(SuccessEntity response) {
                                ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "发布成功");
                                finish();
                            }

                        });
            }
            ZLog.d(recruitmentBody);

        }
    });

    /**
     * description: 选择工作性质
     * author: Andy
     * date: 2019/9/26 0026 11:16
     */
    public BindingCommand jobCommand = new BindingCommand(() -> {
        JobStatePopupwindow educationStatePopupwindow = new JobStatePopupwindow(context.get(), job.get());
        educationStatePopupwindow.setSelectListener(s -> job.set(s));
        educationStatePopupwindow.showPopupWindow(tv_ir_job.get());
    });
}