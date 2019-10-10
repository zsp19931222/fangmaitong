package com.techangkeji.module.ui.view_model;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.kcrason.highperformancefriendscircle.widgets.EmojiPanelView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.techangkeji.module.ui.adapter.CommentStateAdapter;
import com.techangkeji.module.ui.popup.RemarkPopupwindow;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.TcReviewBody;
import me.goldze.mvvmhabit.http.net.body.TcReviewsListBody;
import me.goldze.mvvmhabit.http.net.entity.BuildingListEntity;
import me.goldze.mvvmhabit.http.net.entity.ReviewListEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.utils.RxUtils;

public class CommentViewModel extends BaseViewModel {
    public ObservableField<EmojiPanelView> emojiPanelView = new ObservableField<>();
    public ObservableField<Context> context = new ObservableField<>();
    public ObservableField<SmartRefreshLayout> srl=new ObservableField<>();

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


    /**
    * description: 获取点评列表
    * author: Andy
    * date: 2019/10/10 0010 10:25
    */
    public int pageNum=1;
    public TcReviewsListBody tcReviewsListBody=new TcReviewsListBody();
    public ObservableList<ReviewListEntity.DataBean> dataBeans=new ObservableArrayList<>();
    public CommentStateAdapter commentStateAdapter;
    public void tcReviewsList(){
        if (pageNum==1){
            dataBeans.clear();
        }
        tcReviewsListBody.setMax(20);
        tcReviewsListBody.setPage(pageNum);
        IdeaApi.getApiService()
                .tcReviewsList(tcReviewsListBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<ReviewListEntity>(srl.get()) {
                    @Override
                    public void onSuccess(ReviewListEntity response) {
                        dataBeans.addAll(response.getData());
                        commentStateAdapter.notifyDataSetChanged();
                    }

                });
    }

    /**
    * description: 新增点评
    * author: Andy
    * date: 2019/10/10 0010 10:33
    */
    public void tcReviews(TcReviewBody tcReviewBody){
        IdeaApi.getApiService()
                .tcReviews(tcReviewBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity>(this) {
                    @Override
                    public void onSuccess(SuccessEntity response) {
                        tcReviewsList();
                    }

                });
    }
}
