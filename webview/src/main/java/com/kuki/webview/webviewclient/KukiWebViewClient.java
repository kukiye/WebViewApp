package com.kuki.webview.webviewclient;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kuki.webview.callback.WebViewCallback;

/**
 * author ：yeton
 * date : 2021/8/17 16:01
 * package：com.kuki.webview.webviewclient
 * description :
 */
public class KukiWebViewClient extends WebViewClient {

    public static final String TAG = "KukiWebViewClient";

    private WebViewCallback webViewCallback;

    public KukiWebViewClient(WebViewCallback webViewCallback) {
        this.webViewCallback = webViewCallback;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (webViewCallback != null) {
            webViewCallback.onPageStarted(url);
        } else {
            Log.e(TAG, "onPageStarted: webViewCallback is empty");
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (webViewCallback != null) {
            webViewCallback.onPageFinished(url);
        } else {
            Log.e(TAG, "onPageFinished: webViewCallback is empty");
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        if (webViewCallback != null) {
            webViewCallback.onReceivedError(error);
        } else {
            Log.e(TAG, "onReceivedError: webViewCallback is empty");
        }
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return super.shouldOverrideUrlLoading(view, url);
    }
}
