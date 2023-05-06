package com.puuaru.acl.mapper;

import com.puuaru.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author puuaru
 * @since 2023-04-21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
