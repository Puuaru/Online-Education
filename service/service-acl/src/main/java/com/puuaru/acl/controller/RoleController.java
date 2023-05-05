package com.puuaru.acl.controller;


import com.puuaru.acl.service.RolePermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author puuaru
 * @since 2023-04-21
 */
@RestController
@RequestMapping("/acl/role")
public class RoleController {

    private final RolePermissionService rolePermissionService;

    @Autowired
    public RoleController(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    /**
     * 为角色分配权限
     * @param roleId
     * @param permissionIds
     */
    @PostMapping("/grant")
    @ApiOperation("为角色分配权限")
    public void grantPermissionForRole(Long roleId, Long[] permissionIds) {
        rolePermissionService.grantPermissionForRole(roleId, permissionIds);
    }
}

