package com.puuaru.order.service.impl;

import com.puuaru.order.entity.PayLog;
import com.puuaru.order.mapper.PayLogMapper;
import com.puuaru.order.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
