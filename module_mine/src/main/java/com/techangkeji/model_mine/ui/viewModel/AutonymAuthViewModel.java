package com.techangkeji.model_mine.ui.viewModel;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.fragment.app.FragmentActivity;

import com.goldze.base.constant.TipsConstants;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.AuthRealNameBody;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.UpLoadImageEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.goldze.base.constant.TipsConstants.GET_PERMISSIONS_FAILED;

/**
 * description:
 * author:created by Andy on 2019/9/18 0018 14:24
 * email:zsp872126510@gmail.com
 */
public class AutonymAuthViewModel extends BaseViewModel {
    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> idNum = new ObservableField<>("");
    public ObservableField<String> frontUrl = new ObservableField<>("");
    public ObservableField<String> reverseUrl = new ObservableField<>("");
    public ObservableField<Context> context = new ObservableField<>();
    public ObservableField<Integer> frontResultCode = new ObservableField<>(10086);
    public ObservableField<Integer> reverseResultCode = new ObservableField<>(10087);

    public AutonymAuthViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand submitCommand = new BindingCommand(() -> {
        if (IsNullUtil.getInstance().isEmpty(name.get()) || IsNullUtil.getInstance().isEmpty(idNum.get()) || IsNullUtil.getInstance().isEmpty(frontUrl.get()) || IsNullUtil.getInstance().isEmpty(reverseUrl.get())) {
            ToastUtil.normalToast(context.get(), TipsConstants.PARAMETER_ERROR);
            return;
        }
        AuthRealNameBody authRealNameBody = new AuthRealNameBody(null, null, reverseUrl.get(), -1, idNum.get(), name.get(), LocalDataHelper.getInstance().getUserInfo().getPhone(), LocalDataHelper.getInstance().getUserInfo().getIdentity(), -1, LocalDataHelper.getInstance().getUserInfo().getUserId(), LocalDataHelper.getInstance().getUserInfo().getName(), -1, frontUrl.get());
        IdeaApi.getApiService()
                .realName(authRealNameBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable1 -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity>(this) {
                    @Override
                    public void onSuccess(SuccessEntity response) {
                        ToastUtil.normalToast(context.get(), response.getMsg());
                    }
                });
    });
    /**
     * description: 选择正面照
     * author: Andy
     * date: 2019/9/18 0018 14:33
     */
    public BindingCommand frontCommand = new BindingCommand(() -> new RxPermissions((FragmentActivity) context.get())
            .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe(new io.reactivex.observers.DefaultObserver<Boolean>() {
                @Override
                public void onNext(Boolean aBoolean) {
                    if (aBoolean) {
                        openGallery(frontResultCode.get());
                    } else {
                        ToastUtil.errorToast(context.get(), GET_PERMISSIONS_FAILED, false);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    ToastUtil.errorToast(context.get(), GET_PERMISSIONS_FAILED, false);
                }

                @Override
                public void onComplete() {

                }
            }));
    /**
     * description: 选择反面照
     * author: Andy
     * date: 2019/9/18 0018 14:33
     */
    public BindingCommand reverseCommand = new BindingCommand(() -> new RxPermissions((FragmentActivity) context.get())
            .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe(new io.reactivex.observers.DefaultObserver<Boolean>() {
                @Override
                public void onNext(Boolean aBoolean) {
                    if (aBoolean) {
                        openGallery(reverseResultCode.get());
                    } else {
                        ToastUtil.errorToast(context.get(), GET_PERMISSIONS_FAILED, false);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    ToastUtil.errorToast(context.get(), GET_PERMISSIONS_FAILED, false);
                }

                @Override
                public void onComplete() {

                }
            }));

    /**
     * description: 上传图片
     * author: Andy
     * date: 2019/9/17 0017 17:14
     */
    public void uploadpic(String imagePath, int resultCode) {
        File file = new File(imagePath);
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);//表单类型
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);//表单类型
        builder.addFormDataPart("file", file.getName(), body); //添加图片数据，body创建的请求体
        List<MultipartBody.Part> parts = builder.build().parts();
        IdeaApi.getApiService()
                .uploadpic(parts)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable1 -> showDialog())
                .subscribe(new DefaultObserver<UpLoadImageEntity>(this) {
                    @Override
                    public void onSuccess(UpLoadImageEntity response) {
                        ToastUtil.normalToast(context.get(), response.getMsg());
                        if (resultCode == frontResultCode.get()) {
                            frontUrl.set(response.getData().get(0));
                        } else {
                            reverseUrl.set(response.getData().get(0));
                        }
                    }
                });
    }

    /**
     * description: 打开相册
     * author: Andy
     * date: 2019/9/12 0012 9:46
     */
    private void openGallery(int resultCode) {
        PictureSelector.create((Activity) context.get())
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
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
                .forResult(resultCode);//结果回调onActivityResult code
    }
}
