package com.goldze.base.eventbus;

/**
 * description:
 * author:created by Andy on 2019/9/23 21:45
 * email:zsp872126510@gmail.com
 */
public class PriceRxBusBean {
    private String max;//最大价格
    private String min;//最低

    public PriceRxBusBean(String max, String min) {
        this.max = max;
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }
}
