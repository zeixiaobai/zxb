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

import javax.validation.Validator;
import java.lang.reflect.Method;

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

    @Autowired
    private Validator validator;

    @Autowired(required = false)
    private LogFilter logFilter = new LogFilterImpl();


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
              logFilter.filterIn(log,args[0], method.getName(), className);
            }
            // 执行方法
            long startTime = System.currentTimeMillis();
            result = point.proceed();
            long endTime = System.currentTimeMillis();
            LoggerUtil.info(log,"请求耗时{} ms",endTime-startTime);
            // 响应参数
           logFilter.filterOut(log,args[0], method.getName(), className);
        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    public LogFilter getLogFilter() {
        return logFilter;
    }

    public void setLogFilter(LogFilter logFilter) {
        this.logFilter = logFilter;
    }
}

