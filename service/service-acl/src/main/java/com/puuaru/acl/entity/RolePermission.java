package com.puuaru.acl.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色权限
 * </p>
 *
 * @author puuaru
 * @since 2023-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("acl_role_permission")
@ApiModel(value="RolePermission对象", description="角色权限")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    public RolePermission(Long roleId, Long permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "角色id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    @ApiModelProperty(value = "权限id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long permissionId;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;


}
