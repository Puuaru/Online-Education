package com.puuaru.order.service;

import com.puuaru.utils.ResultCommon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description: CourseFrontService
 * @Author: puuaru
 * @Date: 2023/2/23
 */
@FeignClient("service-edu")
public interface CourseFrontService {
    @GetMapping("/edu/front/course/feign/{id}")
    ResultCommon getCourseFrontInfo(@PathVariable("id") Long id);
}
