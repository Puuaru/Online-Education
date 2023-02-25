package com.puuaru.order.service;

import com.puuaru.order.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author puuaru
 * @since 2023-02-21
 */
public interface PayLogService extends IService<PayLog> {

    Boolean savePayLog(String orderNo);
}
