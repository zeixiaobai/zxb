package org.zxb.common.utils;

import org.slf4j.Logger;

/**
 * @author zjx
 * @description 日志工具类
 * @date 2020/1/3
 */
public class LoggerUtil {

    private LoggerUtil() {
    }

    /**
     * @param log       日志对象
     * @param format    格式化字符串
     * @param arguments 字符串占位符参数
     */
    public static void info(Logger log, String format, Object... arguments) {
        if (log.isInfoEnabled()) {
            log.info(format, arguments);
        }
    }

    /**
     * @param log       日志对象
     * @param format    格式化字符串
     * @param arguments 字符串占位符参数
     */

    public static void debug(Logger log, String format, Object... arguments) {
        if (log.isDebugEnabled()) {
            log.debug(format, arguments);
        }
    }

    /**
     * @param log 日志对象
     * @param e   错误信息
     */
    public static void error(Logger log, Exception e) {
        if (log.isErrorEnabled()) {
            log.error(e.getMessage(), e);
        }
    }


}
