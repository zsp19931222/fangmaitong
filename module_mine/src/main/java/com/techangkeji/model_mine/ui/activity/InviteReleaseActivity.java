package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.goldze.base.eventbus.LocationRxBusBean;
import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityInviteReleaseBinding;
import com.techangkeji.model_mine.ui.adapter.LinkManAdapter;
import com.techangkeji.model_mine.ui.bean.SelectFriendBean;
import com.techangkeji.model_mine.ui.viewModel.InviteReleaseViewModel;

import java.util.ArrayList;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.http.net.entity.RecruitmentListEntity;
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
        RxSubscriptions.add(RxBus.getDefault().toObservable(LocationRxBusBean.class).subscribe(locationRxBusBean -> {
            viewModel.address.set(locationRxBusBean.getAddress());
            viewModel.province.set(locationRxBusBean.getProvince());
            viewModel.city.set(locationRxBusBean.getCity());
            viewModel.district.set(locationRxBusBean.getDistrict());
        }));
        RxSubscriptions.add(RxBus.getDefault().toObservable(ArrayList.class).subscribe(arrayList -> {
            ZLog.d(arrayList);
            viewModel.linkManList.addAll(arrayList);
            viewModel.linkManAdapter.get().notifyDataSetChanged();
            ZLog.d(arrayList);
        }));
        initLinkMan();

    }

    private void initLinkMan() {
        LinkManAdapter linkManAdapter = new LinkManAdapter(R.layout.item_mine_linkman, viewModel.linkManList);
        viewModel.linkManAdapter.set(linkManAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(linkManAdapter);
        linkManAdapter.setPopupSelectListener(position -> {
            viewModel.linkManList.remove(position);
            linkManAdapter.notifyDataSetChanged();
        });
        try {
            RecruitmentListEntity.DataBean dataBean = (RecruitmentListEntity.DataBean) getIntent().getSerializableExtra("data");
            viewModel.title.set(dataBean.getRecruitmentTitle());
            viewModel.position.set(dataBean.getPosition());
            viewModel.compensation.set(dataBean.getMoneyDown() + "-" + dataBean.getMoneyUp());
            viewModel.welfare.set((String) dataBean.getTreatment());
            viewModel.education.set((String) dataBean.getEducation());
            viewModel.address.set((String) dataBean.getWorkAddress());
            viewModel.job.set((String) dataBean.getWorkNature());
            viewModel.synopsis.set((String) dataBean.getWorkContent());
            viewModel.year.set((String) dataBean.getWorkYear());
            viewModel.id.set((long) dataBean.getId());
            viewModel.btn.set("重新提交");
            binding.title.setTitle("修改招聘");
            for (RecruitmentListEntity.DataBean.ContactUserBean contactUserBean : dataBean.getContactUser()) {
                viewModel.linkManList.add(new SelectFriendBean((String) contactUserBean.getHeadUrl(), contactUserBean.getPhone(), contactUserBean.getName(), contactUserBean.getId(), true));
                linkManAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            viewModel.btn.set("发布");
            binding.title.setTitle("发布招聘");
        }
    }
}
