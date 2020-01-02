package org.zxb.web.dto;

import lombok.Data;

/**
 * @description 统一返回结构体  code=1 message =success
 * @author zjx
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
}
