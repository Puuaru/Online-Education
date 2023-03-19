package com.puuaru.statistic.Feign;

import com.puuaru.utils.ResultCommon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description: UcenterClient
 * @Author: puuaru
 * @Date: 2023/3/4
 */
@FeignClient("service-center")
public interface UcenterClient {

    @GetMapping("/center/member/stat/{date}")
    ResultCommon statRegister(@PathVariable("date") String date);
}
