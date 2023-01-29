package com.puuaru.center.thirdparty;

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
public class Email {
    @TableField("id")
    String username;

    @TableField("secret")
    String password;
}
