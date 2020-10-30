package com.zxb.admin.vo.menu;

import lombok.Data;
import org.zxb.web.dto.BaseDTO;
import org.zxb.web.vo.BaseVO;

/**
 * 权限菜单信息 vo
 *
 * @author zjx
 * @date 2020/10/27 0027 12:27
 */
@Data
public class GetMenuInfoVO extends BaseVO {

    /**
     * 路径
     */
    private String path;
    /**
     * 名字
     */
    private String name;
    /**
     * 视图
     */
    private String component;
    /**
     * 是否隐藏
     */
    private String hidden;
    /**
     * 转发菜单
     */
    private String redirect;
    /**
     * 子菜单
     */
    private String children;
    /**
     * 元数据
     */
    private MenuMeta meta;

}

