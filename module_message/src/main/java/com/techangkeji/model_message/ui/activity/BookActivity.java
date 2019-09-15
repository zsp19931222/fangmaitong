package com.techangkeji.model_message.ui.activity;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.launcher.ARouter;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.hyphenate.chatuidemo.ui.ContactListFragment;
import com.techangkeji.model_message.BR;
import com.techangkeji.model_message.R;
import com.techangkeji.model_message.databinding.ActivityBookBinding;
import com.techangkeji.model_message.databinding.ActivityMapBinding;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class BookActivity extends BaseActivity<ActivityBookBinding, BaseViewModel>{
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private ContactListFragment contactListFragment;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_book;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        fragmentManager = getSupportFragmentManager();
        initHome();
    }

    private void initHome() {
        try {
            fragmentTransaction = fragmentManager.beginTransaction();
            if (contactListFragment == null) {
                contactListFragment =new ContactListFragment();
                fragmentTransaction.add(R.id.fl, contactListFragment);
            } else {
                fragmentTransaction.show(contactListFragment);
            }
            fragmentTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}