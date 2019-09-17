package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/17 20:51
 * email:zsp872126510@gmail.com
 */
public class MyMovingListBody {
    private int offset;
    private int max;
    private int page;

    public MyMovingListBody(int max, int page) {
        this.max = max;
        this.page = page;
    }
}
