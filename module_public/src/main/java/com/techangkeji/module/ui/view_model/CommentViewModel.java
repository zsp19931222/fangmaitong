package com.techangkeji.module.ui.view_model;

import android.app.Application;
import android.content.Context;
import android.view.WindowManager;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.kcrason.highperformancefriendscircle.widgets.EmojiPanelView;
import com.techangkeji.module.ui.popup.RemarkPopupwindow;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.CommentBody;
import me.goldze.mvvmhabit.http.net.body.CommentListBody;
import me.goldze.mvvmhabit.http.net.entity.information.CommentListEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ZLog;

public class CommentViewModel extends BaseViewModel {
    public ObservableField<EmojiPanelView> emojiPanelView = new ObservableField<>();
    public ObservableField<Context> context=new ObservableField<>();

    public CommentViewModel(@NonNull Application application) {
        super(application);
    }
    /**
     * description: 评论
     * author: Andy
     * date: 2019/9/25  21:14
     */
    public BindingCommand commentCommand = new BindingCommand(() -> {
        ZLog.d(context);
        RemarkPopupwindow remarkPopupwindow=new RemarkPopupwindow(context.get());
        ZLog.d(remarkPopupwindow);
        remarkPopupwindow.setAdjustInputMethod(false);
        CommentBody commentBody = new CommentBody("", 2, 0, LocalDataHelper.getInstance().getUserInfo().getUserId(), LocalDataHelper.getInstance().getUserInfo().getName());
        emojiPanelView.get().showEmojiPanel(commentBody);
        remarkPopupwindow.showPopupWindow();
    });
}
