package com.goldze.base.utils.glide;

import android.content.Context;

import com.bumptech.glide.Glide;

import java.io.File;
import java.text.DecimalFormat;

/**
 * description:
 * author:created by Andy on 2019/8/12 0012 11:24
 * email:zsp872126510@gmail.com
 */
public class GlideCacheUtil {
    public static long getDirSize(File dir) {
        try {
            if (dir == null) {
                return 0;
            }
            if (!dir.isDirectory()) {
                return 0;
            }
            long dirSize = 0;
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    dirSize += file.length();
                } else if (file.isDirectory()) {
                    dirSize += file.length();
                    dirSize += getDirSize(file); // 递归调用继续统计
                }
            }
            return dirSize;
        } catch (Exception e) {
            return 0;
        }

    }

    private static final double GB = 1024L * 1024L * 1024L;// 定义GB的计算常量
    private static final double MB = 1024L * 1024L;// 定义MB的计算常量
    private static final double KB = 1024L;// 定义KB的计算常量

    public static String byteConversionGBMBKB(double kSize) {
        DecimalFormat df = new DecimalFormat("#.00");
        double temp = 0;
        if (kSize / GB >= 1) {
            temp = kSize / GB;
            return df.format(temp) + "GB";
        } else if (kSize / MB >= 1) {
            temp = kSize / MB;
            return df.format(temp) + "MB";
        } else if (kSize / KB >= 1) {
            temp = kSize / KB;
            return df.format(temp) + "KB";
        }
        return kSize + "B";
    }

    /**
     * description: 清空图片缓存
     * author: Andy
     * date: 2019/8/21 0021 17:22
     */
    public static void clearCache(Context context) {
        new Thread(() -> Glide.get(context).clearDiskCache()).start();
    }

}
