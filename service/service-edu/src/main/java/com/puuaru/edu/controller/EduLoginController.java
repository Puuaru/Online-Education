package com.puuaru.edu.controller;

import com.puuaru.utils.ResultCommon;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: EduLoginController
 * @Author: puuaru
 * @Date: 2022/11/24
 */
@RestController
@RequestMapping("/edu/user")
@CrossOrigin    // 暂时解决跨域
public class EduLoginController {
    @PostMapping("login")
    public ResultCommon login() {
        return ResultCommon.success().setData("token", "admin");
    }

    @GetMapping("info")
    public ResultCommon info() {
        return ResultCommon.success()
                .setData("name", "admin")
                .setData("avatar", "https://avatars.githubusercontent.com/u/38312096?v=4");
    }
}
