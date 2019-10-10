package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/10/10 0010 10:20
 * email:zsp872126510@gmail.com
 */
public class TcReviewsListBody {
    private int max=10;
    private int page = 1;
    private long entityId = -1 ; // 点评对象id

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }
}
