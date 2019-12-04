package me.goldze.mvvmhabit.http.net.body;

/**
 * description:
 * author:created by Andy on 2019/9/30 00:22
 * email:zsp872126510@gmail.com
 */
public class RecommendFriendBody {
    private int page;
    private int max;
    private String phone, name;
    private int sex; //不限就不传，男传1  女传0
    private int areaId;
    private int identity = -1; // 身份 1-总代 2-渠道代理 3-联合代理 4-经纪人
    private int minRate=0;//最小登录率
    private int maxRate=100;//最大登录率

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getMinRate() {
        return minRate;
    }

    public void setMinRate(int minRate) {
        this.minRate = minRate;
    }

    public int getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(int maxRate) {
        this.maxRate = maxRate;
    }
}
