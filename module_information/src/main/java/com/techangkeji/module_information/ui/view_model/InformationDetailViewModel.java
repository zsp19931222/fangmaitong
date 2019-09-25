package com.techangkeji.module_information.ui.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.kcrason.highperformancefriendscircle.widgets.EmojiPanelView;
import com.techangkeji.module_information.ui.adapter.InformationDetailAdapter;

import java.util.HashMap;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.CommentBody;
import me.goldze.mvvmhabit.http.net.body.CommentListBody;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.friend_circle.CommentBean;
import me.goldze.mvvmhabit.http.net.entity.information.CommentListEntity;
import me.goldze.mvvmhabit.http.net.entity.information.NewsListEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.RxUtils;

/**
 * description:
 * author:created by Andy on 2019/9/25 0025 17:42
 * email:zsp872126510@gmail.com
 */
public class InformationDetailViewModel extends BaseViewModel {
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> time = new ObservableField<>("");
    public ObservableField<String> label = new ObservableField<>("");
    public ObservableField<String> look = new ObservableField<>("");
    public ObservableField<String> like = new ObservableField<>("");
    public ObservableField<String> comment = new ObservableField<>("");
    public ObservableField<String> content = new ObservableField<>("");
    public ObservableField<EmojiPanelView> emojiPanelView = new ObservableField<>();
    public String id;
    public ObservableList<CommentListEntity.DataBean> commentListList=new ObservableArrayList<>();
    public ObservableField<String> commentSize=new ObservableField<>("");
    public ObservableField<InformationDetailAdapter> adapter=new ObservableField<>();


    public InformationDetailViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description: 评论
     * author: Andy
     * date: 2019/9/25  21:14
     */
    public BindingCommand commentCommand = new BindingCommand(() -> {
        CommentBody commentBody = new CommentBody("", 1, Integer.valueOf(id), LocalDataHelper.getInstance().getUserInfo().getUserId(), LocalDataHelper.getInstance().getUserInfo().getName());
        emojiPanelView.get().showEmojiPanel(commentBody);
    });

    /**
     * description: 获取资讯详情
     * author: Andy
     * date: 2019/9/25  22:01
     */
    public void getNewsInfo(String id) {
        this.id = id;
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        IdeaApi.getApiService()
                .getNewsInfo(map)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity<NewsListEntity.DataBean>>(this) {
                    @Override
                    public void onSuccess(SuccessEntity<NewsListEntity.DataBean> response) {
                        name.set(response.getContent().getType());
                        time.set(response.getContent().getCreate_time());
                        StringBuilder stringBuilder = new StringBuilder();
                        for (String s : response.getContent().getLabel()) {
                            stringBuilder.append(s).append(" ");
                        }
                        label.set(stringBuilder.toString());
                        look.set(response.getContent().getLook_num() + "");
                        like.set(response.getContent().getLike_num() + "");
                        comment.set(response.getContent().getComment_num() + "");
                        content.set(response.getContent().getContent() + "");
                    }
                });
    }

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

    /**
     * description: 获取评论列表
     * author: Andy
     * date: 2019/9/25  23:10
     */
    public void getCommentList(){
        CommentListBody commentListBody=new CommentListBody(1,10,1,Long.valueOf(id));
        IdeaApi.getApiService()
                .getCommentList(commentListBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<CommentListEntity>() {
                    @Override
                    public void onSuccess(CommentListEntity response) {
                        commentListList.addAll(response.getData());
                        commentSize.set("回复（"+commentListList.size()+"）");
                        adapter.get().notifyDataSetChanged();
                    }

                });
    }
}
