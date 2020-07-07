package org.zxb.common.exception;

/**
 * @author zjx
 * @description zxb 错误父类
 * @date 2020/1/13
 */
public class ZxbException extends RuntimeException {

    protected String code;

    public ZxbException(String messag) {
        super(messag);
    }

    public ZxbException(String code, String messag) {
        this(messag);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
