package me.goldze.mvvmhabit.utils;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;

import androidx.collection.LongSparseArray;
import androidx.collection.SimpleArrayMap;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * description:
 * author:created by Andy on 2019/8/7 0007 10:40
 * email:zsp872126510@gmail.com
 */
public class IsNullUtil {
    private static final IsNullUtil ourInstance = new IsNullUtil();

    public static IsNullUtil getInstance() {
        return ourInstance;
    }

    private IsNullUtil() {
    }

    /**
     * description: 为空判断
     * author: Andy
     * date: 2019/8/7 0007 10:41
     */
    public boolean isEmpty(final Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof CharSequence && obj.toString().length() == 0) {
            return true;
        }
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof SimpleArrayMap && ((SimpleArrayMap) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof SparseArray && ((SparseArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseBooleanArray && ((SparseBooleanArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseIntArray && ((SparseIntArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseLongArray && ((SparseLongArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof LongSparseArray && ((LongSparseArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof android.util.LongSparseArray
                && ((android.util.LongSparseArray) obj).size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * description: 数据是否返回为“null”
     * author: Andy
     * date: 2019/8/7 0007 10:41
     */
    public String StringNull(String s) {
        if ("null".equals(s)||isEmpty(s)) {
            return "";
        }
        return s;
    }
}
