package com.puuaru.center.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: MemberRegisterVo
 * @Author: puuaru
 * @Date: 2023/1/30
 */
@Data
public class MemberRegisterVo {
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("注册邮箱")
    private String email;
    @ApiModelProperty("注册验证码")
    private String code;
}
