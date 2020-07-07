package org.zxb.common.utils;

import org.slf4j.Logger;

/**
 * @author zjx
 * @description 日志工具类基于logback
 * @date 2020/1/3
 */
public class LoggerUtil {

    private LoggerUtil() {
    }

    /**
     * @param format    格式化字符串
     * @param arguments 字符串占位符参数
     */
    public static void info(Logger log, String format, Object... arguments) {
        if (log.isInfoEnabled()) {
            log.info(format, arguments);
        }
    }

    /**
     * @param format    格式化字符串
     * @param arguments 字符串占位符参数
     */
    public static void debug(Logger log, String format, Object... arguments) {
        if (log.isDebugEnabled()) {
//                LocationAwareLogger logger = (LocationAwareLogger) log;
//                logger.log(null, FQCN, LocationAwareLogger.DEBUG_INT, format, arguments, null);
                log.debug(format, arguments);
        }
    }

}
