package com.techangkeji.module_main;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.goldze.base.router.ARouterPath;
import com.hyphenate.EMClientListener;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMMultiDeviceListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.techangkeji.hyphenate.chatuidemo.Constant;
import com.techangkeji.hyphenate.chatuidemo.HMSPushHelper;
import com.techangkeji.hyphenate.chatuidemo.ui.ChatActivity;
import com.techangkeji.hyphenate.chatuidemo.ui.GroupsActivity;
import com.techangkeji.module_main.databinding.ActivityMainBinding;
import com.techangkeji.module_main.view.TabGroupView;
import com.techangkeji.module_main.view.TabView;

import java.util.List;

import io.reactivex.observers.DefaultObserver;
import me.goldze.mvvmhabit.base.AppManager;
import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtil;
import me.goldze.mvvmhabit.utils.ZLog;

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
        binding.mainTgv.setOnItemClickListener(this);
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
                home_resource = (Fragment) ARouter.getInstance().build(ARouterPath.HouseResource.HRFragment).navigation();
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
            if (message == null) {
                message = (Fragment) ARouter.getInstance().build(ARouterPath.Message.MessageFragment).navigation();
                fragmentTransaction.add(R.id.main_vp, message);
            } else {
                fragmentTransaction.show(message);
            }
            fragmentTransaction.commit();
        } catch (Exception e) {
            ZLog.d(e.toString());
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
                mine = (Fragment) ARouter.getInstance().build(ARouterPath.Mine.MinePage).navigation();
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissions();

        registerBroadcastReceiver();

        EMClient.getInstance().contactManager().setContactListener(new MyContactListener());
        EMClient.getInstance().addClientListener(clientListener);
        EMClient.getInstance().addMultiDeviceListener(new MyMultiDeviceListener());

        // 获取华为 HMS 推送 token
        HMSPushHelper.getInstance().getHMSToken(this);
    }

    private LocalBroadcastManager broadcastManager;
    private BroadcastReceiver internalDebugReceiver;
    private BroadcastReceiver broadcastReceiver;

    private void registerBroadcastReceiver() {
        broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION_CONTACT_CHANAGED);
        intentFilter.addAction(Constant.ACTION_GROUP_CHANAGED);
        broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(Constant.ACTION_GROUP_CHANAGED)) {
                    if (EaseCommonUtils.getTopActivity(MainActivity.this).equals(GroupsActivity.class.getName())) {
                        GroupsActivity.instance.onResume();
                    }
                }
            }
        };
        broadcastManager.registerReceiver(broadcastReceiver, intentFilter);
    }

    public class MyMultiDeviceListener implements EMMultiDeviceListener {

        @Override
        public void onContactEvent(int event, String target, String ext) {

        }

        @Override
        public void onGroupEvent(int event, String target, final List<String> username) {
            switch (event) {
                case EMMultiDeviceListener.GROUP_LEAVE:
                    ChatActivity.activityInstance.finish();
                    break;
                default:
                    break;
            }
        }
    }

    public class MyContactListener implements EMContactListener {
        @Override
        public void onContactAdded(String username) {
        }

        @Override
        public void onContactDeleted(final String username) {
            runOnUiThread(() -> {
                if (ChatActivity.activityInstance != null && ChatActivity.activityInstance.toChatUsername != null &&
                        username.equals(ChatActivity.activityInstance.toChatUsername)) {
                    String st10 = getResources().getString(com.techangkeji.model_message.R.string.have_you_removed);
                    Toast.makeText(MainActivity.this, ChatActivity.activityInstance.getToChatUsername() + st10, Toast.LENGTH_LONG)
                            .show();
                    ChatActivity.activityInstance.finish();
                }
            });
        }

        @Override
        public void onContactInvited(String username, String reason) {
        }

        @Override
        public void onFriendRequestAccepted(String username) {
        }

        @Override
        public void onFriendRequestDeclined(String username) {
        }
    }

    /**
     * description: 环信所需权限
     * author: Andy
     * date: 2019/9/10 0010 16:41
     */
    @TargetApi(23)
    private void requestPermissions() {
        new RxPermissions(this)
                .request(Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new DefaultObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                        } else {
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    EMClientListener clientListener = success -> {
        Toast.makeText(MainActivity.this, "onUpgradeFrom 2.x to 3.x " + (success ? "success" : "fail"), Toast.LENGTH_LONG).show();
    };
}