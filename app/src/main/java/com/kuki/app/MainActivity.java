package com.kuki.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kuki.base.autoservice.KukiServiceLoader;
import com.kuki.common.autoservice.IWebViewService;
import com.kuki.webview.WebViewActivity;

import java.util.ServiceLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                startActivity(new Intent(MainActivity.this, WebViewActivity.class));

                //使用AutoService 实现页面跳转
                IWebViewService webViewService = KukiServiceLoader.load(IWebViewService.class);
                //                webViewService.startWebActivity(MainActivity.this, "https://www.baidu.com", "百度", true);
                if (webViewService != null) {
                    webViewService.startDemoHtml(MainActivity.this);
                }
            }
        });
    }
}