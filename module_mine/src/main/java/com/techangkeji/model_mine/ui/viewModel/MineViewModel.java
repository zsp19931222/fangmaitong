package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.alibaba.android.arouter.launcher.ARouter;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_mine.ui.activity.AutonymAuthActivity;
import com.techangkeji.model_mine.ui.activity.BrokerAuthActivity;
import com.techangkeji.model_mine.ui.activity.CollectActivity;
import com.techangkeji.model_mine.ui.activity.FeedBackActivity;
import com.techangkeji.model_mine.ui.activity.HouseResourceActivity;
import com.techangkeji.model_mine.ui.activity.InformationCommentActivity;
import com.techangkeji.model_mine.ui.activity.InviteInformationActivity;
import com.techangkeji.model_mine.ui.activity.JobActivity;
import com.techangkeji.model_mine.ui.activity.PersonActivity;
import com.techangkeji.model_mine.ui.activity.QualificationAuthActivity;
import com.techangkeji.model_mine.ui.activity.SettingActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.IsNullUtil;

public class MineViewModel extends BaseViewModel {
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> url = new ObservableField<>("");
    public ObservableField<Integer> realNameAuthenticateShow = new ObservableField<>(View.GONE);//实名认证
    public ObservableField<Integer> qualificationAuthenticateShow = new ObservableField<>(View.GONE);//资质认证
    public ObservableField<Integer> brokerAuthenticateShow = new ObservableField<>(View.GONE);//经纪人认证
    public ObservableField<String> identity = new ObservableField<>("");//身份
    public ObservableField<String> account = new ObservableField<>("");//账号


    public MineViewModel(@NonNull Application application) {
        super(application);
    }

    //个人资料
    public BindingCommand personCommand = new BindingCommand(() -> startActivity(PersonActivity.class));
    //实名认证
    public BindingCommand autonymAuthCommand = new BindingCommand(() -> startActivity(AutonymAuthActivity.class));
    //资质认证
    public BindingCommand qualificationAuthCommand = new BindingCommand(() -> startActivity(QualificationAuthActivity.class));
    //经纪人认证
    public BindingCommand brokerCommand = new BindingCommand(() -> startActivity(BrokerAuthActivity.class));
    //资讯评论
    public BindingCommand informationCommentCommand = new BindingCommand(() -> startActivity(InformationCommentActivity.class));
    //招聘
    public BindingCommand inviteInformationCommentCommand = new BindingCommand(() -> startActivity(InviteInformationActivity.class));
    //求职
    public BindingCommand jobCommentCommand = new BindingCommand(() -> startActivity(JobActivity.class));
    //房源
    public BindingCommand houseResourceCommand = new BindingCommand(() -> startActivity(HouseResourceActivity.class));
    //收藏
    public BindingCommand collectCommand = new BindingCommand(() -> startActivity(CollectActivity.class));
    //设置
    public BindingCommand settingCommand = new BindingCommand(() -> startActivity(SettingActivity.class));
    //反馈
    public BindingCommand feedBackCommand = new BindingCommand(() -> startActivity(FeedBackActivity.class));
    //我的动态
    public BindingCommand myStateCommand = new BindingCommand(() -> ARouter.getInstance().build(ARouterPath.FriendCircle.MyStateActivity).navigation());
    //我的广场信息
    public BindingCommand myInformationCommand = new BindingCommand(() -> ARouter.getInstance().build(ARouterPath.FriendCircle.MyStateActivity).navigation());

}
