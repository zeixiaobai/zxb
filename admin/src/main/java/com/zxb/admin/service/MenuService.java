package com.zxb.admin.service;

import com.zxb.admin.entity.Menu;

import java.util.List;

/**
 * 菜单服务
 *
 * @author zjx
 * @date 2020/7/10 0010 15:00
 */
public interface MenuService {

    /**
     * 查询所有菜单
     *
     * @param
     * @return {@link List<Menu>}
     * @author zjx
     * @date 2020/07/16 13:11
     */
    List<Menu> queryAll();

    /**
     * 通过父id查询
     *
     * @param parentId
     * @return {@link List< Menu>}
     * @author zjx
     * @date 2020/10/27 12:25
     */
    List<Menu> findAllByParentId(Integer parentId);




}
