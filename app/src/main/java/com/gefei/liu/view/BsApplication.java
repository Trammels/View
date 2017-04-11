package com.gefei.liu.view;

import android.app.Application;
import android.content.Context;
import android.view.Display;

import com.orhanobut.logger.Logger;

/**
 * 作者：Created by gefei on 2017/1/10:16:40.
 * 邮箱：2460932861@qq.com
 */

public class BsApplication extends Application {
    public static Context context;
    public static Display display;
    private static final String TAG = "BsApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Logger.init(TAG);


    }
    public static Context getContext() {
        return context;
    }
}
