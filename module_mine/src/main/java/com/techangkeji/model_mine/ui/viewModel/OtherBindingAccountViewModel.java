package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.blankj.utilcode.util.SPUtils;
import com.goldze.base.utils.OtherLoginUtil;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.litepal.LitePal;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.BindingThirdBody;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.login.RegisterEntity;
import me.goldze.mvvmhabit.litepal.UserInfoLitePal;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/18 19:44
 * email:zsp872126510@gmail.com
 */
public class OtherBindingAccountViewModel extends BaseViewModel {
    public ObservableField<String> phone = new ObservableField<>("");
    public ObservableField<String> weChat = new ObservableField<>("");
    public ObservableField<String> qq = new ObservableField<>("");
    public ObservableField<Context> context = new ObservableField<>();

    public OtherBindingAccountViewModel(@NonNull Application application) {
        super(application);
        if (!IsNullUtil.getInstance().isEmpty(LocalDataHelper.getInstance().getUserInfo().getPhone())) {
            phone.set(LocalDataHelper.getInstance().getUserInfo().getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
        }
        if (!IsNullUtil.getInstance().isEmpty(LocalDataHelper.getInstance().getUserInfo().getWechatNickname())) {
            weChat.set(LocalDataHelper.getInstance().getUserInfo().getWechatNickname());
        } else {
            weChat.set("绑定");
        }
        if (!IsNullUtil.getInstance().isEmpty(LocalDataHelper.getInstance().getUserInfo().getQqNickname())) {
            qq.set(LocalDataHelper.getInstance().getUserInfo().getQqNickname());
        } else {
            qq.set("绑定");
        }
    }

    /**
     * description:绑定微信
     * author: Andy
     * date: 2019/9/18  19:55
     */
    public BindingCommand weChatCommand = new BindingCommand(() -> {
        if (weChat.get().equals("绑定")) {
            OtherLoginUtil.getInstance().login(context.get(), "WECHAT");
        } else {
            unBindThird(3);
        }
    });

    /**
     * description: 绑定QQ
     * author: Andy
     * date: 2019/9/18  19:55
     */
    public BindingCommand qqCommand = new BindingCommand(() -> {
        if (qq.get().equals("绑定")) {
            OtherLoginUtil.getInstance().login(context.get(), "QQ");
        } else {
            unBindThird(4);
        }
    });

    /**
     * description:绑定第三方账号
     * author: Andy
     * date: 2019/8/8 0008 11:36
     */
    public void bindThird(OtherLoginUtil.OtherLoginBean otherLoginBean) {
        int type;
        if (SHARE_MEDIA.QQ.equals(otherLoginBean.getType())) {//qq登录
            type = 4;
        } else {
            type = 3;
        }
        BindingThirdBody bindingThirdBody = new BindingThirdBody(type, LocalDataHelper.getInstance().getUserInfo().getPhone(), otherLoginBean.getOpenid(), otherLoginBean.getCity(), otherLoginBean.getName(), otherLoginBean.getIconurl(), otherLoginBean.getProvince());
        IdeaApi.getApiService()
                .bindingThird(bindingThirdBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity<RegisterEntity>>(this) {
                    @Override
                    public void onSuccess(SuccessEntity<RegisterEntity> response) {
                        if (type == 4) {
                            qq.set(response.getContent().getQqNickname());
                        } else {
                            weChat.set(response.getContent().getWechatNickname());
                        }
                        saveUserInfo(response);
                    }
                });

    }

    /**
     * description: 解绑
     * author: Andy
     * date: 2019/9/18  20:43
     */
    private void unBindThird(int type) {
        IdeaApi.getApiService()
                .untiedThird(type)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity>(this) {
                    @Override
                    public void onSuccess(SuccessEntity response) {
                        if (type == 4) {
                            qq.set("绑定");
                            //更新本地数据
                            ContentValues values = new ContentValues();
                            values.put("qqProvince", "");
                            values.put("qqAvatarUrl", "");
                            values.put("qqNickname", "");
                            values.put("qqOpenId", "");
                            values.put("qqCity", "");
                            LitePal.updateAll(RegisterEntity.class, values);
                        } else {
                            weChat.set("绑定");
                            //更新本地数据
                            ContentValues values = new ContentValues();
                            values.put("wechatNickname", "");
                            values.put("wechatAvatarUrl", "");
                            values.put("wechatProvince", "");
                            values.put("wechatCity", "");
                            values.put("wechatOpenId", "");
                            LitePal.updateAll(RegisterEntity.class, values);
                        }
                    }

                });
    }


    /**
     * description: 更新本地用户信息
     * author: Andy
     * date: 2019/9/17 0017 12:23
     */
    public void saveUserInfo(SuccessEntity<RegisterEntity> response) {
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
        ZLog.d(LocalDataHelper.getInstance().getUserInfo());
    }
}
