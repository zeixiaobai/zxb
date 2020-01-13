package org.zxb.common.utils;

import org.slf4j.Logger;

/**
 * @author zjx
 * @description 日志工具类
 * @date 2020/1/3
 */
public final class LoggerUtil {

    private LoggerUtil(){}

    /**
     *
     * @param log
     * @param format 格式化字符串
     * @param arguments 字符串占位符参数
     */
    public static void info(Logger log, String format, Object... arguments) {
        if (log.isInfoEnabled()) {
            log.info(format,arguments);
        }
    }

    /**
     *
     * @param log
     * @param msg 错误信息
     * @param t 错误堆栈
     */
    public static void info(Logger log, String msg, Throwable t) {
        if (log.isInfoEnabled()) {
            log.info(msg, t);
        }
    }

    /**
     *
     * @param log
     * @param format 格式化字符串
     * @param arguments 字符串占位符参数
     */
    public static void debug(Logger log, String format, Object... arguments) {
        if (log.isDebugEnabled()) {
            log.debug(format,arguments);
        }
    }

    /**
     *
     * @param log
     * @param msg 错误信息
     * @param t 错误堆栈
     */
    public static void debug(Logger log, String msg, Throwable t) {
        if (log.isDebugEnabled()) {
            log.debug(msg, t);
        }
    }

    /**
     *
     * @param log
     * @param format 格式化字符串
     * @param arguments 字符串占位符参数
     */
    public static void error(Logger log, String format, Object... arguments) {
        if (log.isErrorEnabled()) {
            log.error(format,arguments);
        }
    }

    /**
     *
     * @param log
     * @param msg 错误信息
     * @param t 错误堆栈
     */
    public static void error(Logger log, String msg, Throwable t) {
        if (log.isErrorEnabled()) {
            log.error(msg, t);
        }
    }

    /**
     *
     * @param log
     * @param format 格式化字符串
     * @param arguments 字符串占位符参数
     */
    public static void warn(Logger log, String format, Object... arguments) {
        if (log.isWarnEnabled()) {
            log.warn(format,arguments);
        }
    }

    /**
     *
     * @param log
     * @param msg 错误信息
     * @param t 错误堆栈
     */
    public static void warn(Logger log, String msg, Throwable t) {
        if (log.isWarnEnabled()) {
            log.warn(msg, t);
        }
    }
}
