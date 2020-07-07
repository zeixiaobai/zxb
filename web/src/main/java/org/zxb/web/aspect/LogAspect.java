package org.zxb.web.aspect;

import com.alibaba.fastjson.JSON;
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
 * @author zjx
 * @description 日志切面处理类
 * @date 2019/12/24
 */
@Aspect
@Slf4j
@Order(0)
public class LogAspect {

    /* 日志最大长度 */
    private static final int LOG_MAX_LENGTH = 5000;

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
            // 请求的参数
            Object[] args = point.getArgs();
            if (args != null) {
                args = filterArgs(args);
                pringLog(args, method.getName(), className);
            }
            // 执行方法
            result = point.proceed();
            // 响应参数
            String argLog = result.toString();
            if (argLog.length() > LOG_MAX_LENGTH) {
                LoggerUtil.info(log, argLog.substring(0, LOG_MAX_LENGTH));
            }else{
                LoggerUtil.info(log, argLog);
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    /**
     * @description 日志输出
     * @author zjx
     */
    private void pringLog(Object[] args, String methodName, String className) {
        if (args == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            String argLog = null;
            if (args[i] != null) {
                // 最大输出2000
                argLog = args[i].toString();
            } else {
                argLog = "null";
            }
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
            LoggerUtil.info(log, sb.toString());
        }
    }

    /**
     * @description 过滤参数
     * @author zjx
     */
    private Object[] filterArgs(Object[] args) {
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg instanceof ServletRequest || arg instanceof ServletResponse) {
                continue;
            }
            list.add(arg);
        }
        return list.toArray();
    }
}

