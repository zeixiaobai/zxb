package org.zxb.web.exception;

import org.zxb.common.exception.ZxbException;

/**
 * @author zjx
 * @description 校验错误
 * @date 2019/12/24
 */
public class ValidateException extends ZxbException {

    public ValidateException(String messag) {
        super(messag);
    }

    public ValidateException(String fieldName, String message) {
        this(message);
    }

}
