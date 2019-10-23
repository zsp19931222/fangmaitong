package me.goldze.mvvmhabit.http.net.entity;

import java.util.List;

/**
 * description:
 * author:created by Andy on 2019/10/23 0023 14:34
 * email:zsp872126510@gmail.com
 */
public class UpLoadImageEntity extends BaseEntity{

    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
