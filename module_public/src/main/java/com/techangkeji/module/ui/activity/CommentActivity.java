package com.techangkeji.module.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.kcrason.highperformancefriendscircle.others.DataCenter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.techangkeji.module.BR;
import com.techangkeji.module.R;
import com.techangkeji.module.databinding.ActivityCommentBinding;
import com.techangkeji.module.ui.adapter.CommentStateAdapter;
import com.techangkeji.module.ui.view_model.CommentViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.http.net.body.TcReviewBody;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.view.MyVerticalDecoration;

@Route(path = ARouterPath.Public.CommentActivity)
public class CommentActivity extends BaseActivity<ActivityCommentBinding, CommentViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_comment;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("楼盘点评");
        viewModel.context.set(this);
         viewModel.commentStateAdapter = new CommentStateAdapter(R.layout.item_hrd_comment, viewModel.dataBeans);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.addItemDecoration(new MyVerticalDecoration(this, ContextCompat.getColor(this, R.color.color_f6), 1, 0, 0, true));
        binding.rv.setAdapter(viewModel.commentStateAdapter);
        viewModel.srl.set(binding.srl);
        viewModel.tcReviewsListBody.setEntityId(getIntent().getExtras().getInt("entityId"));
        viewModel.tcReviewsList();
        binding.srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                viewModel.pageNum++;
                viewModel.tcReviewsList();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.pageNum = 1;
                viewModel.tcReviewsList();
            }
        });
        RxSubscriptions.add(RxBus.getDefault().toObservable(TcReviewBody.class).subscribe(tcReviewBody -> {
            tcReviewBody.setEntityId(getIntent().getExtras().getInt("entityId"));
            tcReviewBody.setUserId(LocalDataHelper.getInstance().getUserInfo().getUserId());
            viewModel.tcReviews(tcReviewBody);
        }));
    }
}
