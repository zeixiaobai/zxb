package org.zxb.common.exception;

/**
 * @author zjx
 * @description 通用异常
 * @date 2020/1/13
 */
public class CommonException extends RuntimeException {

    protected String code;

    public CommonException(String messag) {
        super(messag);
    }

    public CommonException(String code, String messag) {
        this(messag);
        this.code = code;
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }

}
