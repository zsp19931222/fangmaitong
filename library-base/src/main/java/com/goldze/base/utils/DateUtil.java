package com.goldze.base.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description:日期 时间工具
 * author:created by Andy on 2019/8/13 0013 11:29
 * email:zsp872126510@gmail.com
 */
@SuppressLint("SimpleDateFormat")
public class DateUtil {
    private static final DateUtil ourInstance = new DateUtil();

    public static DateUtil getInstance() {
        return ourInstance;
    }

    private DateUtil() {
    }

    public String getData(String time) {
        try {
            //注意format的格式要与日期String的格式相匹配
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(time);
            return showDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 2016-11-08 14:39:38
     * pattern yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    private String showDate(Date date) {
        String dateStr = format(date);
        String year = dateStr.substring(0, 4);
        Long yearNum = Long.parseLong(year);
        int month = Integer.parseInt(dateStr.substring(5, 7));
        int day = Integer.parseInt(dateStr.substring(8, 10));
        String hour = dateStr.substring(11, 13);
        String minute = dateStr.substring(14, 16);

        long addtime = date.getTime();
        long today = System.currentTimeMillis();//当前时间的毫秒数
        Date now = new Date(today);
        String nowStr = format(now);
        int nowDay = Integer.parseInt(nowStr.substring(8, 10));
        String result;
        long l = today - addtime;//当前时间与给定时间差的毫秒数
        long days = l / (24 * 60 * 60 * 1000);//这个时间相差的天数整数，大于1天为前天的时间了，小于24小时则为昨天和今天的时间
        long hours = (l / (60 * 60 * 1000) - days * 24);//这个时间相差的减去天数的小时数
        long min = ((l / (60 * 1000)) - days * 24 * 60 - hours * 60);//
        long s = (l / 1000 - days * 24 * 60 * 60 - hours * 60 * 60 - min * 60);
        if (days > 0) {
            if (days < 2) {
                result = "昨天 " + hour + ":" + minute;
            } else {
                result = dateStr.substring(5, 7) + "-" + dateStr.substring(8, 10) + " " + hour + ":" + minute;
            }
        } else if (hours > 0) {
            if (day < nowDay) {
                result = "昨天 " + hour + ":" + minute;
            } else {
                result = "今天 " + hour + ":" + minute;
            }
        } else if (min > 0) {
            result = min + "分钟前";
        } else {
            return "刚刚";
        }
        return result;
    }

    /**
     * description:获取天
     * author: Andy
     * date: 2019/9/17  22:35
     */
    public String getDay(String time) {
        try {
            //注意format的格式要与日期String的格式相匹配
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(time);
            String dateStr = format(date);
            return dateStr.substring(8, 10);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * description: 获取月份
     * author: Andy
     * date: 2019/9/17  22:36
     */
    public String getMonth(String time) {
        try {
            //注意format的格式要与日期String的格式相匹配
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(time);
            String dateStr = format(date);
            return dateStr.substring(5, 7);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 日期格式化
     *
     * @param date 需要格式化的日期
     * @return 返回格式化后的时间字符串
     */
    private String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}

