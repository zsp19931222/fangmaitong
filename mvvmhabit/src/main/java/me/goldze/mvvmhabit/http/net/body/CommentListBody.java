package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/25 23:08
 * email:zsp872126510@gmail.com
 */
public class CommentListBody {
    private int page;
    private int max;
    private int entityType;// 评论类型 1-资讯 2-动态 3-房源
    private long entityId;

    public CommentListBody(int page, int max, int entityType, long entityId) {
        this.page = page;
        this.max = max;
        this.entityType = entityType;
        this.entityId = entityId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getEntityType() {
        return entityType;
    }

    public void setEntityType(int entityType) {
        this.entityType = entityType;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }
}
