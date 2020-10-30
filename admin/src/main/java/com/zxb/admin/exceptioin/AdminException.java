package com.zxb.admin.exceptioin;

import org.zxb.web.exception.CommonException;

/**
 * TODO
 *
 * @author zjx
 * @date 2020/10/27 0027 10:48
 */
public class AdminException extends CommonException {

    public AdminException(String code, String messag) {
        super(code, messag);
    }

}
