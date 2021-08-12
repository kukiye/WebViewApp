package com.kuki.webview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.kuki.webview.databinding.ActivityWebviewBinding;

/**
 * author ：yeton
 * date : 2021/8/12 18:06
 * package：com.kuki.webview
 * description :
 */
public class WebViewActivity extends AppCompatActivity {

    private ActivityWebviewBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview);

        mBinding.webView.loadUrl("https://www.baidu.com");

    }
}
