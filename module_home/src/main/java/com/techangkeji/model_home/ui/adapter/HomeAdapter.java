package com.techangkeji.model_home.ui.adapter;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.techangkeji.model_home.R;
import com.techangkeji.model_home.ui.bean.HomeAdapterBean;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.view.MyVerticalDecoration;

/**
 * description:
 * author:created by Andy on 2019/9/9 0009 17:01
 * email:zsp872126510@gmail.com
 */
public class HomeAdapter extends BaseQuickAdapter<HomeAdapterBean, BaseViewHolder> {
    public HomeAdapter(@Nullable List<HomeAdapterBean> data) {
        super(data);
        setMultiTypeDelegate(new MultiTypeDelegate<HomeAdapterBean>() {
            @Override
            protected int getItemType(HomeAdapterBean homeAdapterBean) {
                //根据你的实体类来判断布局类型
                return homeAdapterBean.getType();
            }
        });
        getMultiTypeDelegate()
                .registerItemType(HomeAdapterBean.HomeResourceRecommend, R.layout.item_home)
                .registerItemType(HomeAdapterBean.FriendRecommend, R.layout.item_home)
                .registerItemType(HomeAdapterBean.Recruitment, R.layout.item_home)
                .registerItemType(HomeAdapterBean.NewInformation, R.layout.item_home)
        ;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeAdapterBean item) {
        switch (helper.getItemViewType()) {
            case HomeAdapterBean.HomeResourceRecommend:
                helper.setText(R.id.tv_lh_header, item.getTitle());
                initHomeResourceRecommend(helper);
                break;
            case HomeAdapterBean.FriendRecommend:
                helper.setText(R.id.tv_lh_header, item.getTitle());
                initFriendRecommend(helper);
                break;
            case HomeAdapterBean.Recruitment:
                helper.setText(R.id.tv_lh_header, item.getTitle());
                initRecruitment(helper);
                break;
            case HomeAdapterBean.NewInformation:
                helper.setText(R.id.tv_lh_header, item.getTitle());
                initNewInformation(helper);
                break;
        }
    }


    private void initHomeResourceRecommend(BaseViewHolder helper) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            strings.add("" + i);
        }
        RecyclerView recyclerView = helper.getView(R.id.rv_lh);
        HomeResourceRecommendAdapter HomeResourceRecommendAdapter = new HomeResourceRecommendAdapter(R.layout.item_home_resource, strings);
        recyclerView.setLayoutManager(new LinearLayoutManager(helper.itemView.getContext()));
        recyclerView.addItemDecoration(new MyVerticalDecoration(helper.itemView.getContext(), ContextCompat.getColor(helper.itemView.getContext(), R.color.color_f6), 1, 0, 0, true));
        recyclerView.setAdapter(HomeResourceRecommendAdapter);
    }

    private void initFriendRecommend(BaseViewHolder helper) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            strings.add("" + i);
        }
        RecyclerView recyclerView = helper.getView(R.id.rv_lh);
        FriendRecommendAdapter friendRecommendAdapter = new FriendRecommendAdapter(R.layout.item_friend_recommend, strings);
        recyclerView.setLayoutManager(new GridLayoutManager(helper.itemView.getContext(), 3));
        recyclerView.setAdapter(friendRecommendAdapter);
    }

    private void initRecruitment(BaseViewHolder helper) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 43; i++) {
            strings.add("" + i);
        }
        RecyclerView recyclerView = helper.getView(R.id.rv_lh);
        RecruitmentAdapter recruitmentAdapter = new RecruitmentAdapter(R.layout.item_recruitment, strings);
        recyclerView.setLayoutManager(new LinearLayoutManager(helper.itemView.getContext()));
        recyclerView.addItemDecoration(new MyVerticalDecoration(helper.itemView.getContext(), ContextCompat.getColor(helper.itemView.getContext(), R.color.color_f6), 1, 0, 0, true));
        recyclerView.setAdapter(recruitmentAdapter);
    }

    private void initNewInformation(BaseViewHolder helper) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            strings.add("" + i);
        }
        RecyclerView recyclerView = helper.getView(R.id.rv_lh);
        NewInformationAdapter informationAdapter = new NewInformationAdapter(R.layout.item_information, strings);
        recyclerView.setLayoutManager(new LinearLayoutManager(helper.itemView.getContext()));
        recyclerView.addItemDecoration(new MyVerticalDecoration(helper.itemView.getContext(), ContextCompat.getColor(helper.itemView.getContext(), R.color.color_f6), 1, 0, 0, true));
        recyclerView.setAdapter(informationAdapter);
    }
}