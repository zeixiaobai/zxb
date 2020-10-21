package org.zxb.web.constant;

/**
 * 异常码
 * 异常类型 +  系统ID  + 异常序号
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
    public final static String PARAM_ERROR = "PAR0000000001";
    /**
     * 未知异常
     */
    public final static String SYS_ERROR = "SYS0000009999";

}
