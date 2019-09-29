package com.techangkeji.module.ui.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.techangkeji.module.BR;
import com.techangkeji.module.R;
import com.techangkeji.module.databinding.ActivitySearchBinding;
import com.techangkeji.module.ui.view_model.SearchViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * description:
 * author:created by Andy on 2019/9/29 0029 16:04
 * email:zsp872126510@gmail.com
 */
@Route(path = ARouterPath.Public.SearchActivity)
public class SearchActivity extends BaseActivity<ActivitySearchBinding, SearchViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_search;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

}
