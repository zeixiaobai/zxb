package org.zxb.web.exception;

/**
 * @author zjx
 * @description 校验错误
 * @date 2019/12/24
 */
public class ValidateException extends Exception {

    private String fieldName;

    public ValidateException(String messag) {
        super(messag);
    }

    public ValidateException(String fieldName, String messag) {
        super(messag);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

}
