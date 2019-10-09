package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import com.goldze.base.eventbus.LocationRxBusBean;
import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityJobReleaseBinding;
import com.techangkeji.model_mine.ui.viewModel.JobReleaseViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.http.net.entity.JobHuntingEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;

/**
 * description:
 * author:created by Andy on 2019/9/27 22:25
 * email:zsp872126510@gmail.com
 */
public class JobReleaseActivity extends BaseActivity<ActivityJobReleaseBinding, JobReleaseViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_job_release;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        try {
            JobHuntingEntity.DataBean dataBean = (JobHuntingEntity.DataBean) getIntent().getSerializableExtra("data");
            viewModel.title.set(dataBean.getHuntingTitle());
            viewModel.year.set(dataBean.getWorkYear());
            viewModel.address.set(dataBean.getWorkAddress());
            viewModel.synopsis.set(dataBean.getWorkContent());
            viewModel.job.set(dataBean.getWorkNature());
            binding.title.setTitle("修改求职");
            viewModel.btn.set("重新提交");
            viewModel.id.set((long) dataBean.getId());
        }catch (Exception e){
            binding.title.setTitle("发布求职");
            viewModel.btn.set("发布");
        }
        viewModel.context.set(this);
        viewModel.tv_ir_job.set(binding.tvIrJob);
        RxSubscriptions.add(RxBus.getDefault().toObservable(LocationRxBusBean.class).subscribe(locationRxBusBean -> {
            viewModel.address.set(locationRxBusBean.getAddress());
            viewModel.province.set(locationRxBusBean.getProvince());
            viewModel.city.set(locationRxBusBean.getCity());
            viewModel.district.set(locationRxBusBean.getDistrict());
        }));
    }
}
