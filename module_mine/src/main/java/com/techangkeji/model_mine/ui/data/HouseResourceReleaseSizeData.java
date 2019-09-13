package com.techangkeji.model_mine.ui.data;

import com.techangkeji.model_mine.ui.bean.HouseResourceReleaseSizeBean;

import java.util.ArrayList;
import java.util.List;

public class HouseResourceReleaseSizeData {
    private static final HouseResourceReleaseSizeData ourInstance = new HouseResourceReleaseSizeData();

    public static HouseResourceReleaseSizeData getInstance() {
        return ourInstance;
    }

    private List<HouseResourceReleaseSizeBean> list=new ArrayList<>();
    private HouseResourceReleaseSizeData() {
    }
    public List<HouseResourceReleaseSizeBean> getList(){
        return list;
    }
}
