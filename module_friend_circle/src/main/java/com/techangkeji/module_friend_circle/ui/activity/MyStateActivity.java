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
import com.techangkeji.module_friend_circle.databinding.ActivityMyStateBinding;
import com.techangkeji.module_friend_circle.ui.adapter.MyStateAdapter;
import com.techangkeji.module_friend_circle.ui.viewModel.MyStateViewModel;

import ch.ielse.view.imagewatcher.ImageWatcher;
import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.http.net.body.CommentBody;
import me.goldze.mvvmhabit.utils.ZLog;

@Route(path = ARouterPath.FriendCircle.MyStateActivity)
public class MyStateActivity extends BaseActivity<ActivityMyStateBinding, MyStateViewModel> implements ImageWatcher.OnPictureLongPressListener, ImageWatcher.Loader {
    private MyStateAdapter myStateAdapter;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_my_state;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.smartRefreshLayoutObservableField.set(binding.srl);
        viewModel.context.set(this);
        binding.title.setTitle("我的动态");
        binding.emojiPanelView.initEmojiPanel(DataCenter.emojiDataSources);

        myStateAdapter = new MyStateAdapter(R.layout.item_my_state, viewModel.friendCircleBeans, binding.imageWatcher, binding.emojiPanelView, viewModel);
        viewModel.myStateAdapter.set(myStateAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(myStateAdapter);
        viewModel.myMovingList();


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
                int page=viewModel.page.get();
                page++;
                viewModel.page.set(page);
                viewModel.myMovingList();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.page.set(1);
                viewModel.myMovingList();
            }
        });
    }

    @Override
    public void load(Context context, String url, ImageWatcher.LoadCallback lc) {
        Glide.with(context).asBitmap().load(url).into(new GlideSimpleTarget(lc));
    }

    @Override
    public void onPictureLongPress(ImageView v, String url, int pos) {

    }

    @Override
    public void onBackPressed() {
        if (!binding.imageWatcher.handleBackPressed()) {
            super.onBackPressed();
        }
    }
}
