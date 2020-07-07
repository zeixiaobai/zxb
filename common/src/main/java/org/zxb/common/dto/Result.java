package org.zxb.common.dto;

import lombok.Data;

/**
 * @author zjx
 * @description 统一返回结构体  code=1 message =success
 * @date 2020/1/2
 */
@Data
public class Result extends BaseDTO {

    private int code;

    private String message;

    private Object data;

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @description 返回成功实体
     * @author zjx
     * @date 2020/1/13
     */
    public static Result buildSuccess(String message) {
        return new Result(200, message);
    }

}
