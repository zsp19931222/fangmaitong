package com.techangkeji.module.ui.view_model;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.kcrason.highperformancefriendscircle.widgets.EmojiPanelView;
import com.techangkeji.module.ui.popup.RemarkPopupwindow;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class CommentViewModel extends BaseViewModel {
    public ObservableField<EmojiPanelView> emojiPanelView = new ObservableField<>();
    public ObservableField<Context> context = new ObservableField<>();

    public CommentViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description: 评论
     * author: Andy
     * date: 2019/9/25  21:14
     */
    public BindingCommand commentCommand = new BindingCommand(() -> {
        RemarkPopupwindow remarkPopupwindow=new RemarkPopupwindow(context.get());
        remarkPopupwindow.showPopupWindow();
    });
}
