package org.zxb.web.aspect;

import org.slf4j.Logger;

/**
 *  日志格式
 *
 * @author zjx
 * @date 2020/10/30 0030 16:50
 */
public interface LogFilter {

    /**
     *  请求日志输出
     *
     * @param log           日志对象
     * @param arg           参数对象
     * @param methodName    方法名字
     * @param className     类全路径名
     * @author zjx
     * @return {@link Void}
     * @date 2020/10/30 16:53
     */
    void filterIn(Logger log, Object arg, String methodName, String className);

    /**
     *  响应日志输出
     *
     * @param log           日志对象
     * @param arg           参数对象
     * @param methodName    方法名字
     * @param className     类全路径名
     * @author zjx
     * @return {@link Void}
     * @date 2020/10/30 16:53
     */
    void filterOut(Logger log, Object arg, String methodName, String className);

}
