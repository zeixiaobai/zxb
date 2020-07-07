package org.zxb.web.exception;

import org.zxb.common.exception.ZxbException;

public class BusinessException extends ZxbException {

    public BusinessException(String messag) {
        super(messag);
    }
}
