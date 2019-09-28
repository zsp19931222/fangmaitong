package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.goldze.base.constant.TipsConstants;

import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.FeedBackBody;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtil;

/**
 * description:
 * author:created by Andy on 2019/9/28 16:32
 * email:zsp872126510@gmail.com
 */
public class SubmitFeedBackViewModel extends BaseViewModel {
    public ObservableField<String> title = new ObservableField<>("");
    public ObservableField<String> content = new ObservableField<>("");

    public SubmitFeedBackViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand submitCommand = new BindingCommand(this::addFeedBack);

    /**
     * description: 添加反馈
     * author: Andy
     * date: 2019/9/28  22:51
     */
    private void addFeedBack() {
        if (IsNullUtil.getInstance().isEmpty(content.get()) || IsNullUtil.getInstance().isEmpty(title.get())) {
            ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), TipsConstants.PARAMETER_ERROR);
            return;
        }
        FeedBackBody feedBackBody = new FeedBackBody();
        feedBackBody.setContent(content.get());
        feedBackBody.setContent(title.get());
        feedBackBody.setUserId(LocalDataHelper.getInstance().getUserInfo().getUserId());
        IdeaApi.getApiService()
                .appFeedback(feedBackBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog()).subscribe(new DefaultObserver<SuccessEntity>(this) {
            @Override
            public void onSuccess(SuccessEntity response) {
                ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "反馈成功");
                finish();
            }

        });
    }
}
