package org.zxb.web.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.zxb.web.constant.ResponseConstant;
import org.zxb.web.dto.BaseDTO;

/**
 * 统一返回结构体
 *
 * @author zjx
 * @date 2020/1/2
 */
@Data
@NoArgsConstructor
public class Result<T> extends BaseVO {

    private String code;

    private String message;

    private T data;

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * @description 返回成功实体
     * @author zjx
     * @date 2020/1/13
     */
    public static Result buildSuccess(Object data) {
        return new Result(ResponseConstant.CODE, ResponseConstant.MESSAGE, data);
    }

    /**
     * 返回失败实体
     *
     * @return {@link Result}
     * @Param code
     * @Param message
     * @author zjx
     * @date 2020/07/08 13:59
     */
    public static Result buildFail(String code, String message) {
        return new Result(code, message);
    }

}
