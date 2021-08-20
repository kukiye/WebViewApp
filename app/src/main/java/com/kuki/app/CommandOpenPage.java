package com.kuki.app;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;

import com.google.auto.service.AutoService;
import com.kuki.base.BaseApplication;
import com.kuki.webview.ICallbackFromMainProcessToWebViewProcessAidlInterface;
import com.kuki.webview.command.Command;

import java.util.Map;

/**
 * author ：yeton
 * date : 2021/8/19 17:47
 * package：com.kuki.webview.command
 * description :打开原生界面的命令
 */
//使用AutoService来找到该类，不加注解就找不到该类了
@AutoService(Command.class)
public class CommandOpenPage implements Command {

    @Override
    public String getName() {
        return "openPage";
    }

    @Override
    public void excute(Map params, ICallbackFromMainProcessToWebViewProcessAidlInterface callback) {

        String target_class = (String) params.get("target_class");
        if (!TextUtils.isEmpty(target_class)) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.sApplication, target_class));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.sApplication.startActivity(intent);
        }
    }
}
