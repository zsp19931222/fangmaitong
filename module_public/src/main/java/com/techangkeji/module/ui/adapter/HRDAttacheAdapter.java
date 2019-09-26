package com.techangkeji.module.ui.adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldze.base.constant.TipsConstants;
import com.goldze.base.utils.glide.GlideLoadUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.techangkeji.model_mine.ui.bean.SelectFriendBean;
import com.techangkeji.module.R;

import java.util.List;

import io.reactivex.observers.DefaultObserver;
import me.goldze.mvvmhabit.utils.ToastUtil;

public class HRDAttacheAdapter extends BaseQuickAdapter<SelectFriendBean, BaseViewHolder> {
    public HRDAttacheAdapter(int layoutResId, @Nullable List<SelectFriendBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectFriendBean item) {
        GlideLoadUtils.getInstance().glideLoad(helper.itemView.getContext(),item.getHead_url(),helper.getView(R.id.iv_hrda),0,45);
        helper.setText(R.id.tv_hrda_name,item.getReal_name());
        helper.setText(R.id.tv_hrda_phone,item.getPhone());
        helper.getView(R.id.iv_hrda_pc).setOnClickListener(v -> callPhone(helper.itemView.getContext(),item.getPhone()));
        helper.getView(R.id.iv_hrda_sm).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);//android.intent.action.SENDTO
            //2). 携带数据(号码/内容)
            intent.setData(Uri.parse("smsto:"+item.getPhone()));
            //携带额外数据
            intent.putExtra("sms_body", "");
            //3). startActivity(intent)
            helper.itemView.getContext().startActivity(intent);
        });
    }
    /**
     * 拨打电话（直接拨打电话）
     * @param phoneNum 电话号码
     */
    public void callPhone(Context context, String phoneNum){
        new RxPermissions((FragmentActivity) context)
                .request(Manifest.permission.CALL_PHONE)
                .subscribe(new DefaultObserver<Boolean>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            Uri data = Uri.parse("tel:" + phoneNum);
                            intent.setData(data);
                            context.startActivity(intent);
                        } else {
                            ToastUtil.normalToast(context, TipsConstants.GET_PERMISSIONS_FAILED);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.normalToast(context, TipsConstants.GET_PERMISSIONS_FAILED);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
