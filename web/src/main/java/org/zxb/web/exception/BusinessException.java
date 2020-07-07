package org.zxb.web.exception;

import org.zxb.common.exception.ZxbException;

/**
 * @author zjx
 * @description 业务错误类
 * @date 2019/12/24
 */
public class BusinessException extends ZxbException {

    public BusinessException(String messag) {
        super(messag);
    }
}
