package com.kuki.webview.callback;

import android.webkit.WebResourceError;

/**
 * author ：yeton
 * date : 2021/8/17 16:05
 * package：com.kuki.webview.callback
 * description :
 */
public interface WebViewCallback {

    void onPageStarted(String url);

    void onPageFinished(String url);

    void onReceivedError(WebResourceError error);

    void updateTitle(String title);
}
