package com.zxb.admin.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.zxb.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 菜单实体
 *
 * @author zjx
 * @date 2020/10/27 10:26
 */
@Data
@Entity
@Table(name = "zxb_sys_menu")
@DynamicInsert
public class Menu extends BaseEntity {

    /**
     * 菜单ID
     */
    @Column(name = "menu_id")
    private Integer menuId;
    /**
     * 父菜单ID，一级菜单为0
     */
    @Column(name = "parent_id")
    private Integer parentId;
    /**
     * 菜单名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 菜单URL
     */
    @Column(name = "path")
    private String path;
    /**
     * 类型   0：超链接   1：菜单   2：按钮  3：图片
     */
    @Column(name = "type")
    private String type;
    /**
     * 菜单图标
     */
    @Column(name = "icon")
    private String icon;
    /**
     * 排序
     */
    @Column(name = "order_num")
    private String orderNum;
    /**
     * 视图
     */
    @Column(name = "component")
    private String component;
    /**
     * 是否隐藏
     */
    @Column(name = "hidden")
    private String hidden;
    /**
     * 重定向视图
     */
    @Column(name = "redirect")
    private String redirect;
}

