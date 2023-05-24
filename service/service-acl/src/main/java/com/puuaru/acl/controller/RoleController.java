package com.puuaru.acl.controller;


import com.puuaru.acl.entity.Permission;
import com.puuaru.acl.entity.Role;
import com.puuaru.acl.service.RolePermissionService;
import com.puuaru.servicebase.vo.PageData;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
     * 根据角色Id查询角色拥有的权限
     * @param roleId
     * @return
     */
    @GetMapping("/permissions/list/{roleId}")
    @ApiOperation("根据角色Id查询角色拥有的权限")
    public List<Permission> getPermissionsByRoleId(@PathVariable Long roleId) {
        List<Permission> permissions = rolePermissionService.getPermissionsByRoleId(roleId);
        return permissions;
    }

    /**
     * 为角色分配权限
     * @param roleId
     * @param permissionIds
     */
    @PostMapping("/permissions/grant/{roleId}")
    @ApiOperation("为角色分配权限")
    public void grantPermissionsForRole(@PathVariable Long roleId, @RequestBody Long[] permissionIds) {
        rolePermissionService.grantPermissionsForRole(roleId, permissionIds);
    }

    @PostMapping("/permissions/alter/{roleId}")
    @ApiOperation("更改角色的权限")
    public void alterPermissionsForRole(@PathVariable Long roleId, @RequestBody Set<Long> permissionIds) {
        rolePermissionService.alterPermissionsForRole(roleId, permissionIds);
    }

    /**
     * 获取全部角色信息
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("获取全部角色信息")
    public List<Role> listRoles() {
        return null;
    }

    /**
     * 针对角色名字段进行查询，并根据提供的信息返回分页后的数据
     * @param current 当前页码
     * @param limit 每页的数据上限
     * @param nameQuery 针对角色名的查询信息
     * @return
     */
    @GetMapping("/condition/{current}/{limit}")
    @ApiOperation("针对角色名字段进行查询，并根据提供的信息返回分页后的数据")
    public PageData<Role> getRolesWithQuery(@PathVariable Long current, @PathVariable Long limit, String nameQuery) {
        return null;
    }

    /**
     * 按id删除角色
     * @param roleId
     * @return
     */
    @DeleteMapping("/remove/{roleId}")
    @ApiOperation("按id删除角色")
    public Boolean removeRole(@PathVariable Long roleId) {
        return false;
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加角色")
    public Boolean addRole(@RequestBody Role role) {
        return false;
    }

    /**
     * 更新角色
     * @param role
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("更新角色")
    public Boolean updateRoleById(@RequestBody Role role) {
        return false;
    }

}

