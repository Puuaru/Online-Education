package com.puuaru.vod.properties;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description: VodProperties
 * @Author: puuaru
 * @Date: 2023/1/6
 */
@Data
@TableName("three_party_info")
public class VodProperties {
    @TableField("id")
    private String accessKeyId;

    @TableField("secret")
    private String accessKeySecret;
}
