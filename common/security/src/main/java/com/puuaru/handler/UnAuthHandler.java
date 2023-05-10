package com.puuaru.handler;

import com.puuaru.utils.ResponseUtil;
import com.puuaru.utils.ResultCommon;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: UnAuthHandler
 * @Author: puuaru
 * @Date: 2023/5/10
 */
public class UnAuthHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseUtil.out(httpServletResponse, ResultCommon.fail().setMessage("guest didn't login"));
    }
}
