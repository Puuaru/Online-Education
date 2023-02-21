package com.puuaru.order.service.impl;

import com.puuaru.order.entity.Order;
import com.puuaru.order.mapper.OrderMapper;
import com.puuaru.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author puuaru
 * @since 2023-02-21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
