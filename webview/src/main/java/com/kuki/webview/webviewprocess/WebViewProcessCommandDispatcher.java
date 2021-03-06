package com.kuki.webview.webviewprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.kuki.base.BaseApplication;
import com.kuki.webview.ICallbackFromMainProcessToWebViewProcessAidlInterface;
import com.kuki.webview.IWebViewProcessToMainProcessAidlInterface;
import com.kuki.webview.mainprocess.MainProcessService;

/**
 * author ：yeton
 * date : 2021/8/19 16:25
 * package：com.kuki.webview.webviewprocess
 * description :网页命令的分发器
 */
public class WebViewProcessCommandDispatcher implements ServiceConnection {

    public static final String TAG = "WebCommandDispatcher";

    private IWebViewProcessToMainProcessAidlInterface iWebViewProcessToMainProcess;

    private WebViewProcessCommandDispatcher() {
    }


    private static class WebViewProcessCommandDispatcherHolder {
        private static WebViewProcessCommandDispatcher sInstance = new WebViewProcessCommandDispatcher();
    }

    public static WebViewProcessCommandDispatcher getInstance() {
        return WebViewProcessCommandDispatcherHolder.sInstance;
    }


    /**
     * 通过绑定服务实现WebViewProcess和MainProcess来通讯
     */
    public void initAidlConnection() {
        Intent intent = new Intent(BaseApplication.sApplication, MainProcessService.class);
        BaseApplication.sApplication.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        iWebViewProcessToMainProcess = IWebViewProcessToMainProcessAidlInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

        //断开后重新连接
        iWebViewProcessToMainProcess = null;
        initAidlConnection();
    }

    @Override
    public void onBindingDied(ComponentName name) {
        //断开后重新连接
        iWebViewProcessToMainProcess = null;
        initAidlConnection();
    }


    public void excuteCommand(String commandName, String jsonParam, BaseWebView baseWebView) {


   /*     if ("showToast".equalsIgnoreCase(commandName)) {
            // Map<String, String> paramMap = new Gson().fromJson(jsonParam, Map.class);
            //            Toast.makeText(context, paramMap.get("message"), Toast.LENGTH_LONG).show();

            //不能这样用，因为跨进程了
            MainProcessCommandsManager.getInstance().handleWebCommand(commandName, jsonParam);

        } else if ("openPage".equalsIgnoreCase(commandName)) {


            //同一个App 的不同进程，这种方式在OPPO手机上可以跳转。--但不规范
            // 如果是不同的App就不行了
            *//*Intent intent = new Intent();
            intent.setComponent(new ComponentName(context, paramMap.get("target_class")));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);*//*

        }*/
        //第二种：通过AIDL方式实现跨进程通讯
        if (iWebViewProcessToMainProcess != null) {
            try {
                iWebViewProcessToMainProcess.handleWebCommand(commandName, jsonParam, new ICallbackFromMainProcessToWebViewProcessAidlInterface.Stub() {
                    @Override
                    public void onResult(String callbackname, String response) throws RemoteException {
                        Log.d(TAG,"callbackname:"+callbackname+"--response:"+response);

                        //todo 通过WebView发送数据给网页JS

                        baseWebView.postJsData(callbackname,response);


                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

}
