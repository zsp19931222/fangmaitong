package com.techangkeji.model_mine.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.techangkeji.hyphenate.chatuidemo.DemoHelper;
import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.ActivitySelectFriendBinding;
import com.techangkeji.model_mine.ui.adapter.SelectFriendAdapter;
import com.techangkeji.model_mine.ui.bean.SelectFriendBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.bus.RxBus;

/**
 * description:
 * author:created by Andy on 2019/9/22 20:43
 * email:zsp872126510@gmail.com
 */
public class SelectFriendActivity extends BaseActivity<ActivitySelectFriendBinding, BaseViewModel> {
    private Map<String, EaseUser> contactsMap;
    protected List<EaseUser> contactList = new ArrayList<>();
    private List<SelectFriendBean> selectFriendBeans = new ArrayList<>();
    private List<SelectFriendBean> rxSelectFriendBeans = new ArrayList<>();

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
        contactsMap = DemoHelper.getInstance().getContactList();
        if (contactsMap instanceof Hashtable<?, ?>) {
            //noinspection unchecked
            contactsMap = (Map<String, EaseUser>) ((Hashtable<String, EaseUser>) contactsMap).clone();
        }
        getContactList();
        for (EaseUser easeUser : contactList) {
            selectFriendBeans.add(new SelectFriendBean(easeUser.getUsername(), false));
        }
        SelectFriendAdapter selectFriendAdapter = new SelectFriendAdapter(R.layout.item_select_friend, selectFriendBeans);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(selectFriendAdapter);
    }

    protected void getContactList() {
        contactList.clear();
        if (contactsMap == null) {
            return;
        }
        synchronized (this.contactsMap) {
            Iterator<Map.Entry<String, EaseUser>> iterator = contactsMap.entrySet().iterator();
            List<String> blackList = EMClient.getInstance().contactManager().getBlackListUsernames();
            while (iterator.hasNext()) {
                Map.Entry<String, EaseUser> entry = iterator.next();
                // to make it compatible with data in previous version, you can remove this check if this is new app
                if (!entry.getKey().equals("item_new_friends")
                        && !entry.getKey().equals("item_groups")
                        && !entry.getKey().equals("item_chatroom")
                        && !entry.getKey().equals("item_robots")) {
                    if (!blackList.contains(entry.getKey())) {
                        //filter out users in blacklist
                        EaseUser user = entry.getValue();
                        EaseCommonUtils.setUserInitialLetter(user);
                        contactList.add(user);
                    }
                }
            }
        }

        // sorting
        Collections.sort(contactList, new Comparator<EaseUser>() {

            @Override
            public int compare(EaseUser lhs, EaseUser rhs) {
                if (lhs.getInitialLetter().equals(rhs.getInitialLetter())) {
                    return lhs.getNickname().compareTo(rhs.getNickname());
                } else {
                    if ("#".equals(lhs.getInitialLetter())) {
                        return 1;
                    } else if ("#".equals(rhs.getInitialLetter())) {
                        return -1;
                    }
                    return lhs.getInitialLetter().compareTo(rhs.getInitialLetter());
                }

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (SelectFriendBean selectFriendBean : selectFriendBeans) {
            if (selectFriendBean.isSelect()) {
                rxSelectFriendBeans.add(new SelectFriendBean(selectFriendBean.getPhone(), true));
            }
        }
        RxBus.getDefault().post(rxSelectFriendBeans);
    }
}
