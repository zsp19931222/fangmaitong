package com.techangkeji.module_information.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.base.BaseTabAdapter;
import com.goldze.base.constant.RxBusMessageEventConstants;
import com.goldze.base.router.ARouterPath;
import com.google.android.material.tabs.TabLayout;
import com.techangkeji.model_information.BR;
import com.techangkeji.model_information.R;
import com.techangkeji.model_information.databinding.FragmentInformationMainBinding;
import com.techangkeji.module_information.ui.view_model.InformationMainViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;

@Route(path = ARouterPath.Information.InformationMainFragment)
public class InformationMainFragment extends BaseFragment<FragmentInformationMainBinding, InformationMainViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_information_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        RxSubscriptions.add(RxBus.getDefault().toObservable(RxBusMessageEventConstants.InformationRxMessage.class).subscribe(informationRxMessage -> {
            binding.tablayout.getTabAt(informationRxMessage.getPosition()).select();
        }));
        List<String> titles = new ArrayList<>();
        titles.add("资讯");
        titles.add("招聘");
        titles.add("公告");
        InformationFragment informationFragment = new InformationFragment();
        InviteFragment inviteFragment = new InviteFragment();
        NoticeFragment noticeFragment = new NoticeFragment();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(informationFragment);
        fragments.add(inviteFragment);
        fragments.add(noticeFragment);
        //设置tab文本的没有选中（第一个参数）和选中（第二个参数）的颜色
        binding.tablayout.setTabTextColors(ContextCompat.getColor(getContext(),R.color.color_dark_333333), ContextCompat.getColor(getContext(),R.color.color_dark_333333));
        //给ViewPager设置适配器
        binding.viewpager.setAdapter(new BaseTabAdapter(getFragmentManager(), fragments, titles));
        //将TabLayout和ViewPager关联起来。
        binding.tablayout.setupWithViewPager(binding.viewpager);
        //滑动监听
        binding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
