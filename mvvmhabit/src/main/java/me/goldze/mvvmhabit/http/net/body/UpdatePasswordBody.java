package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/18 0018 09:29
 * email:zsp872126510@gmail.com
 */
public class UpdatePasswordBody {
//    phone	是	string	手机号
//    oldPassword	是	string	原始密码
//    password	是	string	密码
//    code	是	string	验证码
//    type	是	int	类型1-密码 2-手机号
    private String phone;
    private String oldPassword;
    private String password;
    private String code;
    private int type;

    public UpdatePasswordBody(String phone, String oldPassword, String password, String code, int type) {
        this.phone = phone;
        this.oldPassword = oldPassword;
        this.password = password;
        this.code = code;
        this.type = type;
    }
}
