package com.puuaru.handler;

import cn.hutool.crypto.SecureUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Description: Md5PasswordEncoder
 * <p>基础的Md5编码器，并不可靠</p>
 * @Author: puuaru
 * @Date: 2023/5/9
 */
@Component
public class Md5PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return SecureUtil.md5(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return Objects.equals(encodedPassword, SecureUtil.md5(rawPassword.toString()));
    }
}
