package com.techangkeji.module_information.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.goldze.base.utils.SimulationData;
import com.kcrason.highperformancefriendscircle.others.DataCenter;
import com.techangkeji.model_information.BR;
import com.techangkeji.model_information.R;
import com.techangkeji.model_information.databinding.ActivityInfromationDetailBinding;
import com.techangkeji.module_information.ui.adapter.InformationDetailAdapter;
import com.techangkeji.module_information.ui.view_model.InformationDetailViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.http.net.body.CommentBody;

@Route(path = ARouterPath.Information.InformationDetailActivity)
public class InformationDetailActivity extends BaseActivity<ActivityInfromationDetailBinding, InformationDetailViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_infromation_detail;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.emojiPanelView.set(binding.emojiPanelView);
        binding.title.setTitle(getIntent().getExtras().getString("title"));
        InformationDetailAdapter informationDetailAdapter = new InformationDetailAdapter(R.layout.item_i_information_detail, viewModel.commentListList);
        viewModel.adapter.set(informationDetailAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(informationDetailAdapter);
        viewModel.getNewsInfo(getIntent().getExtras().getString("id"));
        binding.emojiPanelView.initEmojiPanel(DataCenter.emojiDataSources);
        RxSubscriptions.add(RxBus.getDefault().toObservable(CommentBody.class).subscribe(commentBody -> {
            viewModel.comment(commentBody);
        }));
        viewModel.getCommentList();
    }
}
