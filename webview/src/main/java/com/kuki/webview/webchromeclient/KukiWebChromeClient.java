package com.kuki.webview.webchromeclient;

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

    private WebViewCallback webViewCallback;

    public KukiWebChromeClient(WebViewCallback webViewCallback) {
        this.webViewCallback = webViewCallback;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);

        if(webViewCallback!=null){
            webViewCallback.updateTitle(title);
        }


    }
}
