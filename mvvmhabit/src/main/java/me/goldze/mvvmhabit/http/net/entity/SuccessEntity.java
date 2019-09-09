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

    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
