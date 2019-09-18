package com.techangkeji.module_friend_circle.ui.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.kcrason.highperformancefriendscircle.Constants;
import com.kcrason.highperformancefriendscircle.beans.CommentBean;
import com.kcrason.highperformancefriendscircle.beans.FriendCircleBean;
import com.kcrason.highperformancefriendscircle.beans.OtherInfoBean;
import com.kcrason.highperformancefriendscircle.beans.PraiseBean;
import com.kcrason.highperformancefriendscircle.beans.UserBean;
import com.kcrason.highperformancefriendscircle.utils.SpanUtils;
import com.techangkeji.module_friend_circle.ui.adapter.MyStateAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.CommentBody;
import me.goldze.mvvmhabit.http.net.body.MyMovingListBody;
import me.goldze.mvvmhabit.http.net.body.VoteBody;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.friend_circle.MyStateEntity;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtil;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/17 20:52
 * email:zsp872126510@gmail.com
 */
public class MyStateViewModel extends BaseViewModel {
    public ObservableField<Integer> page = new ObservableField<>(1);
    public ObservableList<FriendCircleBean> friendCircleBeans = new ObservableArrayList<>();

    public MyStateViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * description: 我的动态列表
     * author: Andy
     * date: 2019/9/17  20:53
     */
    public void myMovingList(Context context, MyStateAdapter myStateAdapter) {
        MyMovingListBody myMovingListBody = new MyMovingListBody(20, page.get());
        IdeaApi.getApiService()
                .myMovingList(myMovingListBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<MyStateEntity>() {
                    @Override
                    public void onSuccess(MyStateEntity response) {
                        for (MyStateEntity.DataBean datum : response.getData()) {
                            FriendCircleBean friendCircleBean = new FriendCircleBean();
                            friendCircleBean.setId(datum.getId());//设置id
                            //设置评论
                            friendCircleBean.setCommentBeans(makeCommentBeans(datum.getCommentList(), context));

                            ZLog.d(IsNullUtil.getInstance().isEmpty(datum.getImgsUrl()));
                            if (!IsNullUtil.getInstance().isEmpty(datum.getImgsUrl())) {
                                String[] strs = datum.getImgsUrl().split(",");
                                List<String> images = new ArrayList<>(Arrays.asList(strs));
                                friendCircleBean.setImageUrls(images);//设置图片
                            }

                            //设置点赞
                            List<PraiseBean> praiseBeans = new ArrayList<>();
                            for (MyStateEntity.DataBean.VoteUserBean voteUserBean : datum.getVoteUser()) {
                                PraiseBean praiseBean = new PraiseBean();
                                praiseBean.setPraiseUserName(voteUserBean.getName());
                                praiseBean.setPraiseUserId(voteUserBean.getId());
                                praiseBeans.add(praiseBean);
                            }
                            friendCircleBean.setPraiseSpan(SpanUtils.makePraiseSpan(context, praiseBeans));
                            friendCircleBean.setPraiseBeans(praiseBeans);

                            //设置文案
                            friendCircleBean.setContent(datum.getContent());

//                            UserBean userBean = new UserBean();
//                            userBean.setUserName(Constants.USER_NAME[(int) (Math.random() * 30)]);
//                            userBean.setUserAvatarUrl(Constants.IMAGE_URL[(int) (Math.random() * 50)]);
//                            friendCircleBean.setUserBean(userBean);
//
//
                            OtherInfoBean otherInfoBean = new OtherInfoBean();
                            otherInfoBean.setTime(datum.getCreateTime());
                            friendCircleBean.setOtherInfoBean(otherInfoBean);
                            friendCircleBeans.add(friendCircleBean);

                        }
                        myStateAdapter.notifyDataSetChanged();
                    }
                });
    }

    private static List<CommentBean> makeCommentBeans(List<MyStateEntity.DataBean.CommentListBean> commentList, Context context) {
        List<CommentBean> commentBeans = new ArrayList<>();
        for (MyStateEntity.DataBean.CommentListBean commentListBean : commentList) {
            CommentBean commentBean = new CommentBean();
            if (commentListBean.getOtherUserId() == 0) {
                commentBean.setCommentType(Constants.CommentType.COMMENT_TYPE_SINGLE);
                commentBean.setChildUserName(commentListBean.getUsername());
            } else {
                commentBean.setCommentType(Constants.CommentType.COMMENT_TYPE_REPLY);
                commentBean.setChildUserName(commentListBean.getOtherUsername());
                commentBean.setParentUserName(commentListBean.getUsername());
            }
            commentBean.setCommentContent(commentListBean.getContent());
            commentBean.build(context);
            commentBeans.add(commentBean);

        }
//            if ((int) (Math.random() * 100) % 2 == 0) {
//                commentBean.setCommentType(Constants.CommentType.COMMENT_TYPE_SINGLE);
//                commentBean.setChildUserName(Constants.USER_NAME[(int) (Math.random() * 30)]);
//            } else {
//                commentBean.setCommentType(Constants.CommentType.COMMENT_TYPE_REPLY);
//                commentBean.setChildUserName(Constants.USER_NAME[(int) (Math.random() * 30)]);
//                commentBean.setParentUserName(Constants.USER_NAME[(int) (Math.random() * 30)]);
//            }


        return commentBeans;
    }

    /**
     * description:删除我的动态
     * author: Andy
     * date: 2019/9/18 0018 9:08
     */
    public void delMoving(int id, int position, MyStateAdapter myStateAdapter) {
        IdeaApi.getApiService()
                .delMoving(id)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity>(this) {
                    @Override
                    public void onSuccess(SuccessEntity response) {
                        ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), response.getMsg());
                        friendCircleBeans.remove(position);
                        myStateAdapter.notifyDataSetChanged();
                    }

                });
    }

    /**
     * description: 点赞
     * author: Andy
     * date: 2019/9/18  22:07
     */
    public void vote(long entityId, int type) {
        VoteBody voteBody = new VoteBody(entityId, type);
        IdeaApi.getApiService()
                .vote(voteBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity>(this) {
                    @Override
                    public void onSuccess(SuccessEntity response) {
                        ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), response.getMsg());
                    }

                });
    }

    /**
     * description: 取消点赞
     * author: Andy
     * date: 2019/9/18  22:07
     */
    public void unVote(long entityId) {
        IdeaApi.getApiService()
                .unVote(entityId)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity>(this) {
                    @Override
                    public void onSuccess(SuccessEntity response) {
                        ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), response.getMsg());
                    }

                });
    }

    /**
     * description: 评论
     * author: Andy
     * date: 2019/9/18  22:43
     */
    public void comment(CommentBody commentBody) {
        IdeaApi.getApiService()
                .comment(commentBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity>(this) {
                    @Override
                    public void onSuccess(SuccessEntity response) {
                        ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), response.getMsg());
                    }

                });
    }
}
