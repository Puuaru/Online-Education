package com.puuaru.center.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description: Email
 * @Author: puuaru
 * @Date: 2023/1/29
 */
@Data
@TableName("three_party_info")
public class ThreePartyProperties {
    @TableField("id")
    String clientId;

    @TableField("secret")
    String secret;
}
