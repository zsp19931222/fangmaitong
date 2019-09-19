package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/19 22:25
 * email:zsp872126510@gmail.com
 */
public class AddFriendBody {
    private String otherPhone ;// 好友的手机号

    public AddFriendBody(String otherPhone) {
        this.otherPhone = otherPhone;
    }
}
