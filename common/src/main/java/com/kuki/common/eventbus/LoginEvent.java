package com.kuki.common.eventbus;

/**
 * author ：yeton
 * date : 2021/8/20 17:51
 * package：com.kuki.common.eventbus
 * description :
 */
public class LoginEvent {
    public String userName;

    public LoginEvent(String userName) {
        this.userName = userName;
    }
}
