package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/17 0017 14:49
 * email:zsp872126510@gmail.com
 */
public class UpdateBody {
    private String name; // 房脉通昵称
    private int sex = -1; // 1-男、2-女、0-未知
    private int age = -1; // 年龄

    public UpdateBody( String name, int sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}
