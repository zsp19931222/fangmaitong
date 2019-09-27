package com.techangkeji.module_friend_circle.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.goldze.base.router.ARouterPath;
import com.kcrason.highperformancefriendscircle.others.DataCenter;
import com.kcrason.highperformancefriendscircle.others.GlideSimpleTarget;
import com.kcrason.highperformancefriendscircle.span.TextClickSpan;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.techangkeji.module_friend_circle.BR;
import com.techangkeji.module_friend_circle.R;
import com.techangkeji.module_friend_circle.databinding.ActivityUserDetailBinding;
import com.techangkeji.module_friend_circle.ui.adapter.UserDetailAdapter;
import com.techangkeji.module_friend_circle.ui.viewModel.UserDetailViewModel;

import ch.ielse.view.imagewatcher.ImageWatcher;
import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.http.net.body.CommentBody;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:名片详情
 * author:created by Andy on 2019/9/16 0016 16:36
 * email:zsp872126510@gmail.com
 */
@Route(path = ARouterPath.FriendCircle.CardActivity)
public class UserDetailActivity extends BaseActivity<ActivityUserDetailBinding, UserDetailViewModel> {
    private UserDetailAdapter myStateAdapter;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_user_detail;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.friendId = getIntent().getExtras().getInt("id");
        viewModel.smartRefreshLayoutObservableField.set(binding.srl);
        binding.title.setTitle("名片详情");
        binding.emojiPanelView.initEmojiPanel(DataCenter.emojiDataSources);
        viewModel.context.set(this);
        myStateAdapter = new UserDetailAdapter(R.layout.item_my_state, viewModel.friendCircleBeans, binding.emojiPanelView, viewModel);
        viewModel.myStateAdapter.set(myStateAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(myStateAdapter);
        RxSubscriptions.add(RxBus.getDefault().toObservable(CommentBody.class).subscribe(commentBody -> {
            ZLog.d(commentBody);
            viewModel.comment(commentBody);
        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(TextClickSpan.TextClickSpanBean.class).subscribe(textClickSpanBean -> {
            CommentBody commentBody = new CommentBody("", textClickSpanBean.getEntityType(), textClickSpanBean.getEntityId(), (int) textClickSpanBean.getmUserId(), textClickSpanBean.getmUserName(), false);
            binding.emojiPanelView.showEmojiPanel(commentBody);
        }));
        binding.srl.setEnableRefresh(true);
        binding.srl.setEnableAutoLoadMore(true);
        binding.srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                viewModel.pageNum++;
                viewModel.getState();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.pageNum = 1;
                viewModel.getState();
            }
        });
        viewModel.getState();
        viewModel.getUserDetailData();
    }
}
