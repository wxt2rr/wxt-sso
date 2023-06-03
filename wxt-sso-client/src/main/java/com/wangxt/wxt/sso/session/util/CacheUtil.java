package com.wangxt.wxt.sso.session.util;

import java.util.HashMap;
import java.util.Map;

public class CacheUtil {

    private final static Map<String, String> cache = new HashMap<>();

    public static synchronized void put(String key, String value) {
        cache.put(key, value);
    }

    public static synchronized String get(String key) {
        return cache.getOrDefault(key, "");
    }

    public static synchronized void del(String key) {
        cache.remove(key);
    }
}
