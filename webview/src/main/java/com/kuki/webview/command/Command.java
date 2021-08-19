package com.kuki.webview.command;

import java.util.Map;

/**
 * author ：yeton
 * date : 2021/8/19 17:45
 * package：com.kuki.webview.command
 * description :命令的接口
 */
public interface Command {

    String getName();

    void excute(Map params);

}
