package com.kuki.webview.bean;


import com.google.gson.JsonObject;

/**
 * author ：yeton
 * date : 2021/8/18 17:42
 * package：com.kuki.webview.bean
 * description :Javascript 传入的JsonString 解析的对象
 * 对象由名字+参数组成，整个JsonString可以看做是一个命令
 */
public class JsParam {

    //    {"name":"showToast","param":{"message":"this is a message from html."}}

    private String name;
    private JsonObject param;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonObject getParam() {
        return param;
    }

    public void setParam(JsonObject param) {
        this.param = param;
    }
}
