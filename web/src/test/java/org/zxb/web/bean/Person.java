package org.zxb.web.bean;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @description: TODO
 * @author: zjx
 * @time: 2020/1/2 23:33
 */
@Data
public class Person {

    @NotBlank(message="like不能为空")
    private String like;

    private String level;
}
