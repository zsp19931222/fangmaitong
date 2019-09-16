package com.goldze.base.utils;

/**
 * description:生成随机数
 * author:created by Andy on 2019/7/9 0009 16:33
 * email:zsp872126510@gmail.com
 */
public class RandomUtil {
    private static class RandomHolder {
        private final static RandomUtil Instance = new RandomUtil();
    }

    public static RandomUtil getInstance() {
        return RandomHolder.Instance;
    }

    public RandomUtil() {
    }
    public  long generateRandomNumber(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("随机数位数必须大于0");
        }
        return (long) (Math.random() * 9 * Math.pow(10, n - 1)) + (long) Math.pow(10, n - 1);
    }
}
