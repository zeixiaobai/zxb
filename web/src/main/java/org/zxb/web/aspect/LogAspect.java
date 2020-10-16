package org.zxb.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.zxb.common.utils.LoggerUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 日志切面处理类
 *
 * @author zjx
 * @date 2019/12/24
 */
@Aspect
@Slf4j
@Order(0)
public class LogAspect {

    /* 日志最大长度 */
    private static final int LOG_MAX_LENGTH = 2000;

    @Autowired
    private Validator validator;

    /* 目标类(target)有ZxbLog注解的所有方法 */
    @Pointcut("@within(org.zxb.web.annotation.ZxbLog)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        try {
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();
            // 请求的方法名
            String className = point.getTarget().getClass().getName();
            // 请求的参数  （取第一个参数，如果请求参数大于一个用对象接收）
            Object[] args = point.getArgs();
            if (args != null && args.length > 0) {
                pringLog(args[0], method.getName(), className, "请求参数：");
            }
            // 执行方法
            result = point.proceed();
            // 响应参数
            pringLog(result, method.getName(), className, "响应参数：");
        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    /**
     * 日志输出
     *
     * @Param arg
     * @Param methodName
     * @Param className
     * @author zjx
     * @date 2020/07/08 11:04
     */
    private void pringLog(Object arg, String methodName, String className, String type) {
        if (arg == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        String argLog = arg.toString();
        if (argLog.length() > LOG_MAX_LENGTH) {
            sb.append(className)
                    .append(".")
                    .append(methodName)
                    .append(": ")
                    .append(argLog.substring(0, LOG_MAX_LENGTH));
        } else {
            sb.append(className)
                    .append(".")
                    .append(methodName)
                    .append(": ")
                    .append(argLog);
        }
        LoggerUtil.info(log, type + ":{}", sb.toString());
    }

}

