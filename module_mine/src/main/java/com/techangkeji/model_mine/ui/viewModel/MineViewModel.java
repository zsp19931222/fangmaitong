package com.techangkeji.model_mine.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.techangkeji.model_mine.ui.activity.AutonymAuthActivity;
import com.techangkeji.model_mine.ui.activity.BokerAuthActivity;
import com.techangkeji.model_mine.ui.activity.CollectActivity;
import com.techangkeji.model_mine.ui.activity.FeedBackActivity;
import com.techangkeji.model_mine.ui.activity.HouseResourceActivity;
import com.techangkeji.model_mine.ui.activity.InformationCommentActivity;
import com.techangkeji.model_mine.ui.activity.InviteInformationActivity;
import com.techangkeji.model_mine.ui.activity.InviteReleaseActivity;
import com.techangkeji.model_mine.ui.activity.PersonActivity;
import com.techangkeji.model_mine.ui.activity.QualificationAuthActivity;
import com.techangkeji.model_mine.ui.activity.SettingActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class MineViewModel extends BaseViewModel {
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
    public BindingCommand brokerCommand = new BindingCommand(() -> startActivity(BokerAuthActivity.class));
    //资讯评论
    public BindingCommand informationCommentCommand = new BindingCommand(() -> startActivity(InformationCommentActivity.class));
    //招聘
    public BindingCommand inviteInformationCommentCommand = new BindingCommand(() -> startActivity(InviteInformationActivity.class));
    //房源
    public BindingCommand houseResourceCommand = new BindingCommand(() -> startActivity(HouseResourceActivity.class));
    //收藏
    public BindingCommand collectCommand = new BindingCommand(() -> startActivity(CollectActivity.class));
    //设置
    public BindingCommand settingCommand = new BindingCommand(() -> startActivity(SettingActivity.class));
    //反馈
    public BindingCommand feedBackCommand = new BindingCommand(() -> startActivity(FeedBackActivity.class));

}
