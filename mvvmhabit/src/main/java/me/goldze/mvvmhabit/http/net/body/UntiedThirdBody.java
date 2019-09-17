package me.goldze.mvvmhabit.http.net.body;

/**
 * description:解绑
 * author:created by Andy on 2019/9/17 0017 10:18
 * email:zsp872126510@gmail.com
 */
public class UntiedThirdBody {
   private int type; //3-微信 4-qq

    public UntiedThirdBody(int type) {
        this.type = type;
    }
}
