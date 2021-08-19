package com.kuki.webview.mainprocess;

import android.content.ComponentName;
import android.content.Intent;

import com.google.gson.Gson;
import com.kuki.base.BaseApplication;
import com.kuki.webview.IWebViewProcessToMainProcess;

import java.util.Map;

/**
 * author ：yeton
 * date : 2021/8/19 17:02
 * package：com.kuki.webview.mainprocess
 * description :实现AIDL接口，接收WebViewProcess传过来的命令
 */
public class MainProcessCommandsManager extends IWebViewProcessToMainProcess.Stub {

    private MainProcessCommandsManager() {
    }

    private static class MainProcessCommandsHolder {
        private static MainProcessCommandsManager sInstance = new MainProcessCommandsManager();
    }

    public static MainProcessCommandsManager getInstance() {
        return MainProcessCommandsHolder.sInstance;
    }

    @Override
    public void handleWebCommand(String commandName, String jsonParams) {

        Map<String, String> paramMap = new Gson().fromJson(jsonParams, Map.class);
        if ("openPage".equalsIgnoreCase(commandName)) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.sApplication, paramMap.get("target_class")));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.sApplication.startActivity(intent);
        }

    }
}
