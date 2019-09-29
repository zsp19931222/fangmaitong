package com.techangkeji.module_information.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.goldze.base.utils.SimulationData;
import com.techangkeji.model_information.BR;
import com.techangkeji.model_information.R;
import com.techangkeji.model_information.databinding.ActivityJobDetailBinding;
import com.techangkeji.module_information.ui.adapter.InviteDetailAdapter;
import com.techangkeji.module_information.ui.view_model.JobDetailViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.http.net.entity.JobHuntingEntity;
import me.goldze.mvvmhabit.http.net.entity.RecruitmentListEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.IsNullUtil;

public class JobDetailActivity extends BaseActivity<ActivityJobDetailBinding, JobDetailViewModel> {
    private JobHuntingEntity.DataBean dataBean;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_job_detail;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.context=this;
        binding.title.setTitle("求职详情");
        dataBean= (JobHuntingEntity.DataBean) getIntent().getSerializableExtra("data");
        viewModel.id=dataBean.getHuntingHumenId();
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
        viewModel.inviteYear.set(dataBean.getWorkYear());
        viewModel.inviteNature.set(dataBean.getWorkNature());
        viewModel.inviteAddress.set(dataBean.getWorkAddress());
        viewModel.inviteContent.set(dataBean.getWorkContent());
        viewModel.phone.set(dataBean.getContactUser().getPhone());
//        InviteDetailAdapter inviteDetailAdapter = new InviteDetailAdapter(R.layout.item_linkman, SimulationData.simulation());
//        binding.rv.setLayoutManager(new LinearLayoutManager(this));
//        binding.rv.setAdapter(inviteDetailAdapter);
    }
}
