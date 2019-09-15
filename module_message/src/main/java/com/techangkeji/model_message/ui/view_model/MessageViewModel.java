package com.techangkeji.model_message.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.techangkeji.model_message.ui.activity.BookActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * description:
 * author:created by Andy on 2019/9/10 0010 13:36
 * email:zsp872126510@gmail.com
 */
public class MessageViewModel extends BaseViewModel {
    public MessageViewModel(@NonNull Application application) {
        super(application);
    }

    //通讯录
    public BindingCommand bookCommand=new BindingCommand(() ->startActivity(BookActivity.class) );
}
