// IWebViewProcessToMainProcessAidlInterface.aidl
package com.kuki.webview;

// Declare any non-default types here with import statements
import com.xiangxue.webview.ICallbackFromMainProcessToWebViewProcessAidlInterface;
interface IWebViewProcessToMainProcessAidlInterface {

    void handleWebCommand(String commandName,String jsonParams, in ICallbackFromMainProcessToWebViewProcessAidlInterface callback);

}