package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivityChangeNickNameBinding;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.utils.ZLog;

public class ChangeNickNameActivity extends BaseActivity<ActivityChangeNickNameBinding, BaseViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_change_nick_name;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("修改昵称");
        binding.confirm.setOnClickListener(view -> {
            ZLog.d(binding.et.getText());
            RxBus.getDefault().post(new ChangeNameBean(binding.et.getText().toString()));
            finish();
        });
    }

    public class ChangeNameBean{
        private String name;

        public ChangeNameBean(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
