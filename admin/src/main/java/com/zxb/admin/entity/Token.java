package com.zxb.admin.entity;

import lombok.Data;

import java.util.List;

/**
 * token entity
 *
 * @author zjx
 * @date 2020/10/27 0027 11:00
 */
@Data
public class Token {

    /**
     * token id
     */
    private String id;
    /**
     * token 有效时间
     */
    private long expireTime;
    /**
     * 用户信息
     */
    private User user;
    /**
     * 角色信息
     */
    private List<Role> roles;

}
