package com.puuaru.helpers;

import java.util.Map;

/**
 * @Description: RedirectHelper
 * @Author: puuaru
 * @Date: 2023/2/1
 */
public class RedirectHelper {
    private String url;
    private int paramCount;

    public RedirectHelper(String baseUrl) {
        this.url = baseUrl;
        paramCount = 0;
    }

    public RedirectHelper addParams(Map<String, String> params) {
        StringBuilder suffixBuilder = new StringBuilder();
        if (paramCount < 1) {
            suffixBuilder.append("?");
        }
        params.forEach((key, value) -> {
            suffixBuilder.append(key + "=" + value + "&");
        });
        url = suffixBuilder.insert(0, url).toString();
        return this;
    }

    public RedirectHelper addParam(String key, String value) {
        StringBuilder suffixBuilder = new StringBuilder();
        if (paramCount < 1) {
            suffixBuilder.append("?");
        }
        suffixBuilder.append(key + "=" + value + "&");
        url = suffixBuilder.insert(0, url).toString();
        return this;
    }

    public String getUrl() {
        return url;
    }
}
