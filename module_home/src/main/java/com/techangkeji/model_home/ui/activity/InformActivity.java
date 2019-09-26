package com.techangkeji.model_home.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.model_home.BR;
import com.techangkeji.model_home.R;
import com.techangkeji.model_home.databinding.ActivityInformBinding;
import com.techangkeji.model_home.ui.popupwindow.InformPopup;
import com.techangkeji.model_home.ui.view_midel.InformViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * description:举报
 * author:created by Andy on 2019/9/16 0016 17:06
 * email:zsp872126510@gmail.com
 */
@Route(path = ARouterPath.Home.InformActivity)
public class InformActivity extends BaseActivity<ActivityInformBinding, InformViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_inform;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("举报");
        binding.tvAiPopup.setOnClickListener(view -> {
            InformPopup informPopup=  new InformPopup(this);
            informPopup.setOnSelectString(select -> viewModel.reason.set(select));
            informPopup.showPopupWindow(view);
        });
        viewModel.listingName.set(getIntent().getExtras().getString("listingName"));
        viewModel.listingId.set(getIntent().getExtras().getString("listingId"));
    }

}
