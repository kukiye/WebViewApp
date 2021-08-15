package com.kuki.webview.autoservice;

import android.content.Context;
import android.content.Intent;

import com.google.auto.service.AutoService;
import com.kuki.common.autoservice.IWebViewService;
import com.kuki.webview.WebViewActivity;
import com.kuki.webview.utils.Constants;

@AutoService({IWebViewService.class})
public class WebViewService implements IWebViewService {
    @Override
    public void startWebActivity(Context context, String url, String title, boolean isShowActionBar) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(Constants.URL, url);
        intent.putExtra(Constants.TITLE, title);
        intent.putExtra(Constants.IS_SHOW_ACTION_BAR, isShowActionBar);
        context.startActivity(intent);

    }
}
