// IWebViewProcessToMainProcess.aidl
package com.kuki.webview;

// Declare any non-default types here with import statements

interface IWebViewProcessToMainProcess {

    void handleWebCommand(String commandName,String jsonParams);

}