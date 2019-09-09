package com.techangkeji.module_main;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.module_main.databinding.ActivityMainBinding;
import com.techangkeji.module_main.view.TabGroupView;
import com.techangkeji.module_main.view.TabView;

import me.goldze.mvvmhabit.base.AppManager;
import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtil;

@Route(path = ARouterPath.Main.PAGER_MAIN)
public class MainActivity extends BaseActivity<ActivityMainBinding, BaseViewModel> implements TabGroupView.OnItemClickListener {
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private Fragment home;
    private Fragment home_resource;
    private Fragment information;
    private Fragment message;
    private Fragment mine;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return com.techangkeji.module_main.BR.viewModel;
    }

    @Override
    public void initData() {
        fragmentManager = getSupportFragmentManager();
        initHome();
    }

    @Override
    public void onClick(TabView tabLayout, int position) {
        switch (position) {
            case 0:
                initHome();
                break;
            case 1:
                initHomeResource();
                break;
            case 2:
                initInformation();
                break;
            case 3:
                initMessage();
                break;
            case 4:
                initMine();
                break;
        }
    }

    /**
     * description:加载首页
     * author: Andy
     * date: 2019/7/29 0029 10:19
     */
    private void initHome() {
        try {
            fragmentTransaction = fragmentManager.beginTransaction();
            hideFragment(fragmentTransaction);
            if (home == null) {
                home = (Fragment) ARouter.getInstance().build(ARouterPath.Home.HomeFragment).navigation();
                fragmentTransaction.add(R.id.main_vp, home);
            } else {
                fragmentTransaction.show(home);
            }
            fragmentTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * description:加载房源
     * author: Andy
     * date: 2019/7/29 0029 10:19
     */
    private void initHomeResource() {
        try {
            fragmentTransaction = fragmentManager.beginTransaction();
            hideFragment(fragmentTransaction);
            if (home_resource == null) {
                home_resource = (Fragment) ARouter.getInstance().build(ARouterPath.Home.HomeFragment).navigation();
                fragmentTransaction.add(R.id.main_vp, home_resource);
            } else {
                fragmentTransaction.show(home_resource);
            }
            fragmentTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * description:加载资讯
     * author: Andy
     * date: 2019/7/29 0029 10:19
     */
    private void initInformation() {
        try {
            fragmentTransaction = fragmentManager.beginTransaction();
            hideFragment(fragmentTransaction);
            if (information == null) {
                information = (Fragment) ARouter.getInstance().build(ARouterPath.Home.HomeFragment).navigation();
                fragmentTransaction.add(R.id.main_vp, information);
            } else {
                fragmentTransaction.show(information);
            }
            fragmentTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * description:加载社交
     * author: Andy
     * date: 2019/7/29 0029 10:19
     */
    private void initMessage() {
        try {
            fragmentTransaction = fragmentManager.beginTransaction();
            hideFragment(fragmentTransaction);
            if (home == null) {
                message = (Fragment) ARouter.getInstance().build(ARouterPath.Home.HomeFragment).navigation();
                fragmentTransaction.add(R.id.main_vp, message);
            } else {
                fragmentTransaction.show(message);
            }
            fragmentTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * description:加载我的
     * author: Andy
     * date: 2019/7/29 0029 10:19
     */
    private void initMine() {
        try {
            fragmentTransaction = fragmentManager.beginTransaction();
            hideFragment(fragmentTransaction);
            if (mine == null) {
                mine = (Fragment) ARouter.getInstance().build(ARouterPath.Home.HomeFragment).navigation();
                fragmentTransaction.add(R.id.main_vp, mine);
            } else {
                fragmentTransaction.show(mine);
            }
            fragmentTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * description: 去除（隐藏）所有的Fragment
     * author: Andy
     * date: 2019/7/29 0029 10:22
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (home != null) {
            //隐藏方法
            transaction.hide(home);
        }
        if (home_resource != null) {
            transaction.hide(home_resource);
        }
        if (information != null) {
            transaction.hide(information);
        }
        if (message != null) {
            transaction.hide(message);
        }
        if (mine != null) {
            transaction.hide(mine);
        }
    }

    /**
     * description:用户双击退出
     * author: Andy
     * date: 2019/8/19 0019 11:43
     */
    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                ToastUtil.normalToast(this, "再按一次退出程序");
                firstTime = secondTime;
                return true;
            } else {
                AppManager.getAppManager().AppExit();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
