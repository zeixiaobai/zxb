package org.zxb.oauth2.handler;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.zxb.web.constant.ErrorConstant;
import org.zxb.common.dto.Result;
import org.zxb.common.utils.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 失败返回
 *
 * @author zjx
 * @date 2020/1/15 0015
 */
public class AuthFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter printWriter = response.getWriter();
        printWriter.append(JSONUtil.toJSONString(new Result(ErrorConstant.AUTH_ERROR, "认证失败")));
    }
}
