package com.puuaru.edu.feign;

import com.puuaru.utils.ResultCommon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description: VodClient
 * @Author: puuaru
 * @Date: 2023/1/7
 */
@FeignClient("service-vod")
public interface VodClient {
    @DeleteMapping("/vod/{id}")
    ResultCommon deleteSourceVideo(@PathVariable("id") String id);
}
