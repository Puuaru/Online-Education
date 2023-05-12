package com.puuaru.acl.controller;


import com.puuaru.acl.entity.Permission;
import com.puuaru.acl.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 前端控制器,
 * 控制权限与菜单
 * </p>
 *
 * @author puuaru
 * @since 2023-04-21
 */
@RestController
@RequestMapping("/acl/permission")
@Api("菜单及权限控制")
public class PermissionController {

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * 以树的结构列出所有权限
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("以树的结构列出所有权限")
    public List<Permission> getPermissionsList() {
        return permissionService.getPermissionsList();
    }

    /**
     * 根据permission_id删除权限
     * @param permissionId
     */
    @DeleteMapping("/delete/{permissionId}")
    @ApiOperation("根据permission_id删除权限")
    public void deletePermission(@PathVariable("permissionId") Long permissionId) {
        permissionService.deletePermissionById(permissionId);
    }

    /**
     * 插入菜单或权限
     * @param permission
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("插入菜单或权限")
    public Boolean savePermission(@RequestBody Permission permission) {
        return permissionService.save(permission);
    }

    /**
     * 更新菜单或权限
     * @param permission
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("更新菜单或权限")
    public Boolean updatePermission(@RequestBody Permission permission) {
        return permissionService.updateById(permission);
    }
}

