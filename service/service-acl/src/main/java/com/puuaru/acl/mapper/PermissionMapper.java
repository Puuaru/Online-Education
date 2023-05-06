package com.puuaru.acl.mapper;

import com.puuaru.acl.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author puuaru
 * @since 2023-04-21
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    List<String> getPermissionsByUserId(Long userId);

}
