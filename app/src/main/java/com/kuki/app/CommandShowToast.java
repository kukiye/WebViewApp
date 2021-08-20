package com.kuki.app;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.auto.service.AutoService;
import com.kuki.base.BaseApplication;
import com.kuki.webview.ICallbackFromMainProcessToWebViewProcessAidlInterface;
import com.kuki.webview.command.Command;

import java.util.Map;

/**
 * author ：yeton
 * date : 2021/8/19 18:09
 * package：com.kuki.webview.command
 * description :
 */
@AutoService(Command.class)
public class CommandShowToast implements Command {
    @Override
    public String getName() {
        return "showToast";
    }

    @Override
    public void excute(Map params, ICallbackFromMainProcessToWebViewProcessAidlInterface callback) {

        Log.d("CommandShowToast", String.valueOf(params.get("message")));
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseApplication.sApplication, String.valueOf(params.get("message")), Toast.LENGTH_LONG).show();
            }
        });

    }
}
