package com.techangkeji.model_message;

import android.app.Application;

import com.goldze.base.base.IModuleInit;

/**
 * description:
 * author:created by Andy on 2019/9/10 0010 10:16
 * email:zsp872126510@gmail.com
 */
public class MessageModuleInit implements IModuleInit {
    public static String currentUserNick = "";

    @Override
    public boolean onInitAhead(Application application) {
        return false;
    }

    @Override
    public boolean onInitLow(Application application) {
        return false;
    }
}
