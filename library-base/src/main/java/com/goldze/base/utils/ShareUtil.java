package com.goldze.base.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;
import android.widget.PopupWindow;

import androidx.collection.LruCache;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import java.io.File;

import io.reactivex.observers.DefaultObserver;
import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.ToastUtil;
import me.goldze.mvvmhabit.utils.ZLog;

import static com.goldze.base.constant.TipsConstants.GET_PERMISSIONS_FAILED;

/**
 * description:
 * author:created by Andy on 2019/9/16 0016 11:20
 * email:zsp872126510@gmail.com
 */
public class ShareUtil {
    private static final ShareUtil ourInstance = new ShareUtil();

    public static ShareUtil getInstance() {
        return ourInstance;
    }

    private ShareUtil() {
    }

    public void share(Context context, Bitmap bitmap) {
        if (IsNullUtil.getInstance().isEmpty(bitmap)) return;
        UMImage image = new UMImage(context, bitmap);//bitmap文件
        new RxPermissions((FragmentActivity) context)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new DefaultObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            ShareBoardConfig config = new ShareBoardConfig();//新建ShareBoardConfig
                            config.setOnDismissListener(() -> RxBus.getDefault().post("完成分享"));
                            new ShareAction((Activity) context).withText("hello").withMedia(image).setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                                    .setCallback(shareListener).open(config);

                        } else {
                            ToastUtil.errorToast(context, GET_PERMISSIONS_FAILED, false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.errorToast(context, GET_PERMISSIONS_FAILED, false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            ZLog.d("onStart");
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "分享成功");
            RxBus.getDefault().post("完成分享");
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "分享失败");
            RxBus.getDefault().post("完成分享");
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtil.normalToast(BaseApplication.getInstance().getBaseContext(), "取消分享");
            RxBus.getDefault().post("完成分享");
        }
    };
}
