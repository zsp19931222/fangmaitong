package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityInformationCommentBinding;
import com.techangkeji.model_mine.ui.adapter.InformationCommentAdapter;
import com.techangkeji.model_mine.ui.bean.InformationCommentBean;
import com.techangkeji.model_mine.ui.viewModel.InformationCommentViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;

public class InformationCommentActivity extends BaseActivity<ActivityInformationCommentBinding, InformationCommentViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_information_comment;
    }

    @Override
    public int initVariableId() {
        return com.techangkeji.model_mine.BR.viewModel;
    }

    @Override
    public void initData() {
        List<InformationCommentBean> beans = new ArrayList<>();
        for (int i = 0; i <3 ; i++) {
            beans.add(new InformationCommentBean(true, ""+i));
            for (int j = 0; j <5 ; j++) {
                beans.add(new InformationCommentBean(""));

            }
        }
        InformationCommentAdapter informationCommentAdapter = new InformationCommentAdapter(R.layout.item_information_comment, R.layout.item_information_comment_head, beans);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(informationCommentAdapter);
    }
}
