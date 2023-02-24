package com.puuaru.order.service;

import com.puuaru.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author puuaru
 * @since 2023-02-21
 */
public interface OrderService extends IService<Order> {

    /**
     * 存储Order并返回订单号
     * @param courseId
     * @param memberId
     * @return
     */
    String saveOrder(Long courseId, String memberId);

    /**
     * 根据订单号获取订单
     * @param orderNo 订单号，注意不是订单id
     * @return
     */
    Order getOrderByNo(String orderNo);
}
