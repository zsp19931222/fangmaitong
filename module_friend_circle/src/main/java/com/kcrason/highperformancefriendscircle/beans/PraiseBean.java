package com.kcrason.highperformancefriendscircle.beans;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;

/**
 * 点赞bean
 */
public class PraiseBean {
    private String praiseUserName;
    private int praiseUserId;

    public String getPraiseUserName() {
        return praiseUserName;
    }

    public void setPraiseUserName(String praiseUserName) {
        this.praiseUserName = praiseUserName;
    }

    public int getPraiseUserId() {
        return praiseUserId;
    }

    public void setPraiseUserId(int praiseUserId) {
        this.praiseUserId = praiseUserId;
    }
}
