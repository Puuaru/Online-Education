package com.puuaru.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puuaru.acl.entity.Permission;
import com.puuaru.acl.entity.RolePermission;
import com.puuaru.acl.mapper.RolePermissionMapper;
import com.puuaru.acl.service.RolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
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

    /**
     * 为角色添加权限，似乎可以被alterPermissionsForRole替代？
     * @param roleId
     * @param permissionIds
     */
    @Override
    public void grantPermissionsForRole(Long roleId, Long[] permissionIds) {
        // TODO: test
        List<RolePermission> rolePermissions = Arrays.stream(permissionIds)
                .map(permissionId -> new RolePermission(roleId, permissionId))
                .collect(Collectors.toList());
        super.saveBatch(rolePermissions);
    }

    /**
     * 根据角色id返回其拥有的全部权限值
     * @param roleId
     * @return
     */
    @Override
    public List<Permission> getPermissionsByRoleId(Long roleId) {
        List<Permission> permissions = baseMapper.getPermissionsByRoleId(roleId);
        return permissions;
    }

    /**
     * 修改角色的权限，可增可删
     * @param roleId 目标角色id
     * @param updatedPermissionIds 角色在操作后应持有的全部权限id
     */
    @Override
    public void alterPermissionsForRole(Long roleId, Set<Long> updatedPermissionIds) {
        // TODO: 增加一个对updatedPermissionIds在acl_permission的数据一致性检测，可以考虑使用redis缓存permission表的全部id字段之后遍历updatedPermissionIds
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        if (CollectionUtils.isEmpty(updatedPermissionIds)) {
            // 更新后的PermissionIds为空，代表将该角色的权限清空
            super.remove(wrapper);
            return;
        }

        List<Long> oldPermissionIds = super.list(wrapper).stream()
                .map(RolePermission::getPermissionId)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(oldPermissionIds)) {
            // 若角色本身没有任何权限，且updatedPermissionIds不为空，则说明需要添加权限
            List<RolePermission> permissions = updatedPermissionIds.stream()
                    .map(permissionId -> new RolePermission(roleId, permissionId))
                    .collect(Collectors.toList());
            super.saveBatch(permissions);
            return;
        }

        // 滤出删除的权限，既保留oldPermissionIds中存在而updatedPermissionIds中不存在的
        List<RolePermission> removedPermissions = oldPermissionIds.stream()
                .filter(item -> !updatedPermissionIds.contains(item))
                .map(permissionId -> new RolePermission(roleId, permissionId))
                .collect(Collectors.toList());
        super.removeByIds(removedPermissions);

        // 滤出新增的权限，既保留updatedPermissionIds中存在而oldPermissionIds中不存在的
        List<RolePermission> appendedPermissions = updatedPermissionIds.stream()
                .filter(item -> !oldPermissionIds.contains(item))
                .map(permissionId -> new RolePermission(roleId, permissionId))
                .collect(Collectors.toList());
        super.saveBatch(appendedPermissions);
    }
}
