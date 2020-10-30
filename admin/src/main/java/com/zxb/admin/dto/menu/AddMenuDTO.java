package com.zxb.admin.dto.menu;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.zxb.web.dto.BaseDTO;

/**
 * 添加菜单dto
 *
 * @author zjx
 * @date 2020/7/10 0010 14:44
 */
@Data
@ApiModel
public class AddMenuDTO extends BaseDTO {
    /**
     * 菜单ID
     */
    private Integer menuId;
    /**
     * 父菜单ID，一级菜单为0
     */
    private Integer parentId;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单URL
     */
    private String url;
    /**
     * 类型   0：超链接   1：菜单   2：按钮  3：图片
     */
    private String type;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 排序
     */
    private String orderNum;

}
