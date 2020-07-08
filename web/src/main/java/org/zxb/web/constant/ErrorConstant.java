package org.zxb.web.constant;

/**
 * 错误异常码
 * 三位系统简称 + 三位业务类型  + 四位错误序号
 *
 * @author zjx
 * @date 2020-07-08 14:24:34
 */
public class ErrorConstant {

    private ErrorConstant() {
    }

    /**
     * 参数校验错误
     */
    public final static String PARAM_ERROR = "ZXB0000001";
    /**
     * 未知异常
     */
    public final static String UNKNOWN_ERROR = "ZXB0009999";
    /**
     * 鉴权失败
     */
    public final static String AUTH_ERROR = "ZXB0001000";
}
