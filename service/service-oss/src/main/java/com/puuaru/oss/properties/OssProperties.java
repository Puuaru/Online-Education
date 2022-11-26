package com.puuaru.oss.properties;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description: OssProperties 考虑到数据保密性在项目中使用访问数据库的形式获取OSS参数
 *                              实际上将参数放入微服务的配置文件中去，通过nacos等配置中心统一管理会更好
 * @Author: puuaru
 * @Date: 2022/11/26
 */
@Data
@TableName("three_party_info")
public class OssProperties {

    @TableField("id")
    private String keyId;

    @TableField("secret")
    private String keySecret;

    @TableField("temp1")
    private String endpoint;

    @TableField("temp2")
    private String bucketName;
}
