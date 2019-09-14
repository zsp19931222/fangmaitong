package com.goldze.base.utils;

import java.util.ArrayList;
import java.util.List;

public  class SimulationData {
    public static List<String> simulation(){
        List<String> strings=new ArrayList<>();
        for (int i = 0; i <20 ; i++) {
            strings.add("");
        }
        return strings;
    }
}
