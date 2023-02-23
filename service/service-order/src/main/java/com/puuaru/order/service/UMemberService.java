package com.puuaru.order.service;

import com.puuaru.utils.ResultCommon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description: UMemberService
 * @Author: puuaru
 * @Date: 2023/2/23
 */
@FeignClient("service-center")
public interface UMemberService {
    @GetMapping("/center/member/memberInfo/{id}")
    ResultCommon getMemberInfo(@PathVariable("id") String id);
}
