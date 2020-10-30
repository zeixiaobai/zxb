package com.zxb.admin.service;

import com.zxb.admin.entity.Token;

/**
 * 鉴定用户操作权限
 *
 * @author zjx
 * @date 2020/10/27  17:21
 */
public interface AuthService {

    /**
     * 判断角色是否有权限
     *
     * @param roleId 角色ID
     * @param path   请求路径
     * @return {@link boolean}
     * @author zjx
     * @date 2020/10/27 17:18
     */
    boolean auth(Integer roleId, String path);

    /**
     * 判断角色是否有权限
     *
     * @param token token对象
     * @param path  请求路径
     * @return {@link boolean}
     * @author zjx
     * @date 2020/10/27 17:18
     */
    boolean auth(Token token, String path);
}
