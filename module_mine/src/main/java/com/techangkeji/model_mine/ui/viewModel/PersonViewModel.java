package com.techangkeji.model_mine.ui.viewModel;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.fragment.app.FragmentActivity;

import com.goldze.base.utils.PermissionsUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.techangkeji.model_mine.ui.activity.ChangeNickNameActivity;
import com.techangkeji.model_mine.ui.activity.PersonActivity;
import com.techangkeji.model_mine.ui.popup.SexPopupwindow;
import com.techangkeji.model_mine.ui.popup.TimePickerPopupwindow;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import org.litepal.crud.LitePalSupport;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
import me.goldze.mvvmhabit.http.net.body.RegisterBody;
import me.goldze.mvvmhabit.http.net.body.UpdateBody;
import me.goldze.mvvmhabit.http.net.entity.SuccessEntity;
import me.goldze.mvvmhabit.http.net.entity.login.RegisterEntity;
import me.goldze.mvvmhabit.litepal.util.LocalDataHelper;
import me.goldze.mvvmhabit.utils.IsNullUtil;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtil;
import me.goldze.mvvmhabit.utils.ZLog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.goldze.base.constant.RxBusMessageEventConstants.OPEN_GALLERY;
import static com.goldze.base.constant.TipsConstants.GET_PERMISSIONS_FAILED;

public class PersonViewModel extends BaseViewModel {
    public ObservableField<Context> context = new ObservableField<>();
    public ObservableField<Integer> sexField = new ObservableField<>();
    public ObservableField<String> sexStrField = new ObservableField<>("");
    public ObservableField<String> ageField = new ObservableField<>();
    public ObservableField<String> headUrl = new ObservableField<>();
    public ObservableField<String> nickName = new ObservableField<>("");

    public PersonViewModel(@NonNull Application application) {
        super(application);
        try {
            if (!IsNullUtil.getInstance().isEmpty(LocalDataHelper.getInstance().getUserInfo().getHeadUrl())) {
                headUrl.set(LocalDataHelper.getInstance().getUserInfo().getHeadUrl());
            }
            if (!IsNullUtil.getInstance().isEmpty(LocalDataHelper.getInstance().getUserInfo().getName())) {
                nickName.set(LocalDataHelper.getInstance().getUserInfo().getName());
            }
            if (!IsNullUtil.getInstance().isEmpty(LocalDataHelper.getInstance().getUserInfo().getSex())) {
                sexField.set(LocalDataHelper.getInstance().getUserInfo().getSex());
                if (LocalDataHelper.getInstance().getUserInfo().getSex() == 1) {
                    sexStrField.set("男");
                } else if (LocalDataHelper.getInstance().getUserInfo().getSex() == 2) {
                    sexStrField.set("女");
                }
            }
            if (!IsNullUtil.getInstance().isEmpty(LocalDataHelper.getInstance().getUserInfo().getAge())) {
                ageField.set(LocalDataHelper.getInstance().getUserInfo().getAge() + "");
            }
        } catch (Exception e) {

        }

    }


    public BindingCommand changeNameCommand = new BindingCommand(() -> startActivity(ChangeNickNameActivity.class));

    /**
     * description: 选择头像
     * author: Andy
     * date: 2019/9/16  21:00
     */
    public BindingCommand choiceHeadImage = new BindingCommand(() -> new RxPermissions((FragmentActivity) context.get())
            .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe(new io.reactivex.observers.DefaultObserver<Boolean>() {
                @Override
                public void onNext(Boolean aBoolean) {
                    if (aBoolean) {
                        openGallery();
                    } else {
                        ToastUtil.errorToast(context.get(), GET_PERMISSIONS_FAILED, false);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    ZLog.e(e.toString());
                    ToastUtil.errorToast(context.get(), GET_PERMISSIONS_FAILED, false);
                }

                @Override
                public void onComplete() {

                }
            })
    );

    /**
     * description: 选择性别
     * author: Andy
     * date: 2019/9/16  21:12
     */
    public BindingCommand sexCommand = new BindingCommand(() -> {
        SexPopupwindow sexPopupwindow = new SexPopupwindow(context.get());
        sexPopupwindow.setOnSelectSexListener(sex -> {
            sexStrField.set(sex);
            if (sex.equals("男")) {
                sexField.set(1);
            } else {
                sexField.set(2);
            }
        });
        sexPopupwindow.showPopupWindow();
    });

    /**
     * description: 修改年龄
     * author: Andy
     * date: 2019/9/16  21:16
     */
    public BindingCommand changeAgeCommand = new BindingCommand(() -> {
        TimePickerPopupwindow timePickerPopupwindow = new TimePickerPopupwindow(context.get());
        timePickerPopupwindow.setOnSelectDateListener(date -> ageField.set(getAgeByBirth(date) + ""));
        timePickerPopupwindow.showPopupWindow();
    });


    /**
     * description: 年龄计算
     * author: Andy
     * date: 2019/9/16  21:17
     */
    public int getAgeByBirth(Date birthDay) {
        int age = 0;

        try {
            Calendar cal = Calendar.getInstance();
            if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
                ToastUtil.normalToast(context.get(), "出生日期晚于当前时间，无法计算");
            }
            int yearNow = cal.get(Calendar.YEAR);  //当前年份
            int monthNow = cal.get(Calendar.MONTH);  //当前月份
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
            cal.setTime(birthDay);
            int yearBirth = cal.get(Calendar.YEAR);
            int monthBirth = cal.get(Calendar.MONTH);
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
            age = yearNow - yearBirth;   //计算整岁数
            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
                } else {
                    age--;//当前月份在生日之前，年龄减一
                }
            }
        } catch (Exception e) {

        }
        if (age < 0) {
            age = 0;
        }
        return age;
    }

    /**
     * description: 保存
     * author: Andy
     * date: 2019/9/17 0017 14:47
     */
    public BindingCommand saveCommand = new BindingCommand(() -> {
        uploadpic();
    });

    /**
     * description: 上传头像
     * author: Andy
     * date: 2019/9/17 0017 17:14
     */
    public void uploadpic() {
        ZLog.d(headUrl.get());
        if (IsNullUtil.getInstance().isEmpty(headUrl.get())) {//不需要上传图片
            UpdateBody updateBody = new UpdateBody(nickName.get(), sexField.get(), Integer.valueOf(ageField.get()));
            updateInformation(updateBody);
        } else {
            try {//headUrl不是来自网络图片
                File file = new File(headUrl.get());
                RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);//表单类型
                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);//表单类型
                builder.addFormDataPart("file", file.getName(), body); //添加图片数据，body创建的请求体
                List<MultipartBody.Part> parts = builder.build().parts();
                IdeaApi.getApiService()
                        .uploadpic(parts)
                        .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                        .compose(RxUtils.schedulersTransformer())
                        .doOnSubscribe(disposable1 -> showDialog())
                        .subscribe(new DefaultObserver<SuccessEntity<String>>(this) {
                            @Override
                            public void onSuccess(SuccessEntity<String> response) {
                                headUrl.set(response.getContent());
                                UpdateBody updateBody = new UpdateBody(headUrl.get(), nickName.get(), sexField.get(), Integer.valueOf(ageField.get()));
                                updateInformation(updateBody);
                            }
                        });
            } catch (Exception e) {
                UpdateBody updateBody = new UpdateBody(nickName.get(), sexField.get(), Integer.valueOf(ageField.get()));
                updateInformation(updateBody);
            }
        }
    }

    /**
     * description: 上传个人信息
     * author: Andy
     * date: 2019/9/17  19:37
     */
    private void updateInformation(UpdateBody updateBody) {
        IdeaApi.getApiService()
                .update(updateBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable1 -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity<RegisterEntity>>(this) {
                    @Override
                    public void onSuccess(SuccessEntity<RegisterEntity> response) {
                        //更新本地数据
                        ContentValues values = new ContentValues();
                        if (!IsNullUtil.getInstance().isEmpty(headUrl.get())) {
                            values.put("headUrl", headUrl.get());
                        }
                        values.put("name", nickName.get());
                        values.put("sex", sexField.get());
                        values.put("age", Integer.valueOf(ageField.get()));
                        LitePal.updateAll(RegisterEntity.class, values);
                        ZLog.d(LocalDataHelper.getInstance().getUserInfo());
                        ToastUtil.normalToast(context.get(), response.getMsg());
                    }
                });
    }


    /**
     * description: 打开相册
     * author: Andy
     * date: 2019/9/12 0012 9:46
     */
    private void openGallery() {
        PictureSelector.create((Activity) context.get())
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .enableCrop(true)// 是否裁剪 true or false
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
    }
}
