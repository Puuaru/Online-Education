package com.puuaru.order.controller;


import com.puuaru.order.entity.Order;
import com.puuaru.order.service.OrderService;
import com.puuaru.utils.JwtUtils;
import com.puuaru.utils.ResultCommon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author puuaru
 * @since 2023-02-21
 */
@RestController
@CrossOrigin
@RequestMapping("/order/operation/")
@Api("订单操作")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 存储订单
     * @param courseId
     * @param request
     * @return
     */
    @PostMapping("/{courseId}")
    @ApiOperation("存储订单")
    public ResultCommon saveOrder(@PathVariable("courseId") Long courseId, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwt(request);
        String orderNo = orderService.saveOrder(courseId, memberId);
        return ResultCommon.success().setData("orderNo", orderNo);
    }

    /**
     * 根据订单号查询订单
     * @param orderNo
     * @return
     */
    @GetMapping("/{orderNo}")
    @ApiOperation("根据订单号查询订单")
    public Order getOrder(@PathVariable("orderNo") String orderNo) {
        Order order = orderService.getOrderByNo(orderNo);
        return order;
    }
}
