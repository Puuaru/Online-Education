package com.puuaru.order.mapper;

import com.puuaru.order.entity.PayLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 支付日志表 Mapper 接口
 * </p>
 *
 * @author puuaru
 * @since 2023-02-21
 */
@Mapper
public interface PayLogMapper extends BaseMapper<PayLog> {

}
