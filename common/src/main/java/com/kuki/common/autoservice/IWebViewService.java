package com.kuki.common.autoservice;

import android.content.Context;

public interface IWebViewService {

  void startWebActivity(Context context,String url,String title,boolean isShowActionBar);
}
