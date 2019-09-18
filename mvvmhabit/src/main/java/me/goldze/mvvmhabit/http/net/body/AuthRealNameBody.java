package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/18 0018 11:41
 * email:zsp872126510@gmail.com
 */
public class AuthRealNameBody {
    private String remark ; // 备注
    private String createTime ; // 创建时间
    private String identityNegativeUrl ; // 身份证国徽面
    private long id = -1 ; // 编号
    private String identityNum ; // 身份证号码
    private String realName ; // 姓名
    private String phone ; // 手机号
    private int identity = -1 ; // 身份 1-总代 2-渠道代理 3-联合代理 4-经纪人
    private int del = -1 ; // 删除标志
    private long userId = -1 ; // 用户id 关联tab_tc_user的id
    private String name ; // 房脉通昵称
    private int state = -1 ; // 状态 0-未审核 1-已审核 2-已拒绝
    private String identityPositiveUrl ; // 身份证人像面

    public AuthRealNameBody(String remark, String createTime, String identityNegativeUrl, long id, String identityNum, String realName, String phone, int identity, int del, long userId, String name, int state, String identityPositiveUrl) {
        this.remark = remark;
        this.createTime = createTime;
        this.identityNegativeUrl = identityNegativeUrl;
        this.id = id;
        this.identityNum = identityNum;
        this.realName = realName;
        this.phone = phone;
        this.identity = identity;
        this.del = del;
        this.userId = userId;
        this.name = name;
        this.state = state;
        this.identityPositiveUrl = identityPositiveUrl;
    }
}
