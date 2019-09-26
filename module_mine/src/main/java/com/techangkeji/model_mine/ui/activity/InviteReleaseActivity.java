package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.goldze.base.eventbus.LocationRxBusBean;
import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityInviteReleaseBinding;
import com.techangkeji.model_mine.ui.adapter.LinkManAdapter;
import com.techangkeji.model_mine.ui.viewModel.InviteReleaseViewModel;

import java.util.ArrayList;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.ZLog;

public class InviteReleaseActivity extends BaseActivity<ActivityInviteReleaseBinding, InviteReleaseViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_invite_release;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.context.set(this);
        viewModel.tv_ir_education.set(binding.tvIrEducation);
        viewModel.tv_ir_job.set(binding.tvIrJob);
        binding.title.setTitle("发布招聘");
        RxSubscriptions.add(RxBus.getDefault().toObservable(LocationRxBusBean.class).subscribe(locationRxBusBean -> {
            viewModel.address.set(locationRxBusBean.getAddress());
            viewModel.province.set(locationRxBusBean.getProvince());
            viewModel.city.set(locationRxBusBean.getCity());
            viewModel.district.set(locationRxBusBean.getDistrict());
        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(ArrayList.class).subscribe(arrayList -> {
            viewModel.linkManList.addAll(arrayList);
            viewModel.linkManAdapter.get().notifyDataSetChanged();
            ZLog.d(arrayList);
        }));
        initLinkMan();
    }

    private void initLinkMan() {
        LinkManAdapter linkManAdapter = new LinkManAdapter(R.layout.item_linkman, viewModel.linkManList);
        viewModel.linkManAdapter.set(linkManAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(linkManAdapter);
    }
}
