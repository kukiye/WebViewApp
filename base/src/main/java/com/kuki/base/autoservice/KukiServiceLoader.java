package com.kuki.base.autoservice;

import java.util.ServiceLoader;

public class KukiServiceLoader {
    private KukiServiceLoader() {
    }

    public static <S> S load(Class<S> service) {
        try {
            return ServiceLoader.load(service).iterator().next();
        } catch (Exception exception) {
            return null;
        }
    }

}
