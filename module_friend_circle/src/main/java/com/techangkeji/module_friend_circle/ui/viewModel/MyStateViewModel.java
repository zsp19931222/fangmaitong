package com.techangkeji.module_friend_circle.ui.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.alibaba.android.arouter.launcher.ARouter;
import com.goldze.base.router.ARouterPath;
import com.kcrason.highperformancefriendscircle.Constants;
import com.kcrason.highperformancefriendscircle.beans.CommentBean;
import com.kcrason.highperformancefriendscircle.beans.FriendCircleBean;
import com.kcrason.highperformancefriendscircle.beans.OtherInfoBean;
import com.kcrason.highperformancefriendscircle.beans.PraiseBean;
import com.kcrason.highperformancefriendscircle.beans.UserBean;
import com.kcrason.highperformancefriendscircle.utils.SpanUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.techangkeji.module_friend_circle.ui.adapter.MyStateAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.CommentBody;
import me.goldze.mvvmhabit.http.net.body.MyMovingListBody;
import me.goldze.mvvmhabit.http.net.body.VoteBody;
import me.goldze.mvvmhabit.http.net.entity.BaseEntity;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.friend_circle.MyStateEntity;
import me.goldze.mvvmhabit.http.net.entity.friend_circle.VoteEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
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
    public ObservableField<Context> context = new ObservableField<>();
    public ObservableField<MyStateAdapter> myStateAdapter = new ObservableField<>();
    public ObservableField<SmartRefreshLayout> smartRefreshLayoutObservableField = new ObservableField<>();
    private int myID= (int) LocalDataHelper.getInstance().getUserInfo().getUserId();

    public MyStateViewModel(@NonNull Application application) {
        super(application);
    }
    /**
     * description: 跳转到发布动态
     * author: Andy
     * date: 2019/9/19 0019 15:55
     */
    public BindingCommand releaseCircle=new BindingCommand(() -> ARouter.getInstance().build(ARouterPath.Message.ReleaseInformationActivity).navigation());
    /**
     * description:获取数据
     * author: Andy
     * date: 2019/9/19 0019 15:54
     */
    public void friendMovingList() {
        if (page.get()==1){
            friendCircleBeans.clear();
        }
        IdeaApi.getApiService()
                .friendMovingList(page.get(),20)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<MyStateEntity>(smartRefreshLayoutObservableField.get()) {
                    @Override
                    public void onSuccess(MyStateEntity response) {
                        for (MyStateEntity.DataBean datum : response.getData()) {
                            FriendCircleBean friendCircleBean = new FriendCircleBean();
                            friendCircleBean.setId(datum.getId());//设置单条动态id
                            //设置评论
                            friendCircleBean.setCommentBeans(makeCommentBeans(datum.getCommentList(), context.get()));

                            if (!IsNullUtil.getInstance().isEmpty(datum.getImgsUrl())) {
                                String[] strs = datum.getImgsUrl().split(",");
                                List<String> images = new ArrayList<>(Arrays.asList(strs));
                                friendCircleBean.setImageUrls(images);//设置图片
                            }
                            //是否点赞标识
                            boolean praise = false;
                            //设置点赞
                            List<PraiseBean> praiseBeans = new ArrayList<>();
                            for (MyStateEntity.DataBean.VoteUserBean voteUserBean : datum.getVoteUser()) {
                                PraiseBean praiseBean = new PraiseBean();
                                praiseBean.setPraiseUserName(voteUserBean.getName());
                                praiseBean.setPraiseUserId(voteUserBean.getId());
                                praiseBeans.add(praiseBean);
                                if (myID == voteUserBean.getId()) {
                                    praise = true;
                                }
                            }
                            ZLog.d(LocalDataHelper.getInstance().getUserInfo().getUserId());
                            friendCircleBean.setPraiseSpan(SpanUtils.makePraiseSpan(context.get(), praiseBeans));
                            friendCircleBean.setPraiseBeans(praiseBeans);

                            //设置点赞状态
                            friendCircleBean.setVote(praise);

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
                        myStateAdapter.get().notifyDataSetChanged();
                    }
                });
    }
    /**
     * description: 我的动态列表
     * author: Andy
     * date: 2019/9/17  20:53
     */
    public void myMovingList() {
        if (page.get()==1){
            friendCircleBeans.clear();
        }
        MyMovingListBody myMovingListBody = new MyMovingListBody(20, page.get());
        IdeaApi.getApiService()
                .myMovingList(myMovingListBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<MyStateEntity>(smartRefreshLayoutObservableField.get()) {
                    @Override
                    public void onSuccess(MyStateEntity response) {
                        for (MyStateEntity.DataBean datum : response.getData()) {
                            FriendCircleBean friendCircleBean = new FriendCircleBean();
                            friendCircleBean.setId(datum.getId());//设置单条动态id
                            //设置评论
                            friendCircleBean.setCommentBeans(makeCommentBeans(datum.getCommentList(), context.get()));

                            if (!IsNullUtil.getInstance().isEmpty(datum.getImgsUrl())) {
                                String[] strs = datum.getImgsUrl().split(",");
                                List<String> images = new ArrayList<>(Arrays.asList(strs));
                                friendCircleBean.setImageUrls(images);//设置图片
                            }
                            //是否点赞标识
                            boolean praise = false;
                            //设置点赞
                            List<PraiseBean> praiseBeans = new ArrayList<>();
                            for (MyStateEntity.DataBean.VoteUserBean voteUserBean : datum.getVoteUser()) {
                                PraiseBean praiseBean = new PraiseBean();
                                praiseBean.setPraiseUserName(voteUserBean.getName());
                                praiseBean.setPraiseUserId(voteUserBean.getId());
                                praiseBeans.add(praiseBean);
                                if (myID == voteUserBean.getId()) {
                                    praise = true;
                                }
                            }
                            ZLog.d(LocalDataHelper.getInstance().getUserInfo().getUserId());
                            friendCircleBean.setPraiseSpan(SpanUtils.makePraiseSpan(context.get(), praiseBeans));
                            friendCircleBean.setPraiseBeans(praiseBeans);

                            //设置点赞状态
                            friendCircleBean.setVote(praise);

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
                        myStateAdapter.get().notifyDataSetChanged();
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
                commentBean.setChildUserId(commentListBean.getUserId());
                commentBean.setEntityId(commentListBean.getEntityId());
                commentBean.setEntityType(commentListBean.getEntityType());
            } else {
                commentBean.setCommentType(Constants.CommentType.COMMENT_TYPE_REPLY);
                commentBean.setChildUserName(commentListBean.getOtherUsername());
                commentBean.setChildUserId(commentListBean.getOtherUserId());
                commentBean.setParentUserName(commentListBean.getUsername());
                commentBean.setParentUserId(commentListBean.getUserId());
                commentBean.setEntityId(commentListBean.getEntityId());
                commentBean.setEntityType(commentListBean.getEntityType());
            }
            commentBean.setCommentContent(commentListBean.getContent());
            commentBean.build(context);
            commentBeans.add(commentBean);

        }
        return commentBeans;
    }

    /**
     * description:删除我的动态
     * author: Andy
     * date: 2019/9/18 0018 9:08
     */
    public void delMoving(int id, int position) {
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
                        myStateAdapter.get().notifyDataSetChanged();
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
                        int position = 0;
                        for (int i = 0; i < friendCircleBeans.size(); i++) {
                            if (friendCircleBeans.get(i).getId() == entityId) {
                                position = i;
                            }
                        }
                        //添加点赞
                        PraiseBean praiseBean = new PraiseBean();
                        praiseBean.setPraiseUserName(LocalDataHelper.getInstance().getUserInfo().getName());
                        praiseBean.setPraiseUserId(myID);
                        friendCircleBeans.get(position).getPraiseBeans().add(praiseBean);
                        friendCircleBeans.get(position).setPraiseSpan(SpanUtils.makePraiseSpan(context.get(), friendCircleBeans.get(position).getPraiseBeans()));
                        friendCircleBeans.get(position).setPraiseBeans(friendCircleBeans.get(position).getPraiseBeans());
                        friendCircleBeans.get(position).setVote(true);
                        myStateAdapter.get().notifyDataSetChanged();
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
                        int position = 0;
                        for (int i = 0; i < friendCircleBeans.size(); i++) {
                            if (friendCircleBeans.get(i).getId() == entityId) {
                                position = i;
                            }
                        }
                        //删除点赞
                        for (PraiseBean bean : friendCircleBeans.get(position).getPraiseBeans()) {
                            if (bean.getPraiseUserId()==myID){
                                friendCircleBeans.get(position).getPraiseBeans().remove(bean);
                            }
                        }
                        friendCircleBeans.get(position).setPraiseSpan(SpanUtils.makePraiseSpan(context.get(), friendCircleBeans.get(position).getPraiseBeans()));
                        friendCircleBeans.get(position).setPraiseBeans(friendCircleBeans.get(position).getPraiseBeans());
                        friendCircleBeans.get(position).setVote(false);
                        myStateAdapter.get().notifyDataSetChanged();
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
                .subscribe(new DefaultObserver<SuccessEntity<me.goldze.mvvmhabit.http.net.entity.friend_circle.CommentBean>>(this) {
                    @Override
                    public void onSuccess(SuccessEntity<me.goldze.mvvmhabit.http.net.entity.friend_circle.CommentBean> response) {
                        int position = 0;
                        for (int i = 0; i < friendCircleBeans.size(); i++) {
                            if (friendCircleBeans.get(i).getId() == response.getContent().getEntityId()) {
                                position = i;
                            }
                        }
                        CommentBean commentBean = new CommentBean();
                        if (response.getContent().getOtherUserId() <= 0) {
                            commentBean.setCommentType(Constants.CommentType.COMMENT_TYPE_SINGLE);
                            commentBean.setChildUserName(response.getContent().getUsername());
                            commentBean.setChildUserId(response.getContent().getUserId());
                            commentBean.setEntityId(response.getContent().getEntityId());
                            commentBean.setEntityType(response.getContent().getEntityType());
                        } else {
                            commentBean.setCommentType(Constants.CommentType.COMMENT_TYPE_REPLY);
                            commentBean.setChildUserName(response.getContent().getOtherUsername());
                            commentBean.setChildUserId(response.getContent().getOtherUserId());
                            commentBean.setParentUserName(response.getContent().getUsername());
                            commentBean.setParentUserId(response.getContent().getUserId());
                            commentBean.setEntityId(response.getContent().getEntityId());
                            commentBean.setEntityType(response.getContent().getEntityType());
                        }
                        commentBean.setCommentContent(response.getContent().getContent());
                        commentBean.build(context.get());
                        friendCircleBeans.get(position).getCommentBeans().add(commentBean);
                        friendCircleBeans.get(position).setCommentBeans(friendCircleBeans.get(position).getCommentBeans());
                        myStateAdapter.get().notifyDataSetChanged();
                    }

                });
    }
}
