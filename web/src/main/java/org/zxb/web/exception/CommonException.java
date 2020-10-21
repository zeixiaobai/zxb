package org.zxb.web.exception;

/**
 *  通用异常
 *  此类错误会被全局异常类处理，code需要在信息资源存在
 * {@link org.zxb.web.config.GlobalException}
 *
 * @author zjx
 * @date 2020-10-21 10:51:32
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
