package com.puuaru.acl.vo;

import com.puuaru.acl.entity.Permission;
import com.puuaru.acl.entity.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description: UserInfo
 * @Author: puuaru
 * @Date: 2023/5/23
 */
@Data
@ApiModel("登录时用于返回用户角色信息及权限信息的VO类")
public class UserInfo {
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户头像URL")
    private String avatar;

    @ApiModelProperty(value = "用户持有的权限集")
    List<Permission> permissions;

    @ApiModelProperty(value = "用户持有的角色集")
    List<Role> roles;
}
