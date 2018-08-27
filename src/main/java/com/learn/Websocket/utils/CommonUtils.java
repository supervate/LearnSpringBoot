package com.learn.Websocket.utils;

import java.util.Map;

public class CommonUtils {

    public static String getValue(Map<String, Object> param, String sltAccountId) {
        return (String)param.get(sltAccountId);
    }
}
