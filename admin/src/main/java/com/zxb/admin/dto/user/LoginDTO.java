package com.zxb.admin.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.zxb.web.dto.BaseDTO;

import javax.validation.constraints.NotBlank;

/**
 * 登录dto
 *
 * @author zjx
 * @date 13:54
 */
@Data
@ApiModel
public class LoginDTO extends BaseDTO {

    @ApiModelProperty(example = "zjx", required = true)
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(example = "123456", required = true)
    @NotBlank(message = "密码不能为空")
    private String password;

}