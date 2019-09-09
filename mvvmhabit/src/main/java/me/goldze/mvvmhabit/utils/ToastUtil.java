package me.goldze.mvvmhabit.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * 吐司
 * author：Andy on 2019/4/29 0029-10:17
 * email:zsp872126510@gmail.com
 */

public class ToastUtil {

    /**
     * 错误提示
     *
     * @param context  上下文
     * @param msg      提示信息
     * @param withIcon 是否显示icon
     * @author Andy
     * created at 2019/4/29 0029 10:23
     */
    public static void errorToast(Context context, String msg, boolean withIcon) {
        try {
            Toasty.error(context, msg, Toast.LENGTH_SHORT, withIcon).show();
        } catch (Exception e) {
            ZLog.e(e.toString());
        }
    }

    /**
     * 成功提示
     *
     * @param context  上下文
     * @param msg      提示信息
     * @param withIcon 是否显示icon
     * @author Andy
     * created at 2019/4/29 0029 10:23
     */
    public static void successToast(Context context, String msg, boolean withIcon) {
        try {
            Toasty.success(context, msg, Toast.LENGTH_SHORT, withIcon).show();
        } catch (Exception e) {
            ZLog.e(e.toString());
        }
    }

    /**
     * info提示
     *
     * @param context  上下文
     * @param msg      提示信息
     * @param withIcon 是否显示icon
     * @author Andy
     * created at 2019/4/29 0029 10:23
     */
    public static void infoToast(Context context, String msg, boolean withIcon) {
        try {

            Toasty.info(context, msg, Toast.LENGTH_SHORT, withIcon).show();
        } catch (
                Exception e) {
            ZLog.e(e.toString());
        }
    }

    /**
     * warning提示
     *
     * @param context  上下文
     * @param msg      提示信息
     * @param withIcon 是否显示icon
     * @author Andy
     * created at 2019/4/29 0029 10:23
     */
    public static void warningToast(Context context, String msg, boolean withIcon) {
        try {
            Toasty.warning(context, msg, Toast.LENGTH_SHORT, withIcon).show();
        } catch (Exception e) {
            ZLog.e(e.toString());
        }
    }

    /**
     * normal提示
     *
     * @param context 上下文
     * @param msg     提示信息
     * @author Andy
     * created at 2019/4/29 0029 10:23
     */
    public static void normalToast(Context context, String msg) {
        try {
            Toasty.normal(context, msg, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            ZLog.e(e.toString());
        }
    }

    /**
     * normal提示
     *
     * @param context  上下文
     * @param msg      提示信息
     * @param drawable 图片
     * @author Andy
     * created at 2019/4/29 0029 10:23
     */
    public static void normalToast(Context context, String msg, Drawable drawable) {
        try {
            Toasty.normal(context, msg, Toast.LENGTH_SHORT, drawable).show();
        } catch (Exception e) {
            ZLog.e(e.toString());
        }
    }

    /**
     * custom提示
     *
     * @param context  上下文
     * @param msg      提示信息
     * @param drawable 图片
     * @author Andy
     * created at 2019/4/29 0029 10:23
     */
    public static void customToast(Context context, int msg, Drawable drawable, int tintColor, int duration, boolean withIcon, boolean shouldTint) {
        try {
            Toasty.custom(context, msg, drawable, tintColor, duration, withIcon,
                    shouldTint).show();
        } catch (Exception e) {
            ZLog.e(e.toString());
        }
    }

}
