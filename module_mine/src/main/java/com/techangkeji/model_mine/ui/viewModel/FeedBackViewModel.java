package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.techangkeji.model_mine.ui.activity.SubmitFeedBackActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * description:
 * author:created by Andy on 2019/9/28 16:37
 * email:zsp872126510@gmail.com
 */
public class FeedBackViewModel extends BaseViewModel {
    public FeedBackViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand newFeedBackCommand=new BindingCommand(() -> startActivity(SubmitFeedBackActivity.class));
}
