package com.puuaru.order.mapper;

import com.puuaru.order.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author puuaru
 * @since 2023-02-21
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
