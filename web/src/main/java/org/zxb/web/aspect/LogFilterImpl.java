package org.zxb.web.aspect;

import org.slf4j.Logger;
import org.zxb.common.utils.JSONUtil;
import org.zxb.common.utils.LoggerUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *  日志格式
 *
 * @author zjx
 * @date 2020/10/30 0030 17:08
 */
public class LogFilterImpl implements LogFilter {

    @Override
    public void filterIn(Logger log, Object arg, String methodName, String className) {
        if (arg == null || arg instanceof ServletRequest || arg instanceof ServletResponse) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        LoggerUtil.info(log,"--------------- 请求 ---------------");
        LoggerUtil.info(log,"请求类：         {}",className);
        LoggerUtil.info(log,"请求方法：       {}",methodName);
        LoggerUtil.info(log,"入参：           {}",JSONUtil.toJSONString(arg));
    }

    @Override
    public void filterOut(Logger log, Object arg, String methodName, String className) {
        if (arg == null || arg instanceof ServletRequest || arg instanceof ServletResponse) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        LoggerUtil.info(log,"--------------- 响应 ---------------");
        LoggerUtil.info(log,"出参：           {}",JSONUtil.toJSONString(arg));
    }
}
