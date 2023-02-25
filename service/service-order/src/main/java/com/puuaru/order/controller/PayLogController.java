package com.puuaru.order.controller;


import com.puuaru.order.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author puuaru
 * @since 2023-02-21
 */
@RestController
@RequestMapping("/order/t-pay-log")
public class PayLogController {
    private final PayLogService payLogService;

    @Autowired
    public PayLogController(PayLogService payLogService) {
        this.payLogService = payLogService;
    }

    public Boolean savePayLog(@PathVariable("orderNo") String orderNo) {
        payLogService.savePayLog(orderNo);
    }
}

