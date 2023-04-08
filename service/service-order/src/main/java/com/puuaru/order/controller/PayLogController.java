package com.puuaru.order.controller;


import com.puuaru.order.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author puuaru
 * @since 2023-02-21
 */
@RestController
@RequestMapping("/order/pay-log")
public class PayLogController {
    private final PayLogService payLogService;

    @Autowired
    public PayLogController(PayLogService payLogService) {
        this.payLogService = payLogService;
    }

    @PostMapping("/{orderNo}")
    public Boolean savePayLog(@PathVariable("orderNo") String orderNo) {
        return payLogService.savePayLog(orderNo);
    }
}

