package org.zxb.common.exception;

/**
 * @author zjx
 * @description zxb 错误父类
 * @date 2020/1/13
 */
public class ZxbException extends Exception {

    protected int code;

    protected String fieldName;

    public ZxbException(String messag) {
        super(messag);
    }

    public ZxbException(int code, String messag) {
        this(messag);
        this.code = code;
    }

    public ZxbException(String fieldName, String messag) {
        this(messag);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getCode() {
        return code;
    }

}
