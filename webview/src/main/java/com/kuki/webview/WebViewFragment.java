package com.kuki.webview;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kuki.base.loadsir.ErrorCallback;
import com.kuki.base.loadsir.LoadingCallback;
import com.kuki.webview.callback.WebViewCallback;
import com.kuki.webview.databinding.FragmentWebviewBinding;
import com.kuki.webview.utils.Constants;
import com.kuki.webview.webchromeclient.KukiWebChromeClient;
import com.kuki.webview.webviewclient.KukiWebViewClient;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * author ：yeton
 * date : 2021/8/16 16:21
 * package：com.kuki.webview
 * description :
 */
public class WebViewFragment extends Fragment implements WebViewCallback, OnRefreshListener {

    public static final String TAG = WebViewFragment.class.getSimpleName();

    private String mUrl;
    private FragmentWebviewBinding mBinding;
    private LoadService mLoadService;
    //页面是否收到错误
    private boolean mIsReceivedError = false;
    private boolean mCanNativeRefresh;

    public static WebViewFragment newInstance(String url, boolean canNativeRefresh) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.URL, url);
        bundle.putBoolean(Constants.CAN_NATIVE_REFRESH, canNativeRefresh);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUrl = bundle.getString(Constants.URL);
            mCanNativeRefresh = bundle.getBoolean(Constants.CAN_NATIVE_REFRESH);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false);

        mBinding.webview.getSettings().setJavaScriptEnabled(true);
        mBinding.webview.setWebViewClient(new KukiWebViewClient(this));
        mBinding.webview.setWebChromeClient(new KukiWebChromeClient(this));
        mBinding.webview.loadUrl(mUrl);


        mBinding.smartrefreshlayout.setEnableLoadMore(false);
        mBinding.smartrefreshlayout.setEnableRefresh(mCanNativeRefresh);
        mBinding.smartrefreshlayout.setOnRefreshListener(this);

        //第二步：注册布局View
        mLoadService = LoadSir.getDefault().register(mBinding.smartrefreshlayout, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                // 重新加载逻辑
                mLoadService.showCallback(LoadingCallback.class);
                mBinding.webview.reload();
            }
        });

        //第三步：返回LoadSir生成的LoadLayout
        return mLoadService.getLoadLayout();
    }

    @Override
    public void onPageStarted(String url) {
        if (mLoadService != null) {
            mLoadService.showCallback(LoadingCallback.class);
        }

    }

    @Override
    public void onPageFinished(String url) {
        Log.d(TAG, "onPageFinished..");

        mBinding.smartrefreshlayout.finishRefresh();

        //收到错误之后也会走 onPageFinished，所以这里要做处理
        if (mIsReceivedError) {
            mIsReceivedError = false;
            return;
        }

        mBinding.smartrefreshlayout.setEnableRefresh(mCanNativeRefresh);
        if (mLoadService != null) {
            mLoadService.showSuccess();
        }
    }

    @Override
    public void onReceivedError(WebResourceError error) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d(TAG, "onReceivedError:" + error.getDescription());
        }

        mIsReceivedError = true;
        //错误的时候强制可以下拉刷新
        mBinding.smartrefreshlayout.setEnableRefresh(true);
        if (mLoadService != null) {
            mLoadService.showCallback(ErrorCallback.class);
        }
    }

    @Override
    public void updateTitle(String title) {

        //有争议，可以使用接口回调，或者EventBus
        if (getActivity() instanceof WebViewActivity) {
            ((WebViewActivity) getActivity()).updateTitle(title);
        }

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mBinding.webview.reload();
    }
}
