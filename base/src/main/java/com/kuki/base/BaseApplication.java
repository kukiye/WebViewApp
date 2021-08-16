package com.kuki.base;

import android.app.Application;

/**
 * author ：yeton
 * date : 2021/8/16 17:54
 * package：com.kuki.base.autoservice
 * description :
 */
public class BaseApplication extends Application {

    public static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
