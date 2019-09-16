package com.techangkeji.model_home.ui.view_midel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.techangkeji.model_home.ui.activity.AuctionActivity;
import com.techangkeji.model_home.ui.activity.CreditActivity;
import com.techangkeji.model_home.ui.activity.HouseMortgagePledgeActivity;
import com.techangkeji.model_home.ui.activity.HousePledgeActivity;
import com.techangkeji.model_home.ui.activity.MortgagePledgeActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * description:
 * author:created by Andy on 2019/9/16 0016 18:11
 * email:zsp872126510@gmail.com
 */
public class HousingInformationViewModel extends BaseViewModel {
    public HousingInformationViewModel(@NonNull Application application) {
        super(application);
    }

    //房产抵押贷款
    public BindingCommand housePledgeCommand=new BindingCommand(() -> {
        startActivity(HousePledgeActivity.class);
    });
    //按揭房再贷
    public BindingCommand mortgagePledgeCommand=new BindingCommand(() -> {
        startActivity(MortgagePledgeActivity.class);
    });
    //住房按揭贷
    public BindingCommand houseMortgagePledgeCommand=new BindingCommand(() -> {
        startActivity(HouseMortgagePledgeActivity.class);
    });
    //个人信用贷
    public BindingCommand creditCommand=new BindingCommand(() -> {
        startActivity(CreditActivity.class);
    });
    //拍卖房按揭贷款
    public BindingCommand auctionCommand=new BindingCommand(() -> {
        startActivity(AuctionActivity.class);
    });
}
