package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.alibaba.android.arouter.launcher.ARouter;
import com.goldze.base.constant.TipsConstants;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_mine.ui.activity.SelectFriendActivity;
import com.techangkeji.model_mine.ui.adapter.LinkManAdapter;
import com.techangkeji.model_mine.ui.bean.SelectFriendBean;
import com.techangkeji.model_mine.ui.popup.EducationStatePopupwindow;
import com.techangkeji.model_mine.ui.popup.JobStatePopupwindow;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.RecruitmentBody;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtil;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/26 0026 10:15
 * email:zsp872126510@gmail.com
 */
public class InviteReleaseViewModel extends BaseViewModel {
    public ObservableField<String> title = new ObservableField<>("标题");
    public ObservableField<String> position = new ObservableField<>("经理");
    public ObservableField<String> compensation = new ObservableField<>("2000-3000");
    public ObservableField<String> welfare = new ObservableField<>("双休");
    public ObservableField<String> education = new ObservableField<>("本科");
    public ObservableField<String> address = new ObservableField<>("");
    public ObservableField<String> job = new ObservableField<>("兼职");
    public ObservableField<String> synopsis = new ObservableField<>("没有公司简介");
    public ObservableField<String> province = new ObservableField<>("");
    public ObservableField<String> city = new ObservableField<>("");
    public ObservableField<String> district = new ObservableField<>("");
    public ObservableField<Context> context = new ObservableField<>();
    public ObservableField<TextView> tv_ir_education = new ObservableField<>();
    public ObservableField<TextView> tv_ir_job = new ObservableField<>();

    public ObservableField<LinkManAdapter> linkManAdapter = new ObservableField<>();
    public ObservableList<SelectFriendBean> linkManList = new ObservableArrayList<>();//联系人数据

    public InviteReleaseViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand addressCommand = new BindingCommand(() -> {
        ARouter.getInstance().build(ARouterPath.Public.MoreAddressActivity).withInt("addressType", 1).navigation();
    });

    /**
     * description:提交发布
     * author: Andy
     * date: 2019/9/26 0026 10:21
     */
    public BindingCommand submitCommand = new BindingCommand(() -> {
        if (IsNullUtil.getInstance().isEmpty(title.get()) ||
                IsNullUtil.getInstance().isEmpty(position.get()) ||
                IsNullUtil.getInstance().isEmpty(compensation.get()) ||
                IsNullUtil.getInstance().isEmpty(welfare.get()) ||
                IsNullUtil.getInstance().isEmpty(education.get()) ||
                IsNullUtil.getInstance().isEmpty(address.get()) ||
                IsNullUtil.getInstance().isEmpty(synopsis.get()) ||
                IsNullUtil.getInstance().isEmpty(job.get())) {
            ToastUtil.errorToast(context.get(), TipsConstants.PARAMETER_ERROR, false);
        } else {

            RecruitmentBody recruitmentBody = new RecruitmentBody();

            if ("面议".equals(compensation.get())) {
                recruitmentBody.setMoneyDown(0);
                recruitmentBody.setMoneyUp(0);
            } else {
                recruitmentBody.setMoneyDown(Double.valueOf(compensation.get().split("-")[0]));
                recruitmentBody.setMoneyUp(Double.valueOf(compensation.get().split("-")[1]));
            }

            recruitmentBody.setCity(city.get());
            recruitmentBody.setDistrict(district.get());
            recruitmentBody.setProvince(province.get());
            recruitmentBody.setWorkAddress(address.get());
            recruitmentBody.setRecruitmentTitle(title.get());
            recruitmentBody.setWorkContent(synopsis.get());
            recruitmentBody.setPosition(position.get());
            recruitmentBody.setWorkNature(job.get());
            recruitmentBody.setEducation(education.get());
            ZLog.d(recruitmentBody);
            IdeaApi.getApiService()
                    .addRecruitments(recruitmentBody)
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
    });

    /**
     * description: 选择学历
     * author: Andy
     * date: 2019/9/26 0026 11:05
     */
    public BindingCommand educationCommand = new BindingCommand(() -> {
        EducationStatePopupwindow educationStatePopupwindow = new EducationStatePopupwindow(context.get(), education.get());
        educationStatePopupwindow.setSelectListener(s -> education.set(s));
        educationStatePopupwindow.showPopupWindow(tv_ir_education.get());
    });

    /**
     * description: 选择工作性质
     * author: Andy
     * date: 2019/9/26 0026 11:16
     */
    public BindingCommand jobCommand = new BindingCommand(() -> {
        JobStatePopupwindow educationStatePopupwindow = new JobStatePopupwindow(context.get(), education.get());
        educationStatePopupwindow.setSelectListener(s -> job.set(s));
        educationStatePopupwindow.showPopupWindow(tv_ir_job.get());
    });

    /**
     * description:添加联系人
     * author: Andy
     * date: 2019/9/26 0026 11:46
     */
    public BindingCommand addLinkManCommand = new BindingCommand(() -> {
        startActivity(SelectFriendActivity.class);
    });
}
