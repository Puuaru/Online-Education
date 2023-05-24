package com.puuaru.acl.service;

import com.puuaru.acl.entity.Permission;
import com.puuaru.acl.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色权限 服务类
 * </p>
 *
 * @author puuaru
 * @since 2023-04-21
 */
public interface RolePermissionService extends IService<RolePermission> {

    void grantPermissionsForRole(Long roleId, Long[] permissionIds);

    List<Permission> getPermissionsByRoleId(Long roleId);

    void alterPermissionsForRole(Long roleId, Set<Long> updatedPermissionIds);
}
