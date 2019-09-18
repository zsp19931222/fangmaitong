package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/18 0018 11:41
 * email:zsp872126510@gmail.com
 */
public class AuthBrokerBody {
    private String remark ; // 备注
    private String createTime ; // 创建时间
    private long id = -1 ; // 编号
    private String refuseReason ; // 拒绝理由
    private String phone ; // 手机号
    private String companyName ; // 提交的公司名称
    private int identity = -1 ; // 身份 1-总代 2-渠道代理 3-联合代理 4-经纪人
    private String badgeUrl ; // 胸牌
    private int del = -1 ; // 删除标志
    private long userId = -1 ; // 用户id 关联tab_tc_user的id
    private String nameplateUrl ; // 名牌
    private String companyAddress ; // 提交的地址
    private String name ; // 房脉通昵称
    private int state = -1 ; // 状态 0-未审核 1-已审核 2-已拒绝

    public AuthBrokerBody(String remark, String createTime, long id, String refuseReason, String phone, String companyName, int identity, String badgeUrl, int del, long userId, String nameplateUrl, String companyAddress, String name, int state) {
        this.remark = remark;
        this.createTime = createTime;
        this.id = id;
        this.refuseReason = refuseReason;
        this.phone = phone;
        this.companyName = companyName;
        this.identity = identity;
        this.badgeUrl = badgeUrl;
        this.del = del;
        this.userId = userId;
        this.nameplateUrl = nameplateUrl;
        this.companyAddress = companyAddress;
        this.name = name;
        this.state = state;
    }
}
