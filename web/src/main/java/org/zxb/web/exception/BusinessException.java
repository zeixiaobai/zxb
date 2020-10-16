package org.zxb.web.exception;

import org.zxb.common.exception.CommonException;

/**
 * @author zjx
 * @description 业务错误类
 * @date 2019/12/24
 */
public class BusinessException extends CommonException {

    public BusinessException(String code, String messag) {
        super(code, messag);
    }
}
