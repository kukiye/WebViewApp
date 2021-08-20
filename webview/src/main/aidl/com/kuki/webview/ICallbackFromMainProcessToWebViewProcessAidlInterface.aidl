// ICallbackFromMainProcessToWebViewProcessAidlInterface.aidl
package com.kuki.webview;

// Declare any non-default types here with import statements

interface ICallbackFromMainProcessToWebViewProcessAidlInterface {

    void onResult(String callbackname,String response);

}