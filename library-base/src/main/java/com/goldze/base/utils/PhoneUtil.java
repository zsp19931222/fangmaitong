package com.goldze.base.utils;

/**
 * description:判断手机号以1开头，11位
 * author:created by Andy on 2019/11/8 0008 13:55
 * email:zsp872126510@gmail.com
 */
public class PhoneUtil {
    private static final PhoneUtil ourInstance = new PhoneUtil();

    public static PhoneUtil getInstance() {
        return ourInstance;
    }

    private PhoneUtil() {
    }

    public boolean checkPhone(String phone) {
        return phone.length() == 11 &&phone.startsWith("1");
    }
}
