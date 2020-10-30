package com.zxb.admin.filter;

import com.zxb.admin.constant.AdminConstant;
import com.zxb.admin.constant.AdminErrorConstant;
import com.zxb.admin.entity.Role;
import com.zxb.admin.entity.Token;
import com.zxb.admin.service.AuthService;
import com.zxb.admin.service.MenuService;
import com.zxb.admin.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.zxb.common.utils.JSONUtil;
import org.zxb.common.utils.LoggerUtil;
import org.zxb.web.vo.Result;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 权限认证
 *
 * @author zjx
 * @date 2020/7/31 0031 15:21
 */
@Component
@Slf4j
public class AutoFilter implements Filter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthService authService;

    private String[] excludePaths = {"/swagger*/**", "/webjars/**", "/v2/**", "/user/login", "/", "/csrf"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        // 从请求头获取tokne
        String token = req.getHeader("Auto-Token");
        // 获取具体请求
        String uri = req.getRequestURI();
        LoggerUtil.info(log, "请求路径： {}", uri);

        // 判断请求是否需要验证token
        if (pass(uri)) {
            chain.doFilter(request, response);
            return;
        }

        // token不存在，返回错误信息
        if (token == null) {
            print(response);
            return;
        }
        // 获取token对象
        Token tokenBean = tokenService.get(token);

        // 判断token是否已经存在
        if (tokenBean == null) {
            print(response);
            return;
        }
        // 判断token是否失效
        if (System.currentTimeMillis() - tokenBean.getExpireTime() <= AdminConstant.TOKEN_EXPIRETIME) {
            // 重置token有效时间
            tokenBean.setExpireTime(System.currentTimeMillis());

            // 判断该用户角色是否有权限调用接口
            if (!authService.auth(tokenBean, uri)) {
                print(response);
                return;
            }

            chain.doFilter(request, response);
            return;
        }

        // 失效移除  后台任务处理
//        tokenService.remove(token);
        print(response);
    }

    @Override
    public void destroy() {

    }

    /**
     * 判断请求是否需要验证token
     *
     * @param uri
     * @return {@link boolean}
     * @author zjx
     * @date 2020/10/27 14:08
     */
    private boolean pass(String uri) {
        AntPathMatcher matcher = new AntPathMatcher();
        for (String excludePath : excludePaths) {
            if (matcher.match(excludePath, uri)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 输出
     *
     * @param response
     * @return {@link Void}
     * @author zjx
     * @date 2020/10/27 14:15
     */
    private void print(ServletResponse response) throws IOException {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Content-Type", "application/json;charset=utf-8");
        String respStr = JSONUtil.toJSONString(Result.buildFail(AdminErrorConstant.REQUEST_ERROR, "请求异常，请登录"));
        response.getWriter().println(respStr);
        LoggerUtil.info(log, "响应参数：{}", respStr);
    }

}
