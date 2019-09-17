package me.goldze.mvvmhabit.http.net.entity;

/**
 * description:
 * author:created by Andy on 2019/7/10 0010 17:05
 * email:zsp872126510@gmail.com
 */
public class SuccessEntity<T> extends BaseEntity {

    /**
     * content : null
     */

    private T data;

    public T getContent() {
        return data;
    }

    public void setContent(T data) {
        this.data = data;
    }
}
