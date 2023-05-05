package com.puuaru.acl.service.impl;

import com.puuaru.acl.entity.RolePermission;
import com.puuaru.acl.mapper.RolePermissionMapper;
import com.puuaru.acl.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author puuaru
 * @since 2023-04-21
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    public void grantPermissionForRole(Long roleId, Long[] permissionIds) {
        // TODO: test
        List<RolePermission> rolePermissions = Arrays.stream(permissionIds)
                .map(permissionId -> new RolePermission(roleId, permissionId))
                .collect(Collectors.toList());
        super.saveBatch(rolePermissions);
    }
}
