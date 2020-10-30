package com.zxb.admin.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.zxb.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色菜单关系表
 *
 * @author zjx
 * @date 2020/10/27  14:46
 */
@Data
@Entity
@Table(name = "zxb_sys_role_menu")
@DynamicInsert
public class RoleMenuRel extends BaseEntity {

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 菜单ID
     */
    @Column(name = "menu_id")
    private Integer menuId;
}
