package com.kuki.webview.mainprocess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * author ：yeton
 * date : 2021/8/19 16:59
 * package：com.kuki.webview.mainprocess
 * description :
 */
public class MainProcessService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return MainProcessCommandsManager.getInstance();
    }
}
