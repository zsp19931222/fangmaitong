package com.techangkeji.model_mine.ui.popup;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.techangkeji.model_mine.R;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import me.goldze.mvvmhabit.utils.ZLog;
import razerdp.basepopup.BasePopupWindow;
import top.defaults.view.DateTimePickerView;

/**
 * description:
 * author:created by Andy on 2019/9/12 0012 16:29
 * email:zsp872126510@gmail.com
 */
public class TimePickerPopupwindow extends BasePopupWindow {
    private String dateString;

    public TimePickerPopupwindow(Context context) {
        super(context);
        DateTimePickerView timePickerView = findViewById(R.id.datePicker);
        TextView tv_cancel = findViewById(R.id.tv_cancel);
        TextView tv_confirm = findViewById(R.id.tv_confirm);
        timePickerView.setStartDate(Calendar.getInstance());
        // 注意：月份是从0开始计数的
        timePickerView.setSelectedDate(new GregorianCalendar(1993, 0, 1));
        timePickerView.setOnSelectedDateChangedListener(date -> {
            int year = date.get(Calendar.YEAR);
            int month = date.get(Calendar.MONTH);
            int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
            dateString = String.format(Locale.getDefault(), "%d-%02d-%02d", year, month + 1, dayOfMonth);
            ZLog.d(dateString);
        });
        tv_cancel.setOnClickListener(view -> dismiss());
        tv_confirm.setOnClickListener(view -> {
            onSelectTimeListener.time(dateString);
            dismiss();
        });
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_time_picker);
    }

    public interface OnSelectTimeListener {
        void time(String time);
    }

    public void setOnSelectTimeListener(OnSelectTimeListener onSelectTimeListener) {
        this.onSelectTimeListener = onSelectTimeListener;
    }

    public OnSelectTimeListener onSelectTimeListener;

}
