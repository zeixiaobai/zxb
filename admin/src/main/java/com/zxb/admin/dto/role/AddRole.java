package com.zxb.admin.dto.role;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.zxb.web.dto.BaseDTO;

import javax.validation.constraints.NotBlank;

/**
 * 添加角色dto
 *
 * @author zjx
 * @date 2020/7/10 0010 14:32
 */
@Data
@ApiModel
public class AddRole extends BaseDTO {

    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    /**
     * 备注
     */
    private String remark;

}
