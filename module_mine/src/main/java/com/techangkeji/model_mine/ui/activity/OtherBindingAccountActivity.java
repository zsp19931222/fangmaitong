package com.techangkeji.model_mine.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.goldze.base.constant.TipsConstants;
import com.goldze.base.utils.OtherLoginUtil;
import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityOtherBindingAccountBinding;
import com.techangkeji.model_mine.ui.viewModel.OtherBindingAccountViewModel;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.ToastUtil;

import static com.goldze.base.utils.OtherLoginUtil.LOGIN_SUCCESS;

public class OtherBindingAccountActivity extends BaseActivity<ActivityOtherBindingAccountBinding, OtherBindingAccountViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_other_binding_account;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.context.set(this);
        binding.title.setTitle("绑定账号");
        RxSubscriptions.add(RxBus.getDefault().toObservable(OtherLoginUtil.OtherLoginBean.class).subscribe(otherLoginBean -> {
            if (LOGIN_SUCCESS.equals(otherLoginBean.getCode())) {
                viewModel.bindThird(otherLoginBean);
            } else {
                if (SHARE_MEDIA.QQ.equals(otherLoginBean.getType())) {
                    ToastUtil.normalToast(this, TipsConstants.OTHER_BIND_FAIL_QQ);
                } else {
                    ToastUtil.normalToast(this, TipsConstants.OTHER_BIND_FAIL_WE_CHAT);
                }
            }
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
