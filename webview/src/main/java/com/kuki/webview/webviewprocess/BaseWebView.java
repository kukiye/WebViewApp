package com.kuki.webview.webviewprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.kuki.base.BaseApplication;
import com.kuki.webview.bean.JsParam;
import com.kuki.webview.callback.WebViewCallback;
import com.kuki.webview.webviewprocess.webchromeclient.KukiWebChromeClient;
import com.kuki.webview.webviewprocess.websettings.WebViewSettings;
import com.kuki.webview.webviewprocess.webviewclient.KukiWebViewClient;

import java.util.Map;

/**
 * author ：yeton
 * date : 2021/8/18 16:34
 * package：com.kuki.webview.webviewprocess
 * description :自定义WebView
 */
public class BaseWebView extends WebView {
    public static final String TAG = BaseWebView.class.getSimpleName();

    public BaseWebView(@NonNull Context context) {
        super(context);
        init();
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        WebViewProcessCommandDispatcher.getInstance().initAidlConnection();

        WebViewSettings.getInstance().setSettings(this);
        addJavascriptInterface(this, "kukiwebview");

    }

    public void registerWebViewCallBack(WebViewCallback webViewCallback) {
        setWebViewClient(new KukiWebViewClient(webViewCallback));
        setWebChromeClient(new KukiWebChromeClient(webViewCallback));
    }

    @JavascriptInterface
    public void takeNativeAction(final String jsString) {
        Log.d(TAG, jsString);

        if (!TextUtils.isEmpty(jsString)) {
            JsParam jsParamObject = new Gson().fromJson(jsString, JsParam.class);
            if (jsParamObject != null) {
                WebViewProcessCommandDispatcher.getInstance().excuteCommand(jsParamObject.getName(), new Gson().toJson(jsParamObject.getParam()), this);
            }
        }

    }

}
