package com.kuki.webview.webviewprocess.webchromeclient;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.kuki.webview.callback.WebViewCallback;

/**
 * author ：yeton
 * date : 2021/8/17 16:30
 * package：com.kuki.webview.webviewchromeclient
 * description :
 */
public class KukiWebChromeClient extends WebChromeClient {

    public static final String TAG = KukiWebChromeClient.class.getSimpleName();

    private WebViewCallback webViewCallback;

    public KukiWebChromeClient(WebViewCallback webViewCallback) {
        this.webViewCallback = webViewCallback;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);

        if (webViewCallback != null) {
            webViewCallback.updateTitle(title);
        }
    }


    /**
     * 获取网页内部打印的日志
     *
     * @param consoleMessage 控制台信息
     * @return
     */
    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.d(TAG, consoleMessage.message());
        return super.onConsoleMessage(consoleMessage);
    }
}
