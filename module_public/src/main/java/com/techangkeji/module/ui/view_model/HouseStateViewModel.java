package com.techangkeji.module.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.kcrason.highperformancefriendscircle.widgets.EmojiPanelView;
import com.techangkeji.module.ui.adapter.HouseStateAdapter;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.CommentBody;
import me.goldze.mvvmhabit.http.net.body.CommentListBody;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.friend_circle.CommentBean;
import me.goldze.mvvmhabit.http.net.entity.information.CommentListEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.RxUtils;

/**
 * description:
 * author:created by Andy on 2019/9/11 0011 15:49
 * email:zsp872126510@gmail.com
 */
public class HouseStateViewModel extends BaseViewModel {
    public int id;
    public ObservableField<EmojiPanelView> emojiPanelView = new ObservableField<>();

    public ObservableList<CommentListEntity.DataBean> dataBeans = new ObservableArrayList<>();
    public ObservableField<HouseStateAdapter> adapter = new ObservableField<>();

    public HouseStateViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description: 获取评论列表
     * author: Andy
     * date: 2019/9/25  23:10
     */
    public void getCommentList() {
        CommentListBody commentListBody = new CommentListBody(1, 10, 2, (long) id);
        IdeaApi.getApiService()
                .getCommentList(commentListBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<CommentListEntity>() {
                    @Override
                    public void onSuccess(CommentListEntity response) {
                        dataBeans.addAll(response.getData());
                        adapter.get().notifyDataSetChanged();
                    }

                });
    }
    /**
     * description: 评论
     * author: Andy
     * date: 2019/9/25  21:14
     */
    public BindingCommand commentCommand = new BindingCommand(() -> {
        CommentBody commentBody = new CommentBody("", 2, id, LocalDataHelper.getInstance().getUserInfo().getUserId(), LocalDataHelper.getInstance().getUserInfo().getName());
        emojiPanelView.get().showEmojiPanel(commentBody);
    });

    /**
     * description: 提交评论
     * author: Andy
     * date: 2019/9/25  22:01
     */
    public void comment(CommentBody commentBody) {
        IdeaApi.getApiService()
                .comment(commentBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity<CommentBean>>(this) {
                    @Override
                    public void onSuccess(SuccessEntity<CommentBean> response) {
                        getCommentList();
                    }
                });
    }
}
