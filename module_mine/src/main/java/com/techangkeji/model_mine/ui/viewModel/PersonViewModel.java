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

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtil;

import static com.goldze.base.constant.RxBusMessageEventConstants.OPEN_GALLERY;

public class PersonViewModel extends BaseViewModel {
    public ObservableField<Context> context = new ObservableField<>();
    public ObservableField<String> sexField = new ObservableField<>("");
    public ObservableField<String> ageField = new ObservableField<>("");

    public PersonViewModel(@NonNull Application application) {
        super(application);
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
        sexPopupwindow.setOnSelectSexListener(sex -> sexField.set(sex));
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
}
