package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/20 15:12
 * email:zsp872126510@gmail.com
 */
public class AppReportBody {
    private long id = -1 ; // 编号
    private String remark ; // 备注
    private String content ; // 举报内容
    private String listingName ; // 房源名
    private String createTime ; // 创建时间
    private int del = -1 ; // 删除标志
    private long userId = -1 ; // 用户id
    private long listingId = -1 ; // 房源id
    private int state = -1 ; // 状态
//    private List<TcComment> commentList;//评论列表

    public AppReportBody(long id, String remark, String content, String listingName, String createTime, int del, long userId, long listingId, int state) {
        this.id = id;
        this.remark = remark;
        this.content = content;
        this.listingName = listingName;
        this.createTime = createTime;
        this.del = del;
        this.userId = userId;
        this.listingId = listingId;
        this.state = state;
    }
}
