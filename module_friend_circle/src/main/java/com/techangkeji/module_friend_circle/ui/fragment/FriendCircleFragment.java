package com.techangkeji.module_friend_circle.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.techangkeji.module_friend_circle.databinding.FragmentFriendStateBinding;
import com.techangkeji.module_friend_circle.ui.adapter.MyStateAdapter;
import com.techangkeji.module_friend_circle.ui.viewModel.MyStateViewModel;

import ch.ielse.view.imagewatcher.ImageWatcher;
import me.goldze.mvvmhabit.base.BaseLazyFragment;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.http.net.body.CommentBody;
import me.goldze.mvvmhabit.utils.ZLog;

@Route(path = ARouterPath.FriendCircle.FriendCircleFragment)
public class FriendCircleFragment extends BaseLazyFragment<FragmentFriendStateBinding, MyStateViewModel> implements ImageWatcher.OnPictureLongPressListener, ImageWatcher.Loader {
    @Override
    public void fetchData() {
        viewModel.smartRefreshLayoutObservableField.set(binding.srl);
        viewModel.context.set(getContext());
        binding.emojiPanelView.initEmojiPanel(DataCenter.emojiDataSources);
        MyStateAdapter myStateAdapter = new MyStateAdapter(R.layout.item_my_state, viewModel.friendCircleBeans, binding.imageWatcher, binding.emojiPanelView,viewModel);
        viewModel.myStateAdapter.set(myStateAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(myStateAdapter);
        RxSubscriptions.add(RxBus.getDefault().toObservable(String.class).subscribe(s -> {
            if ("onBackPressed".equals(s)) {
                binding.emojiPanelView.dismiss();
            }
        }));
        viewModel.friendMovingList();

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
                viewModel.friendMovingList();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.page.set(1);
                viewModel.friendMovingList();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        binding.emojiPanelView.dismiss();
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_friend_state;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void load(Context context, String url, ImageWatcher.LoadCallback lc) {
        Glide.with(context).asBitmap().load(url).into(new GlideSimpleTarget(lc));
    }

    @Override
    public void onPictureLongPress(ImageView v, String url, int pos) {

    }

    @Override
    public void initData() {
    viewModel.context.set(getActivity());
    viewModel.smartRefreshLayoutObservableField.set(binding.srl);
    }
}
