package com.zxb.admin.service;

import com.zxb.admin.entity.Role;

import java.util.List;

/**
 * 角色服务
 *
 * @author zjx
 * @date 2020-07-09 13:52:28
 */
public interface RoleService {

    /**
     * 通过用户id查找角色
     *
     * @param userId
     * @return {@link List< Role>}
     * @author zjx
     * @date 2020/10/27 16:10
     */
    List<Role> findByUserId(String userId);
}
