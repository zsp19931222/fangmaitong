package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/17 0017 10:13
 * email:zsp872126510@gmail.com
 */
public class LogoutBody {
   private long  time; //用户在线时间

    public LogoutBody(long time) {
        this.time = time;
    }
}
