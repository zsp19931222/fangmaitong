package com.techangkeji.model_message.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.goldze.base.base.BaseTabAdapter;
import com.goldze.base.router.ARouterPath;
import com.google.android.material.tabs.TabLayout;
import com.techangkeji.hyphenate.chatuidemo.ui.ContactListFragment;
import com.techangkeji.hyphenate.chatuidemo.ui.ConversationListFragment;
import com.techangkeji.hyphenate.chatuidemo.ui.SettingsFragment;
import com.techangkeji.model_message.BR;
import com.techangkeji.model_message.R;
import com.techangkeji.model_message.databinding.FragmentMessageBinding;
import com.techangkeji.model_message.ui.view_model.MessageViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseLazyFragment;

/**
 * description:
 * author:created by Andy on 2019/9/10 0010 13:57
 * email:zsp872126510@gmail.com
 */
@Route(path = ARouterPath.Message.MessageFragment)
public class MessageFragment extends BaseLazyFragment<FragmentMessageBinding, MessageViewModel> {
    private ConversationListFragment conversationListFragment;
    private ContactListFragment contactListFragment;
    private SettingsFragment settingFragment;


    @Override
    public void fetchData() {
        init();
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_message;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    private void init() {
        List<String> titles = new ArrayList<>();
        titles.add("聊天");
        titles.add("好友动态");
        titles.add("广场信息");
        conversationListFragment = new ConversationListFragment();
        contactListFragment = new ContactListFragment();
        settingFragment = new SettingsFragment();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(conversationListFragment);
        fragments.add((Fragment) ARouter.getInstance().build(ARouterPath.FriendCircle.FriendCircleFragment).navigation());
        fragments.add((Fragment) ARouter.getInstance().build(ARouterPath.FriendCircle.FriendCircleFragment).navigation());
        //给ViewPager设置适配器
        binding.vpFm.setAdapter(new BaseTabAdapter(getFragmentManager(), fragments, titles));
        //将TabLayout和ViewPager关联起来。
        binding.tlFm.setupWithViewPager(binding.vpFm);
        //滑动监听
        binding.tlFm.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
