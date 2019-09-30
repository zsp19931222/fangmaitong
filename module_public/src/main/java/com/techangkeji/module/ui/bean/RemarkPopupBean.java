package com.techangkeji.module.ui.bean;

/**
 * description:
 * author:created by Andy on 2019/9/30 0030 09:56
 * email:zsp872126510@gmail.com
 */
public class RemarkPopupBean {
    private String name;
    private int score;

    public RemarkPopupBean(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
