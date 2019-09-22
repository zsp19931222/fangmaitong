package com.goldze.base.utils;

import java.util.Map;

import me.goldze.mvvmhabit.utils.ZLog;

/**
 * description:
 * author:created by Andy on 2019/9/22 16:19
 * email:zsp872126510@gmail.com
 */
public class ParameterLogUtil {
    private static final ParameterLogUtil ourInstance = new ParameterLogUtil();

    public static ParameterLogUtil getInstance() {
        return ourInstance;
    }

    private ParameterLogUtil() {
    }

    public void parameterLog(Map<String, Object> parameter) {
        for (Map.Entry<String, Object> entry : parameter.entrySet()) {
            ZLog.d(entry.getKey() + ": " + entry.getValue() + "\n");
        }
    }
}
