package com.kuki.app;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.kuki.base.BaseApplication;
import com.kuki.common.eventbus.LoginEvent;
import com.kuki.webview.ICallbackFromMainProcessToWebViewProcessAidlInterface;
import com.kuki.webview.command.Command;
import com.kuki.webview.webviewprocess.WebViewProcessCommandDispatcher;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Map;

/**
 * author ：yeton
 * date : 2021/8/19 18:09
 * package：com.kuki.webview.command
 * description :登录的命令
 */
@AutoService(Command.class)
public class CommandLogin implements Command {

    private String callbacknameFromNativeJs;
    private ICallbackFromMainProcessToWebViewProcessAidlInterface callback;

    public CommandLogin() {
        EventBus.getDefault().register(this);
    }

    @Override
    public String getName() {
        return "login";
    }

    @Override
    public void excute(Map parameters, ICallbackFromMainProcessToWebViewProcessAidlInterface callback) {

        Log.d("CommandLogin", new Gson().toJson(parameters));

        //1、去登录
        String target_class = (String) parameters.get("target_class");
        if (!TextUtils.isEmpty(target_class)) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.sApplication, target_class));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.sApplication.startActivity(intent);
        }

        this.callback = callback;
        this.callbacknameFromNativeJs = parameters.get("callbackname").toString();

    }

    //2、获取登录的结果--使用EventBus来回调
    @Subscribe
    public void onEventOn(LoginEvent loginEvent) {
        Log.d("CommandLogin", loginEvent.userName);

        //3、将登录的结果传给Html
        // 怎么传呢
        //需要WebView传给Html，但是拿不到WebView的实例，如果通过WebViewProcessCommandDispatcher来拿WebView，
        //需要在WebViewProcessCommandDispatcher中保存WebView，WebViewProcessCommandDispatcher是单例模式，会导致内存泄露
        //        WebViewProcessCommandDispatcher.getInstance().handleCallback
        //所以需要用接口回调的方式

        //在执行命令的时候传一个接口过来
        //这里是主进程需要给WebView的进程发送数据，所以需要用AIDL
        if (callback != null) {
            try {
                callback.onResult(callbacknameFromNativeJs, loginEvent.userName);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

}
