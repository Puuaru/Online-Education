package com.puuaru.acl.mapper;

import com.puuaru.acl.entity.Permission;
import com.puuaru.acl.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 角色权限 Mapper 接口
 * </p>
 *
 * @author puuaru
 * @since 2023-04-21
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    List<Permission> getPermissionsByRoleId(Long roleId);
}
