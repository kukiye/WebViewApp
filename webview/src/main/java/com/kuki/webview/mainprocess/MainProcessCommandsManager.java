package com.kuki.webview.mainprocess;

import android.content.ComponentName;
import android.content.Intent;
import android.os.RemoteException;

import com.google.gson.Gson;
import com.kuki.base.BaseApplication;
import com.kuki.webview.ICallbackFromMainProcessToWebViewProcessAidlInterface;
import com.kuki.webview.IWebViewProcessToMainProcessAidlInterface;
import com.kuki.webview.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * author ：yeton
 * date : 2021/8/19 17:02
 * package：com.kuki.webview.mainprocess
 * description :实现AIDL接口，接收WebViewProcess传过来的命令
 */
public class MainProcessCommandsManager extends IWebViewProcessToMainProcessAidlInterface.Stub {

    private HashMap<String, Command> commandHashMap = new HashMap<>();

    private MainProcessCommandsManager() {
        //通过Command接口找到所有的子类，并且创建所有子类实例
        ServiceLoader<Command> commandServiceLoader = ServiceLoader.load(Command.class);
        for (Command command : commandServiceLoader) {
            if (!commandHashMap.containsKey(command.getName())) {
                commandHashMap.put(command.getName(), command);
            }
        }
    }

    private static class MainProcessCommandsHolder {
        private static MainProcessCommandsManager sInstance = new MainProcessCommandsManager();
    }

    public static MainProcessCommandsManager getInstance() {
        return MainProcessCommandsHolder.sInstance;
    }

     @Override
    public void handleWebCommand(String commandName, String jsonParams, ICallbackFromMainProcessToWebViewProcessAidlInterface callback) throws RemoteException {

        //第一种直接调用
        //        excuteCommand(commandName, );

        //第二种使用接口注册的方式实现
        commandHashMap.get(commandName).excute(new Gson().fromJson(jsonParams, Map.class), callback);

    }

    private void excuteCommand(String commandName, Map<String, String> paramMap) {
        if ("openPage".equalsIgnoreCase(commandName)) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.sApplication, paramMap.get("target_class")));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.sApplication.startActivity(intent);
        }
    }
}
