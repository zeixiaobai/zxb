package com.zxb.admin.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.zxb.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户角色关系表
 *
 * @author zjx
 * @date 2020/10/27 14:45
 */
@Data
@Entity
@Table(name = "zxb_sys_user_role")
@DynamicInsert
public class UserRoleRel extends BaseEntity {

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Integer roleId;
}
