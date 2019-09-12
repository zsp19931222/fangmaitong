package com.goldze.base.utils.glide;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import me.goldze.mvvmhabit.utils.ZLog;


/**
 * description:Glide 加载 简单判空封装 防止异步加载数据时调用Glide 抛出异常
 * author:created by Andy on 2019/6/20 0020 15:33
 * email:zsp872126510@gmail.com
 */
public class GlideLoadUtils implements GlideLoadInterface {
    public static final int BORDER_RADIUS = 45;
    public static final int NORMAL = 0;

    /**
     * 借助内部类 实现线程安全的单例模式
     * 属于懒汉式单例，因为Java机制规定，内部类SingletonHolder只有在getInstance()
     * 方法第一次调用的时候才会被加载（实现了lazy），而且其加载过程是线程安全的。
     * 内部类加载的时候实例化一次instance。
     */

    //判断Activity是否Destroy
    public static boolean isDestroy(Activity activity) {
        return activity == null || activity.isFinishing() || activity.isDestroyed();
    }

    private static class GlideLoadUtilsHolder {
        private final static GlideLoadUtils INSTANCE = new GlideLoadUtils();
    }

    public static GlideLoadUtils getInstance() {
        return GlideLoadUtilsHolder.INSTANCE;
    }

    public GlideLoadUtils() {
    }

    /**
     * @param context
     * @param url           加载图片的url地址  String
     * @param imageView     加载图片的ImageView 控件
     * @param default_image 图片展示错误的本地图片 id
     * @param radius        圆角（0-无圆角，45-圆图片，其他值-相对应的圆角图片）
     * @description:Glide 加载 简单判空封装 防止异步加载数据时调用Glide 抛出异常
     * @author:Andy
     * @date:2019/6/20 0020 15:41
     */
    @Override
    public void glideLoad(Context context, Object url, ImageView imageView, int default_image, int radius) {
        try {
            if (context != null) {
                if (!isDestroy((Activity) context)) {
                    Glide.with(imageView.getContext()).load(url)
                            .apply(new RequestOptions()
//                                .fitCenter()
//                                .centerCrop()
                                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                                    .placeholder(default_image)
                                    .error(default_image).centerCrop()
                                    .transform(new GlideRoundTransform(imageView.getContext(), radius)))
                            .thumbnail(loadTransform(imageView.getContext(), default_image, radius))
                            .into(imageView);
                }
            } else {
                ZLog.i("Picture loading failed,context is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * description: 使用RadiusImageView在布局里面设置圆角
     * author: Andy
     * date: 2019/8/1 0001 10:47
     */
    public void glideLoad(Context context, Object url, ImageView imageView, int default_image) {
        try {

            if (context != null) {
                if (!isDestroy((Activity) context)) {
                    Glide.with(imageView.getContext()).load(url)
                            .apply(new RequestOptions()
                                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                                    .placeholder(default_image)
                                    .error(default_image).centerCrop()
                            )
                            .into(imageView);
                }
            } else {
                ZLog.i("Picture loading failed,context is null");
            }
        } catch (Exception e) {

        }
    }

    private static RequestBuilder<Drawable> loadTransform(Context context, @DrawableRes int placeholderId, float radius) {

        return Glide.with(context)
                .load(placeholderId)
                .apply(new RequestOptions().centerCrop()
                        .transform(new GlideRoundTransform(context, radius)));

    }
}
