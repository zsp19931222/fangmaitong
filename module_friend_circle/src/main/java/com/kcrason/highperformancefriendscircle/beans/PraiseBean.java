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
//    private int entityType;
//    private long entityId;

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
//
//    public int getEntityType() {
//        return entityType;
//    }
//
//    public void setEntityType(int entityType) {
//        this.entityType = entityType;
//    }
//
//    public long getEntityId() {
//        return entityId;
//    }
//
//    public void setEntityId(long entityId) {
//        this.entityId = entityId;
//    }
}
