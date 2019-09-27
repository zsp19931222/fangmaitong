package com.techangkeji.module_information.ui.view_model;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.alibaba.android.arouter.launcher.ARouter;
import com.goldze.base.router.ARouterPath;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

public class InviteDetailViewModel extends BaseViewModel {
    public ObservableField<String> hrUrl=new ObservableField<>("");
    public ObservableField<String> hrName=new ObservableField<>("");
    public ObservableField<String> hrNum=new ObservableField<>("");
    public ObservableField<String> hrIdent=new ObservableField<>("");
    public ObservableField<Integer> hrAuthSM=new ObservableField<>(View.GONE);
    public ObservableField<Integer> hrAuthZZ=new ObservableField<>(View.GONE);
    public ObservableField<Integer> hrAuthJJR=new ObservableField<>(View.GONE);
    public ObservableField<String> inviteCompany=new ObservableField<>("");
    public ObservableField<String> inviteName=new ObservableField<>("");
    public ObservableField<String> invitePrice=new ObservableField<>("");
    public ObservableField<String> inviteWelfare=new ObservableField<>("");
    public ObservableField<String> inviteEducation=new ObservableField<>("");
    public ObservableField<String> inviteYear=new ObservableField<>("");
    public ObservableField<String> inviteAddress=new ObservableField<>("");
    public ObservableField<String> inviteNature=new ObservableField<>("");
    public ObservableField<String> inviteContent=new ObservableField<>("");
    public ObservableField<String> inviteLinkMan=new ObservableField<>("");
    public InviteDetailViewModel(@NonNull Application application) {
        super(application);
    }

    //跳转到动态页面
    public BindingCommand circleCommand = new BindingCommand(() -> ARouter.getInstance().build(ARouterPath.FriendCircle.PersonCircleStateActivity).navigation());
}
