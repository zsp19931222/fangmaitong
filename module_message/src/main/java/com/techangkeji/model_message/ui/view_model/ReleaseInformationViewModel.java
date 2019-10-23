package com.techangkeji.model_message.ui.view_model;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.fragment.app.FragmentActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.techangkeji.model_message.ui.bean.ReleaseInformationBean;

import org.litepal.LitePal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.ReleaseMovingBody;
import me.goldze.mvvmhabit.http.net.body.UpdateBody;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.UpLoadImageEntity;
import me.goldze.mvvmhabit.http.net.entity.login.RegisterEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.MaterialDialogUtils;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtil;
import me.goldze.mvvmhabit.utils.ZLog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.goldze.base.constant.TipsConstants.GET_PERMISSIONS_FAILED;

/**
 * description:
 * author:created by Andy on 2019/9/17 0017 17:01
 * email:zsp872126510@gmail.com
 */
public class ReleaseInformationViewModel extends BaseViewModel {
    public ObservableList<ReleaseInformationBean> imageUrls = new ObservableArrayList<>();
    public ObservableList<String> imagePathList = new ObservableArrayList<>();
    public ObservableField<StringBuilder> stringBuilder = new ObservableField<>(new StringBuilder());
    public ObservableField<String> content = new ObservableField<>("");
    public ObservableField<Context> context = new ObservableField<>();
    private MaterialDialog dialog;


    public ReleaseInformationViewModel(@NonNull Application application) {
        super(application);
    }

    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void showDialog() {
        if (IsNullUtil.getInstance().isEmpty(dialog)) {
            MaterialDialog.Builder builder = MaterialDialogUtils.showIndeterminateProgressDialog(context.get(), "上传中，请稍后", true);
            dialog = builder.show();
        } else {
            dialog.show();
        }
    }

    /**
     * description:发布
     * author: Andy
     * date: 2019/9/17 0017 17:03
     */
    public BindingCommand releaseCommand = new BindingCommand(() -> {
        showDialog();
        int size = imagePathList.size();
        if (size > 0) {
            uploadImage();
        } else {
            if (content.get().length() < 0) {
                ToastUtil.normalToast(context.get(), "请输入点什么吧");
            } else {
                release();
            }
        }
    });

    /**
     * description: 打开相册
     * author: Andy
     * date: 2019/9/12 0012 9:46
     */
    public void openGallery(FragmentActivity activity) {
        new RxPermissions(activity)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new io.reactivex.observers.DefaultObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            PictureSelector.create(activity)
                                    .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                                    .maxSelectNum(9)// 最大图片选择数量 int
                                    .minSelectNum(1)// 最小选择数量
                                    .imageSpanCount(4)// 每行显示个数 int
                                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                                    .previewImage(true)// 是否可预览图片 true or false
                                    .isCamera(true)// 是否显示拍照按钮 true or false
                                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                                    .enableCrop(false)// 是否裁剪 true or false
                                    .compress(true)// 是否压缩 true or false
                                    .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                                    .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                                    .isGif(false)// 是否显示gif图片 true or false
                                    .freeStyleCropEnabled(false)// 裁剪框是否可拖拽 true or false
                                    .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                                    .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                                    .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                        } else {
                            ToastUtil.errorToast(activity, GET_PERMISSIONS_FAILED, false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ZLog.e(e.toString());
                        ToastUtil.errorToast(activity, GET_PERMISSIONS_FAILED, false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * description:上传图片
     * author: Andy
     * date: 2019/9/17  20:20
     */
    private void uploadImage() {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (ReleaseInformationBean imageUrl : imageUrls) {
            builder.addFormDataPart("file", new File((String) imageUrl.getImage()).getName(), RequestBody.create(MediaType.parse("multipart/form-data"), new File((String) imageUrl.getImage())));

        }
        List<MultipartBody.Part> parts = builder.build().parts();
        IdeaApi.getApiService()
                .uploadpic(parts)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<UpLoadImageEntity>() {
                    @Override
                    public void onSuccess(UpLoadImageEntity response) {
                        for (String datum : response.getData()) {
                            stringBuilder.get().append(datum).append(",");
                        }
                        release();
                    }

                    @Override
                    public void onFail(UpLoadImageEntity response) {
                        ToastUtil.normalToast(context.get(), response.getMsg());
                        dismissDialog();
                    }

                    @Override
                    public void onException(ExceptionReason reason) {
                        dismissDialog();
                        ToastUtil.normalToast(context.get(), setErrorText(reason));
                    }
                });
    }

    /**
     * description:
     * author: Andy
     * date: 2019/9/17  20:33
     */
    private void release() {
        ZLog.d(stringBuilder.get().toString());
        ReleaseMovingBody releaseMovingBody = new ReleaseMovingBody(stringBuilder.get().toString(), content.get(), 0);
        IdeaApi.getApiService()
                .releaseMoving(releaseMovingBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new DefaultObserver<SuccessEntity>() {
                    @Override
                    public void onSuccess(SuccessEntity response) {
                        dismissDialog();
                        ToastUtil.normalToast(context.get(), response.getMsg());
                        finish();
                    }

                    @Override
                    public void onFail(SuccessEntity response) {
                        ToastUtil.normalToast(context.get(), response.getMsg());
                        dismissDialog();
                    }

                    @Override
                    public void onException(ExceptionReason reason) {
                        dismissDialog();
                        ToastUtil.normalToast(context.get(), setErrorText(reason));
                    }
                });
    }
}
