package com.puuaru.order.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.puuaru.order.entity.Order;
import com.puuaru.order.entity.PayLog;
import com.puuaru.order.mapper.PayLogMapper;
import com.puuaru.order.service.OrderService;
import com.puuaru.order.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author puuaru
 * @since 2023-02-21
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    private final OrderService orderService;

    @Autowired
    public PayLogServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Boolean savePayLog(String orderNo) {
        Order order = orderService.getOrderByNo(orderNo);
        order.setStatus(1);
        if (!orderService.updateById(order)) {
            return false;
        }
        PayLog payLog = new PayLog();
        payLog.setOrderNo(orderNo);
        payLog.setTotalFee(order.getTotalFee());
        payLog.setPayTime(LocalDateTime.now());
        payLog.setTransactionId(RandomUtil.randomNumbers(11));
        return super.save(payLog);
    }
}
