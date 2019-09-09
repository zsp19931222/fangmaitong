package me.goldze.mvvmhabit.http.net;


import me.goldze.mvvmhabit.http.net.entity.BaseEntity;

/**
 * description:
 * author:created by Andy on 2019/7/9 0009 15:06
 * email:zsp872126510@gmail.com
 */
public interface ApiService<T extends BaseEntity> {
    //20122838  123456 测试账号
    int DEFAULT_TIMEOUT = 8 * 1000;
    String LOGIN_BASE_URL= "";
}
