package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivitySelectFriendBinding;
import com.techangkeji.model_mine.ui.adapter.SelectFriendAdapter;
import com.techangkeji.model_mine.ui.bean.SelectFriendBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.entity.SelectFriendEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/22 20:43
 * email:zsp872126510@gmail.com
 */
public class SelectFriendActivity extends BaseActivity<ActivitySelectFriendBinding, BaseViewModel> {
    private List<SelectFriendBean> selectFriendBeans = new ArrayList<>();
    private List<SelectFriendBean> rxSelectFriendBeans = new ArrayList<>();
    private SelectFriendAdapter selectFriendAdapter;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_select_friend;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.title.setTitle("添加联系人");
        selectFriendAdapter = new SelectFriendAdapter(R.layout.item_select_friend, selectFriendBeans);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(selectFriendAdapter);
        getFriendList();
    }


    @Override
    protected void onPause() {
        super.onPause();
        for (SelectFriendBean selectFriendBean : selectFriendBeans) {
            if (selectFriendBean.isSelect()) {
                rxSelectFriendBeans.add(new SelectFriendBean(selectFriendBean.getHead_url(), selectFriendBean.getPhone(), selectFriendBean.getReal_name(), selectFriendBean.getId(), true));
            }
        }
        ZLog.d(selectFriendBeans);
        ZLog.d(rxSelectFriendBeans);
        RxBus.getDefault().post(rxSelectFriendBeans);
    }


    private void getFriendList() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", LocalDataHelper.getInstance().getUserInfo().getUserId());
        IdeaApi.getApiService()
                .getFriendList(map)
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<SelectFriendEntity>() {
                    @Override
                    public void onSuccess(SelectFriendEntity response) {
                        for (SelectFriendEntity.DataBean datum : response.getData()) {
                            selectFriendBeans.add(new SelectFriendBean(datum.getHead_url(), datum.getPhone(), datum.getReal_name(), datum.getId(), false));
                        }
                        selectFriendAdapter.notifyDataSetChanged();
                    }
                });
    }
}
