package com.techangkeji.module.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.goldze.base.view.TitleVIew;
import com.kcrason.highperformancefriendscircle.others.DataCenter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.techangkeji.module.BR;
import com.techangkeji.module.R;
import com.techangkeji.module.databinding.ActivityHouseStateBinding;
import com.techangkeji.module.ui.adapter.HouseStateAdapter;
import com.techangkeji.module.ui.view_model.HouseStateViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.http.net.body.CommentBody;
import me.goldze.mvvmhabit.view.MyVerticalDecoration;

public class HouseStateActivity extends BaseActivity<ActivityHouseStateBinding, HouseStateViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_house_state;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.id = (int) getIntent().getExtras().get("id");
        viewModel.emojiPanelView.set(binding.emojiPanelView);
        binding.emojiPanelView.initEmojiPanel(DataCenter.emojiDataSources);
        TitleVIew titleVIew = (TitleVIew) binding.title;
        titleVIew.setTitle(getIntent().getExtras().getString("listingName"));
        HouseStateAdapter houseSizeAdapter = new HouseStateAdapter(R.layout.item_house_state, viewModel.dataBeans);
        viewModel.adapter.set(houseSizeAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.addItemDecoration(new MyVerticalDecoration(this, ContextCompat.getColor(this, R.color.color_f6), 1, 0, 0, true));
        binding.rv.setAdapter(houseSizeAdapter);
        viewModel.getCommentList();
        RxSubscriptions.add(RxBus.getDefault().toObservable(CommentBody.class).subscribe(commentBody -> {
            viewModel.comment(commentBody);
        }));
        viewModel.srl.set(binding.srl);
        binding.srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                viewModel.pageNum++;
                viewModel.getCommentList();

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.pageNum=1;
                viewModel.getCommentList();
            }
        });
    }
}
