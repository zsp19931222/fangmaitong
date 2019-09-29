package me.goldze.mvvmhabit.http.net.entity;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/9/29 0029 15:38
 * email:zsp872126510@gmail.com
 */
public class NewPlacardEntity extends BaseEntity{

    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
