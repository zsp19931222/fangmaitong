package com.techangkeji.module_friend_circle.ui.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.kcrason.highperformancefriendscircle.Constants;
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
import me.goldze.mvvmhabit.http.net.body.MyMovingListBody;
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
                            friendCircleBean.setCommentBeans(null);//设置评论
                            ZLog.d(IsNullUtil.getInstance().isEmpty(datum.getImgsUrl()));
                            if (!IsNullUtil.getInstance().isEmpty(datum.getImgsUrl())) {
                                String[] strs = datum.getImgsUrl().split(",");
                                List<String> images = new ArrayList<>(Arrays.asList(strs));
                                friendCircleBean.setImageUrls(images);//设置图片
                            }

                            List<PraiseBean> praiseBeans = new ArrayList<>();//设置点赞
                            friendCircleBean.setPraiseSpan(SpanUtils.makePraiseSpan(context, praiseBeans));
                            friendCircleBean.setPraiseBeans(praiseBeans);
                            friendCircleBean.setContent(datum.getContent());//设置文案

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

    /**
     * description:删除我的动态
     * author: Andy
     * date: 2019/9/18 0018 9:08
     */
    public void delMoving(int id,int position,MyStateAdapter myStateAdapter) {
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
}
