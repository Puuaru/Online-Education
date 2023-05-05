package com.puuaru.acl.controller;


import com.puuaru.acl.entity.Permission;
import com.puuaru.acl.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author puuaru
 * @since 2023-04-21
 */
@RestController
@RequestMapping("/acl/permission")
public class PermissionController {

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("/list")
    public List<Permission> getPermissionsList() {
        return permissionService.getPermissionsList();
    }

    @DeleteMapping("/delete/{permissionId}")
    public void deletePermission(@PathVariable("permissionId") Long permissionId) {
        permissionService.deletePermissionById(permissionId);
    }
}

