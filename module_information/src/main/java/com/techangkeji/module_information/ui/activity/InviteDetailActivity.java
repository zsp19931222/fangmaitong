package com.techangkeji.module_information.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_information.BR;
import com.techangkeji.model_information.R;
import com.techangkeji.model_information.databinding.ActivityInviteDetailBinding;
import com.techangkeji.module_information.ui.adapter.InviteDetailAdapter;
import com.techangkeji.module_information.ui.view_model.InviteDetailViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.http.net.entity.RecruitmentListEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.IsNullUtil;

@Route(path = ARouterPath.Information.InviteDetailActivity)
public class InviteDetailActivity extends BaseActivity<ActivityInviteDetailBinding, InviteDetailViewModel> {
    private RecruitmentListEntity.DataBean dataBean;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_invite_detail;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        dataBean= (RecruitmentListEntity.DataBean) getIntent().getSerializableExtra("data");
        viewModel.hrUrl.set(dataBean.getUser().getHeadUrl());
        viewModel.hrName.set(dataBean.getUser().getRealName());
        viewModel.hrNum.set(dataBean.getUser().getId()+"");
        if (!IsNullUtil.getInstance().isEmpty(dataBean.getUser().getIdentity())) {
            switch (LocalDataHelper.getInstance().getUserInfo().getIdentity()) {
                case 1:
                    viewModel.hrIdent.set("总代");
                    break;
                case 2:
                    viewModel.hrIdent.set("渠道代理");
                    break;
                case 3:
                    viewModel.hrIdent.set("联合代理");
                    break;
                case 4:
                    viewModel.hrIdent.set("经纪人");
                    break;
            }
        }
        if (dataBean.getUser().getBrokerAuthenticate()==1){
            viewModel.hrAuthJJR.set(View.VISIBLE);
        }else {
            viewModel.hrAuthJJR.set(View.GONE);
        }
        if (dataBean.getUser().getQualificationAuthenticate()==1){
            viewModel.hrAuthZZ.set(View.VISIBLE);
        }else {
            viewModel.hrAuthZZ.set(View.GONE);
        }
        if (dataBean.getUser().getRealNameAuthenticate()==1){
            viewModel.hrAuthSM.set(View.VISIBLE);
        }else {
            viewModel.hrAuthSM.set(View.GONE);
        }
        viewModel.inviteCompany.set(dataBean.getRecruitmentTitle());
        viewModel.inviteName.set(dataBean.getPosition());
        viewModel.invitePrice.set(dataBean.getMoneyDown()+"-"+dataBean.getMoneyUp());
        viewModel.inviteWelfare.set(IsNullUtil.getInstance().StringNull((String) dataBean.getTreatment()));
        viewModel.inviteEducation.set(dataBean.getEducation());
        viewModel.inviteYear.set((String) dataBean.getWorkYear());
        viewModel.inviteAddress.set(dataBean.getWorkAddress());
        viewModel.inviteNature.set(dataBean.getWorkNature());
        viewModel.inviteContent.set(dataBean.getWorkContent());
        viewModel.id=dataBean.getRecruitmentHumenId();
        binding.title.setTitle("招聘详情");
        InviteDetailAdapter inviteDetailAdapter = new InviteDetailAdapter(R.layout.item_linkman, dataBean.getContactUser());
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(inviteDetailAdapter);
    }
}
