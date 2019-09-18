package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/18 0018 11:43
 * email:zsp872126510@gmail.com
 */
public class AuthQualificationBody {
    private String businessLicenseUrl ; // 营业执照
    private String remark ; // 备注
    private String createTime ; // 创建时间
    private String identityNegativeUrl ; // 联系人身份证国徽面
    private long id = -1 ; // 编号
    private String identityNum ; // 联系人身份证号码
    private String refuseReason ; // 拒绝理由
    private String realName ; // 联系人姓名
    private String phone ; // 手机号
    private String companyName ; // 提交的公司名称
    private int identity = -1 ; // 身份 1-总代 2-渠道代理 3-联合代理 4-经纪人
    private int del = -1 ; // 删除标志
    private long userId = -1 ; // 用户id 关联tab_tc_user的id
    private String name ; // 房脉通昵称
    private int state = -1 ; // 状态 0-未审核 1-已审核 2-已拒绝
    private String identityPositiveUrl ; // 联系人身份证人像面

    public AuthQualificationBody(String businessLicenseUrl, String remark, String createTime, String identityNegativeUrl, long id, String identityNum, String refuseReason, String realName, String phone, String companyName, int identity, int del, long userId, String name, int state, String identityPositiveUrl) {
        this.businessLicenseUrl = businessLicenseUrl;
        this.remark = remark;
        this.createTime = createTime;
        this.identityNegativeUrl = identityNegativeUrl;
        this.id = id;
        this.identityNum = identityNum;
        this.refuseReason = refuseReason;
        this.realName = realName;
        this.phone = phone;
        this.companyName = companyName;
        this.identity = identity;
        this.del = del;
        this.userId = userId;
        this.name = name;
        this.state = state;
        this.identityPositiveUrl = identityPositiveUrl;
    }
}
