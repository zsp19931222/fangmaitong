package me.goldze.mvvmhabit.http.net.entity;

/**
 * Created by Administrator on 2019/3/8 0008.
 */

public abstract class BaseEntity {
    private String code;
    private String message;
    private String otherMsg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOtherMsg() {
        return otherMsg;
    }

    public void setOtherMsg(String otherMsg) {
        this.otherMsg = otherMsg;
    }
}
