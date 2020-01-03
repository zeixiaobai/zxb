package org.zxb.common.utils;

import org.slf4j.Logger;

/**
 * @author zjx
 * @description 日志工具类
 * @date 2020/1/3
 */
public final class LoggerUtil {

    private LoggerUtil(){}

    public static void info(Logger log, String format, Object... arguments) {
        if (log.isInfoEnabled()) {
            log.info(format,arguments);
        }
    }

    public static void info(Logger log, String msg, Throwable t) {
        if (log.isInfoEnabled()) {
            log.info(msg, t);
        }
    }

    public static void debug(Logger log, String format, Object... arguments) {
        if (log.isDebugEnabled()) {
            log.debug(format,arguments);
        }
    }

    public static void debug(Logger log, String msg, Throwable t) {
        if (log.isDebugEnabled()) {
            log.debug(msg, t);
        }
    }

    public static void error(Logger log, String format, Object... arguments) {
        if (log.isErrorEnabled()) {
            log.error(format,arguments);
        }
    }

    public static void error(Logger log, String msg, Throwable t) {
        if (log.isErrorEnabled()) {
            log.error(msg, t);
        }
    }

    public static void warn(Logger log, String format, Object... arguments) {
        if (log.isWarnEnabled()) {
            log.warn(format,arguments);
        }
    }

    public static void warn(Logger log, String msg, Throwable t) {
        if (log.isWarnEnabled()) {
            log.warn(msg, t);
        }
    }
}
