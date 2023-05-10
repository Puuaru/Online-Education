package com.puuaru.handler;

import com.puuaru.utils.ResponseUtil;
import com.puuaru.utils.ResultCommon;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: AccessDeniedHandler
 * @Author: puuaru
 * @Date: 2023/5/10
 */
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtil.out(httpServletResponse, ResultCommon.fail().setMessage("Authority forbidden"));
    }
}
