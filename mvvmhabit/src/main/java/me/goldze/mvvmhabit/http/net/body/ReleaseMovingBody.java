package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/17 0017 17:06
 * email:zsp872126510@gmail.com
 */
public class ReleaseMovingBody {
    private long id = -1 ; // 编号
    private String remark ; // 备注
    private String imgsUrl ; // 图片地址 多张图片地址用逗号拼接
    private String content ; // 内容
    private int onlyFriend = -1 ; // 仅好友可见 1-仅好友可见 0-所有人可见
    private String createTime ; // 创建时间
    private int del = -1 ; // 删除标志
    private long userId = -1 ; // 用户编号 关联tab_tc_user的id
    private int commnetNum = -1 ; // 评论数
    private int state = -1 ; // 状态 1-未审核 2-审核已通过 3-审核未通过
    private int voteNum = -1 ; // 点赞数

    public ReleaseMovingBody(String imgsUrl, String content, int onlyFriend) {
        this.imgsUrl = imgsUrl;
        this.content = content;
        this.onlyFriend = onlyFriend;
    }
}
