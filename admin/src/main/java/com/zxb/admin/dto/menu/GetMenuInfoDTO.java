package com.zxb.admin.dto.menu;

import lombok.Data;
import org.zxb.web.dto.BaseDTO;

/**
 * 权限菜单信息 dto
 *
 * @author zjx
 * @date 2020/10/27 0027 12:27
 */
@Data
public class GetMenuInfoDTO extends BaseDTO {

    /**
     * token
     */
    private String token;

    /**
     * 当前角色
     */
    private String role;


}
