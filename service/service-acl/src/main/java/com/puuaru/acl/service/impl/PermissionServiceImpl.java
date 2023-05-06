package com.puuaru.acl.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.puuaru.acl.entity.Permission;
import com.puuaru.acl.mapper.PermissionMapper;
import com.puuaru.acl.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author puuaru
 * @since 2023-04-21
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    /**
     * <p>查找所有权限，并以树的形式展现</p>
     * <p>使用递归实现</p>
     * @return
     */
    @Override
    public List<Permission> getPermissionsList() {
        List<Permission> permissions = this.list();
        return permissions.stream()
                .filter(item -> item.getId() == 1)
                .peek(item -> {
                    item.setLevel(0);
                    item.setChildren(getPermissionChildren(permissions, item));
                }).collect(Collectors.toList());
    }

    private List<Permission> getPermissionChildren(List<Permission> permissions, Permission parent) {
        return permissions.stream()
                .filter(item -> item.getPid() == parent.getId())
                .peek(item -> {
                    item.setLevel(parent.getLevel() + 1);
                    item.setChildren(getPermissionChildren(permissions, item));
                }).collect(Collectors.toList());
    }

    /**
     * <p>以递归的形式删除权限</p>
     * @param permissionId
     */
    @Override
    public void deletePermissionById(Long permissionId) {
        super.removeById(permissionId);
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        deleteChildren(permissionId, wrapper);
    }

    private void deleteChildren(Long permissionId, QueryWrapper<Permission> wrapper) {
        wrapper.clear();
        wrapper.eq("pid", permissionId);
        // 查出子权限
        List<Long> childrenIds = super.list(wrapper).stream()
                .map(Permission::getId)
                .collect(Collectors.toList());
        if (CollectionUtil.isEmpty(childrenIds)) {
            // 无子权限直接返回
            return;
        }
        super.removeByIds(childrenIds);
        childrenIds.forEach(id -> deleteChildren(id, wrapper));
    }

    @Override
    public List<String> getPermissionsByUserId(Long userId) {
        return super.baseMapper.getPermissionsByUserId(userId);
    }

}
