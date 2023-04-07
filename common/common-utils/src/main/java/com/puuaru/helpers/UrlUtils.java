package com.puuaru.helpers;

import java.util.Map;

/**
 * @Description: RedirectHelper
 * @Author: puuaru
 * @Date: 2023/2/1
 */
public class UrlUtils {
    private String url;
    private int paramCount;

    public UrlUtils() {
    }

    public static UrlUtils init(String baseUrl) {
        UrlUtils urlUtils = new UrlUtils();
        urlUtils.url = baseUrl;
        urlUtils.paramCount = 0;
        return urlUtils;
    }

    public UrlUtils addParams(Map<String, String> params) {
        StringBuilder suffixBuilder = new StringBuilder();
        if (paramCount < 1) {
            suffixBuilder.append("?");
        }
        params.forEach((key, value) -> {
            suffixBuilder.append(key + "=" + value + "&");
        });
        paramCount += params.size();
        url = suffixBuilder.insert(0, url).toString();
        return this;
    }

    public UrlUtils addParam(String key, String value) {
        StringBuilder suffixBuilder = new StringBuilder();
        if (paramCount < 1) {
            suffixBuilder.append("?");
        }
        suffixBuilder.append(key + "=" + value + "&");
        paramCount++;
        url = suffixBuilder.insert(0, url).toString();
        return this;
    }

    public String getUrl() {
        String result = this.url;
        if (paramCount > 0) {
            result = url.substring(0, url.length() - 1);
        }
        return result;
    }
}
