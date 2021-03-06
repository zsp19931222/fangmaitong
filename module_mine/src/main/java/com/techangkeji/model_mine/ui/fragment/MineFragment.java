package com.techangkeji.model_mine.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.goldze.base.router.ARouterPath;
import com.goldze.base.utils.glide.GlideLoadUtils;
import com.techangkeji.model_mine.BR;
import com.techangkeji.model_mine.R;
import com.techangkeji.model_mine.databinding.FragmentMineBinding;
import com.techangkeji.model_mine.ui.viewModel.MineViewModel;

import me.goldze.mvvmhabit.base.BaseLazyFragment;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.ZLog;

@Route(path = ARouterPath.Mine.MinePage)
public class MineFragment extends BaseLazyFragment<FragmentMineBinding, MineViewModel> {
    @Override
    public void fetchData() {

    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_mine;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            String headUrl = "";
            if (!IsNullUtil.getInstance().isEmpty(LocalDataHelper.getInstance().getUserInfo().getHeadUrl())) {//有app头像
                headUrl = LocalDataHelper.getInstance().getUserInfo().getHeadUrl();
            } else if (!IsNullUtil.getInstance().isEmpty(LocalDataHelper.getInstance().getUserInfo().getWechatAvatarUrl())) {//微信头像
                headUrl = LocalDataHelper.getInstance().getUserInfo().getWechatAvatarUrl();
            } else if (!IsNullUtil.getInstance().isEmpty(LocalDataHelper.getInstance().getUserInfo().getQqAvatarUrl())) {//qq微信
                headUrl = LocalDataHelper.getInstance().getUserInfo().getQqAvatarUrl();
            }
            viewModel.url.set(headUrl);
            String name = "";
            if (!IsNullUtil.getInstance().isEmpty(LocalDataHelper.getInstance().getUserInfo().getName())) {
                name = LocalDataHelper.getInstance().getUserInfo().getName();
            } else if (!IsNullUtil.getInstance().isEmpty(LocalDataHelper.getInstance().getUserInfo().getWechatNickname())) {
                name = LocalDataHelper.getInstance().getUserInfo().getWechatNickname();
            } else if (!IsNullUtil.getInstance().isEmpty(LocalDataHelper.getInstance().getUserInfo().getQqNickname())) {
                name = LocalDataHelper.getInstance().getUserInfo().getQqNickname();
            }else {
                name=LocalDataHelper.getInstance().getUserInfo().getPhone().substring(4,8);
            }
            viewModel.name.set(name);

            //经纪人认证
            if (!IsNullUtil.getInstance().isEmpty(LocalDataHelper.getInstance().getUserInfo().getBrokerAuthenticate())) {
                if (LocalDataHelper.getInstance().getUserInfo().getBrokerAuthenticate() == 1) {
                    viewModel.brokerAuthenticateShow.set(View.GONE);
                } else {
                    viewModel.brokerAuthenticateShow.set(View.VISIBLE);
                }
            }
            //资质认证
            if (!IsNullUtil.getInstance().isEmpty(LocalDataHelper.getInstance().getUserInfo().getQualificationAuthenticate())) {
                if (LocalDataHelper.getInstance().getUserInfo().getQualificationAuthenticate() == 1) {
                    viewModel.qualificationAuthenticateShow.set(View.GONE);
                } else {
                    viewModel.qualificationAuthenticateShow.set(View.VISIBLE);
                }
            }
            //实名认证
            if (!IsNullUtil.getInstance().isEmpty(LocalDataHelper.getInstance().getUserInfo().getRealNameAuthenticate())) {
                if (LocalDataHelper.getInstance().getUserInfo().getRealNameAuthenticate() == 1) {
                    viewModel.realNameAuthenticateShow.set(View.GONE);
                } else {
                    viewModel.realNameAuthenticateShow.set(View.VISIBLE);
                }
            }

            //身份
            if (!IsNullUtil.getInstance().isEmpty(LocalDataHelper.getInstance().getUserInfo().getIdentity())) {
                switch (LocalDataHelper.getInstance().getUserInfo().getIdentity()) {
                    case 1:
                        viewModel.identity.set("总代");
                        viewModel.showHR.set(View.VISIBLE);
                        viewModel.brokerAuthenticateShow.set(View.VISIBLE);
                        break;
                    case 2:
                        viewModel.identity.set("渠道代理");
                        viewModel.showHR.set(View.GONE);
                        break;
                    case 3:
                        viewModel.identity.set("联合代理");
                        viewModel.showHR.set(View.GONE);
                        break;
                    case 4:
                        viewModel.identity.set("经纪人");
                        viewModel.showHR.set(View.GONE);
                        viewModel.qualificationAuthenticateShow.set(View.VISIBLE);
                        break;
                }
            }
            //账号
            if (!IsNullUtil.getInstance().isEmpty(LocalDataHelper.getInstance().getUserInfo().getUserId())) {
                viewModel.account.set("账号：" + LocalDataHelper.getInstance().getUserInfo().getPhone());
            }
            if (LocalDataHelper.getInstance().getUserInfo().getSex()==1){
                GlideLoadUtils.getInstance().glideLoad(getContext(),R.mipmap.man_icon,binding.ivSex,0);
            }else if (LocalDataHelper.getInstance().getUserInfo().getSex()==2){
                GlideLoadUtils.getInstance().glideLoad(getContext(),R.mipmap.woman_icon,binding.ivSex,0);
            }
        } catch (Exception e) {
            ZLog.d(e.toString());
        }
    }
}
