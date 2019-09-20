package com.techangkeji.model_login.ui.util;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.goldze.base.constant.TipsConstants;
import com.goldze.base.router.ARouterPath;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.exceptions.HyphenateException;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.techangkeji.hyphenate.chatuidemo.DemoHelper;
import com.techangkeji.hyphenate.chatuidemo.db.DemoDBManager;
import com.techangkeji.model_message.MessageModuleInit;

import org.litepal.LitePal;

import io.reactivex.observers.DefaultObserver;
import me.goldze.mvvmhabit.base.AppManager;
import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.login.RegisterEntity;
import me.goldze.mvvmhabit.litepal.UserInfoLitePal;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.ToastUtil;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:登录工具类
 * author:created by Andy on 2019/9/17 0017 12:21
 * email:zsp872126510@gmail.com
 */
public class LoginUtil {
    private static final LoginUtil ourInstance = new LoginUtil();

    public static LoginUtil getInstance() {
        return ourInstance;
    }

    private LoginUtil() {
    }

    /**
     * description: 注册环信
     * author: Andy
     * date: 2019/9/10 0010 15:56
     */
    private void registerHX(String username, String pwd) {
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd)) {
            new Thread(() -> {
                try {
                    // call method in SDK
                    EMClient.getInstance().createAccount(username, pwd);
                    // save current user
                    DemoHelper.getInstance().setCurrentUserName(username);
                    ZLog.d("注册成功");
                    loginHX(username, pwd);
                } catch (final HyphenateException e) {
                    int errorCode = e.getErrorCode();
                    ZLog.d(errorCode);
                    if (errorCode == EMError.USER_ALREADY_EXIST) {//已经注册
                        logoutHX(username, pwd);
                    }
                }
            }).start();

        }
    }

    /**
     * description: 登录环信
     * author: Andy
     * date: 2019/9/10 0010 16:09
     */
    private void loginHX(String currentUsername, String currentPassword) {
        if (!EaseCommonUtils.isNetWorkConnected(BaseApplication.getInstance().getBaseContext())) {
            Toast.makeText(BaseApplication.getInstance().getBaseContext(), com.techangkeji.model_message.R.string.network_isnot_available, Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(currentUsername)) {
            Toast.makeText(BaseApplication.getInstance().getBaseContext(), com.techangkeji.model_message.R.string.User_name_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(currentPassword)) {
            Toast.makeText(BaseApplication.getInstance().getBaseContext(), com.techangkeji.model_message.R.string.Password_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        DemoDBManager.getInstance().closeDB();
        DemoHelper.getInstance().setCurrentUserName(currentUsername);
        ZLog.d("EMClient.getInstance().login");
        EMClient.getInstance().login(currentUsername, currentPassword, new EMCallBack() {

            @Override
            public void onSuccess() {
                ZLog.d("login: onSuccess");
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();

                boolean updatenick = EMClient.getInstance().pushManager().updatePushNickname(
                        MessageModuleInit.currentUserNick.trim());
                if (!updatenick) {
                    ZLog.d("update current user nick fail");
                }
                DemoHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();
                //存储登录时间
                SPUtils.getInstance().put("loginTime", System.currentTimeMillis());
                AppManager.getAppManager().finishAllActivity();
                ARouter.getInstance().build(ARouterPath.Main.PAGER_MAIN).navigation();

            }

            @Override
            public void onProgress(int progress, String status) {
                ZLog.d("login: onProgress");
            }

            @Override
            public void onError(final int code, final String message) {
                ZLog.d("login: onError: " + code);
                logoutHX(currentUsername, currentPassword);
            }
        });
    }

    /**
     * description:注销环信
     * author: Andy
     * date: 2019/9/10 0010 17:23
     */
    private void logoutHX(String currentUsername, String currentPassword) {
        DemoHelper.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                loginHX(currentUsername, currentPassword);
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                ZLog.d(message);
            }
        });
    }

    /**
     * description: 保存用户信息到本地
     * author: Andy
     * date: 2019/9/17 0017 12:23
     */
    private void saveUserInfo(SuccessEntity<RegisterEntity> response) {

        LocalDataHelper.getInstance().deleteData();
        UserInfoLitePal dataBean = new UserInfoLitePal();
        dataBean.setAge(response.getContent().getAge());
        dataBean.setBrokerAuthenticate(response.getContent().getBrokerAuthenticate());
        dataBean.setBuildNum(response.getContent().getBuildNum());
        dataBean.setCollectNum(response.getContent().getCollectNum());
        dataBean.setCreateTime(response.getContent().getCreateTime());
        dataBean.setCumulativeLoginTime(response.getContent().getCumulativeLoginTime());
        dataBean.setDel(response.getContent().getDel());
        dataBean.setEnable(response.getContent().getEnable());
        dataBean.setFreeze(response.getContent().getFreeze());
        dataBean.setHeadUrl(response.getContent().getHeadUrl());
        dataBean.setUserId(response.getContent().getId());
        dataBean.setIdentity(response.getContent().getIdentity());
        dataBean.setImNickname(response.getContent().getImNickname());
        dataBean.setImPassword(response.getContent().getImPassword());
        dataBean.setImUsername(response.getContent().getImUsername());
        dataBean.setJobhuntingNum(response.getContent().getJobhuntingNum());
        dataBean.setJwtToken(response.getContent().getJwtToken());
        dataBean.setLastLogin(response.getContent().getLastLogin());
        dataBean.setMail(response.getContent().getMail());
        dataBean.setMovingNum(response.getContent().getMovingNum());
        dataBean.setName(response.getContent().getName());
        dataBean.setOldPassword(response.getContent().getOldPassword());
        dataBean.setPassword(response.getContent().getPassword());
        dataBean.setPhone(response.getContent().getPhone());
        dataBean.setQqAvatarUrl(response.getContent().getQqAvatarUrl());
        dataBean.setQqCity(response.getContent().getQqCity());
        dataBean.setQqCountry(response.getContent().getQqCountry());
        dataBean.setQqLanguage(response.getContent().getQqLanguage());
        dataBean.setQqNickname(response.getContent().getQqNickname());
        dataBean.setQqOpenId(response.getContent().getQqOpenId());
        dataBean.setQqProvince(response.getContent().getQqProvince());
        dataBean.setQualificationAuthenticate(response.getContent().getQualificationAuthenticate());
        dataBean.setRealName(response.getContent().getRealName());
        dataBean.setRealNameAuthenticate(response.getContent().getRealNameAuthenticate());
        dataBean.setRecruitmentNum(response.getContent().getRecruitmentNum());
        dataBean.setRemark(response.getContent().getRemark());
        dataBean.setSex(response.getContent().getSex());
        dataBean.setSquareNum(response.getContent().getSquareNum());
        dataBean.setState(response.getContent().getState());
        dataBean.setType(response.getContent().getType());
        dataBean.setWechatAvatarUrl(response.getContent().getWechatAvatarUrl());
        dataBean.setWechatCity(response.getContent().getWechatCity());
        dataBean.setWechatCountry(response.getContent().getWechatCountry());
        dataBean.setWechatLanguage(response.getContent().getWechatLanguage());
        dataBean.setWechatNickname(response.getContent().getWechatNickname());
        dataBean.setWechatOpenId(response.getContent().getWechatOpenId());
        dataBean.setWechatProvince(response.getContent().getWechatProvince());
        dataBean.setWxappOpenId(response.getContent().getWxappOpenId());
        if (!IsNullUtil.getInstance().isEmpty(response.getContent().getJwtToken())) {
            SPUtils.getInstance().put("token", response.getContent().getJwtToken().getToken());
        }
        dataBean.save();
        UserInfoLitePal registerEntity = LocalDataHelper.getInstance().getUserInfo();
        ZLog.d(registerEntity);
        ZLog.d(LocalDataHelper.getInstance().getUserInfo());
        //存储登录时间
        SPUtils.getInstance().put("loginTime", System.currentTimeMillis());
        AppManager.getAppManager().finishAllActivity();
        ARouter.getInstance().build(ARouterPath.Main.PAGER_MAIN).navigation();
        // FIXME: 2019/9/18 0018 这里要用后台返回的用户名和密码来注册环信，现目前没有先写死
        registerHX(registerEntity.getImUsername(), registerEntity.getImPassword());
    }

    /**
     * description: app所需权限
     * author: Andy
     * date: 2019/9/20  10:54
     */
    @TargetApi(23)
    public void requestPermissions(Context context,SuccessEntity<RegisterEntity> response) {
        new RxPermissions((FragmentActivity) context)
                .request(Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new DefaultObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            saveUserInfo(response);
                        } else {
                            ToastUtil.normalToast(context, TipsConstants.GET_PERMISSIONS_FAILED);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.normalToast(context, TipsConstants.GET_PERMISSIONS_FAILED);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
