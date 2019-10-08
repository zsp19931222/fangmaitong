package com.techangkeji.module.ui.activity;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.kcrason.highperformancefriendscircle.others.DataCenter;
import com.techangkeji.module.BR;
import com.techangkeji.module.R;
import com.techangkeji.module.databinding.ActivityCommentBinding;
import com.techangkeji.module.ui.adapter.CommentStateAdapter;
import com.techangkeji.module.ui.view_model.CommentViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
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
        List<String> strings = new ArrayList<>();
        CommentStateAdapter houseSizeAdapter = new CommentStateAdapter(R.layout.item_hrd_comment, strings);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.addItemDecoration(new MyVerticalDecoration(this, ContextCompat.getColor(this, R.color.color_f6), 1, 0, 0, true));
        binding.rv.setAdapter(houseSizeAdapter);
    }
}
