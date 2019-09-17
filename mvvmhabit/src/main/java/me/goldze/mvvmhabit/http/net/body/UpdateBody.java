package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/17 0017 14:49
 * email:zsp872126510@gmail.com
 */
public class UpdateBody {
    private String url;
    private String name; // 房脉通昵称
    private int sex = -1; // 1-男、2-女、0-未知
    private int age = -1; // 年龄

    public UpdateBody(String name, int sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public UpdateBody(String url, String name, int sex, int age) {
        this.url = url;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
