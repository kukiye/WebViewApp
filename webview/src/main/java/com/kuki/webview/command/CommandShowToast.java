package com.kuki.webview.command;

import android.util.Log;
import android.widget.Toast;

import com.google.auto.service.AutoService;
import com.kuki.base.BaseApplication;

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
    public void excute(Map params) {

        Log.d("CommandShowToast", String.valueOf(params.get("message")));
        Toast.makeText(BaseApplication.sApplication, String.valueOf(params.get("message")), Toast.LENGTH_LONG).show();
    }
}
