package com.techangkeji.model_mine.ui.viewModel;

import android.Manifest;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.fragment.app.FragmentActivity;

import com.goldze.base.utils.PermissionsUtils;
import com.techangkeji.model_mine.ui.activity.ChangeNickNameActivity;
import com.techangkeji.model_mine.ui.popup.SexPopupwindow;
import com.techangkeji.model_mine.ui.popup.TimePickerPopupwindow;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.net.DefaultObserver;
import me.goldze.mvvmhabit.http.net.IdeaApi;
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

public class PersonViewModel extends BaseViewModel {
    public ObservableField<Context> context = new ObservableField<>();
    public ObservableField<Integer> sexField = new ObservableField<>();
    public ObservableField<String> sexStrField = new ObservableField<>("");
    public ObservableField<String> ageField = new ObservableField<>();
    public ObservableField<Object> headUrl = new ObservableField<>();
    public ObservableField<String> nickName = new ObservableField<>("");

    public PersonViewModel(@NonNull Application application) {
        super(application);
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
    }


    public BindingCommand changeNameCommand = new BindingCommand(() -> startActivity(ChangeNickNameActivity.class));

    /**
     * description: 选择头像
     * author: Andy
     * date: 2019/9/16  21:00
     */
    public BindingCommand choiceHeadImage = new BindingCommand(() ->
            PermissionsUtils.getInstance().getPermissionsWithFragmentActivity((FragmentActivity) context.get(), OPEN_GALLERY, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
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
        UpdateBody updateBody = new UpdateBody(nickName.get(), sexField.get(), Integer.valueOf(ageField.get()));
        IdeaApi.getApiService()
                .update(updateBody)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable1 -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity<RegisterEntity>>(this) {
                    @Override
                    public void onSuccess(SuccessEntity<RegisterEntity> response) {
                        ToastUtil.normalToast(context.get(), response.getMsg());
                    }
                });
    });

    /**
    * description: 上传头像
    * author: Andy
    * date: 2019/9/17 0017 17:14
    */
    public void uploadpic(){
        ZLog.d(headUrl.get());
        if (IsNullUtil.getInstance().isEmpty(headUrl.get()))return;
        File file = new File((String) headUrl.get());
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);//表单类型
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);//表单类型
        builder.addFormDataPart("file", file.getName(), body); //添加图片数据，body创建的请求体
        List<MultipartBody.Part> parts = builder.build().parts();
        IdeaApi.getApiService()
                .uploadpic(parts)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .doOnSubscribe(disposable1 -> showDialog())
                .subscribe(new DefaultObserver<SuccessEntity>(this) {
                    @Override
                    public void onSuccess(SuccessEntity response) {
                    }
                });
    }
}
