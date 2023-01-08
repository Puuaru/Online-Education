package com.puuaru.edu.feign.fallback;

import com.puuaru.edu.feign.VodClient;
import com.puuaru.utils.ResultCommon;
import org.springframework.stereotype.Service;

/**
 * @Description: VodFallback
 * @Author: puuaru
 * @Date: 2023/1/8
 */
@Service
public class VodFallback implements VodClient {
    @Override
    public ResultCommon deleteSourceVideo(String id) {
        ResultCommon result = ResultCommon.fail().setMessage("Vod服务调用失败");
        System.out.println("soutFlag" + result);
        return result;
    }
}
