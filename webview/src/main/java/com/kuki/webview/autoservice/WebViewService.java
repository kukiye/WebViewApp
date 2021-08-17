package com.kuki.webview.autoservice;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.google.auto.service.AutoService;
import com.kuki.common.autoservice.IWebViewService;
import com.kuki.webview.WebViewActivity;
import com.kuki.webview.WebViewFragment;
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

    @Override
    public Fragment getWebViewFragment(String url, boolean canNativeRefresh) {
        WebViewFragment webViewFragment = WebViewFragment.newInstance(url, canNativeRefresh);
        return webViewFragment;
    }
}
