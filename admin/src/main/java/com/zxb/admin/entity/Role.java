package com.zxb.admin.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.zxb.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色实体
 *
 * @author zjx
 * @date 2020/10/27  10:29
 */
@Data
@Entity
@Table(name = "zxb_sys_role")
@DynamicInsert
public class Role extends BaseEntity {

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Integer roleId;
    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

}
