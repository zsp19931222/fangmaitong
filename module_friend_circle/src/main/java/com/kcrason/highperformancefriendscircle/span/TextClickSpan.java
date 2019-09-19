package com.kcrason.highperformancefriendscircle.span;

import android.content.Context;
import android.graphics.Color;

import androidx.core.content.ContextCompat;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Toast;

import com.techangkeji.module_friend_circle.R;

import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.http.net.body.CommentBody;

/**
 * @author KCrason
 * @date 2018/4/28
 */
public class TextClickSpan extends ClickableSpan {

    private Context mContext;

    private String mUserName;
    private long mUserId;
    private boolean mPressed;
    private int entityType;
    private long entityId;

    public TextClickSpan(Context context, String userName, long mUserId,int entityType,long entityId) {
        this.mContext = context;
        this.mUserName = userName;
        this.mUserId = mUserId;
        this.entityType=entityType;
        this.entityId=entityId;
    }
    public TextClickSpan(Context context, String userName, long mUserId) {
        this.mContext = context;
        this.mUserName = userName;
        this.mUserId = mUserId;
    }

    public void setPressed(boolean isPressed) {
        this.mPressed = isPressed;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.bgColor = mPressed ? ContextCompat.getColor(mContext, R.color.base_B5B5B5) : Color.TRANSPARENT;
        ds.setColor(ContextCompat.getColor(mContext, R.color.base_697A9F));
        ds.setUnderlineText(false);
    }

    @Override
    public void onClick(View widget) {
//        Toast.makeText(mContext, mUserId + "You Click " + mUserName+"-entityType:"+entityType+"-entityId:"+entityId, Toast.LENGTH_SHORT).show();
        RxBus.getDefault().post(new TextClickSpanBean(mUserName,mUserId,entityType,entityId));
    }

    public class TextClickSpanBean{
        private String mUserName;
        private long mUserId;
        private int entityType;
        private long entityId;

        public TextClickSpanBean(String mUserName, long mUserId, int entityType, long entityId) {
            this.mUserName = mUserName;
            this.mUserId = mUserId;
            this.entityType = entityType;
            this.entityId = entityId;
        }

        public String getmUserName() {
            return mUserName;
        }

        public void setmUserName(String mUserName) {
            this.mUserName = mUserName;
        }

        public long getmUserId() {
            return mUserId;
        }

        public void setmUserId(long mUserId) {
            this.mUserId = mUserId;
        }

        public int getEntityType() {
            return entityType;
        }

        public void setEntityType(int entityType) {
            this.entityType = entityType;
        }

        public long getEntityId() {
            return entityId;
        }

        public void setEntityId(long entityId) {
            this.entityId = entityId;
        }
    }
}
