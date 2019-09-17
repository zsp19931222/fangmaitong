package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/17 0017 15:07
 * email:zsp872126510@gmail.com
 */
public class LoginBody {
    String password;
    String phone;
    int  type;
    public LoginBody(String password, String phone, int type) {
        this.password = password;
        this.phone = phone;
        this.type = type;
    }
}
