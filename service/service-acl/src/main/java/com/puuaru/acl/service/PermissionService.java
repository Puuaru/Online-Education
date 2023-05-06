package com.puuaru.acl.service;

import com.puuaru.acl.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author puuaru
 * @since 2023-04-21
 */
public interface PermissionService extends IService<Permission> {

    List<Permission> getPermissionsList();

    void deletePermissionById(Long permissionId);

    List<String> getPermissionsByUserId(Long userId);
}
