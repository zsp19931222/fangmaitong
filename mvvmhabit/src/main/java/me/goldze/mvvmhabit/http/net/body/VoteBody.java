package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/18 22:05
 * email:zsp872126510@gmail.com
 */
public class VoteBody {
    private long entityId;// 动态id
    private int type;// 点赞类型 1-资讯 2-动态
    public static final int INFORMATION=1;//资讯
    public static final int STATE=2;//动态
    public VoteBody(long entityId, int type) {
        this.entityId = entityId;
        this.type = type;
    }
}
