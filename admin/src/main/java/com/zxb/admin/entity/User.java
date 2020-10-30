package com.zxb.admin.entity;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.zxb.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户表
 *
 * @author zjx
 * @date 2020-07-09 17:46:14
 */
@Data
@Entity
@Table(name = "zxb_sys_user")
@DynamicInsert
public class User extends BaseEntity {
    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;
    /**
     * 用户昵称
     */
    @Column(name = "user_name")
    private String userName;
    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;
    /**
     * 用户邮箱
     */
    @Column(name = "user_mail")
    private String userMail;
    /**
     * 登录密码
     */
    @Column(name = "password")
    private String password;
    /**
     * 手机号码
     */
    @Column(name = "user_mobile")
    private String userMobile;
    /**
     * 备注
     */
    @Column(name = "user_memo")
    private String userMemo;
    /**
     * M(男) or F(女)
     */
    @Column(name = "sex")
    private String sex;
    /**
     * 例如：2009-11-27
     */
    @Column(name = "birth_date")
    private String birthDate;
    /**
     * 头像图片路径
     */
    @Column(name = "pic")
    private String pic;
    /**
     * 状态 1 正常 0 无效
     */
    @Column(name = "user_status")
    private String userStatus;

}
