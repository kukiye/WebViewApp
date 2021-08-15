package com.kuki.webview;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.kuki.webview.databinding.ActivityWebviewBinding;
import com.kuki.webview.utils.Constants;

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
        mBinding.webview.getSettings().setJavaScriptEnabled(true);
        mBinding.webview.loadUrl(getIntent().getStringExtra(Constants.URL));

        mBinding.actionBar.setVisibility(getIntent().getBooleanExtra(Constants.IS_SHOW_ACTION_BAR, true)? View.VISIBLE:View.GONE);
        mBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.this.finish();
            }
        });

    }
}
