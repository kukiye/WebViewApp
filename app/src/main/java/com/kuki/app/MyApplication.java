package com.kuki.app;

import com.kingja.loadsir.core.LoadSir;
import com.kuki.base.BaseApplication;
import com.kuki.base.loadsir.CustomCallback;
import com.kuki.base.loadsir.EmptyCallback;
import com.kuki.base.loadsir.ErrorCallback;
import com.kuki.base.loadsir.LoadingCallback;
import com.kuki.base.loadsir.TimeoutCallback;

/**
 * author ：yeton
 * date : 2021/8/16 17:56
 * package：com.kuki.app
 * description :
 */
public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化LoadSir
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();

    }
}
