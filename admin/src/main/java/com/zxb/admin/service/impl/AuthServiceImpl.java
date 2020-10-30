package com.zxb.admin.service.impl;

import com.zxb.admin.dao.RoleMenuRelRepository;
import com.zxb.admin.entity.Role;
import com.zxb.admin.entity.Token;
import com.zxb.admin.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 鉴定用户操作权限
 *
 * @author zjx
 * @date 2020/10/27  17:22
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RoleMenuRelRepository roleMenuRelRepository;

    /**
     * 判断角色是否有权限
     *
     * @param roleId
     * @param path
     * @return {@link boolean}
     * @author zjx
     * @date 2020/10/27 17:18
     */
    @Override
    public boolean auth(Integer roleId, String path) {
        Integer i = roleMenuRelRepository.countByRoleIdAndPath(roleId, path);
        if (i != null && i > 0) {
            return true;
        }

        return false;
    }

    /**
     * 判断角色是否有权限
     *
     * @param token token对象
     * @param path  请求路径
     * @return {@link boolean}
     * @author zjx
     * @date 2020/10/27 17:18
     */
    @Override
    public boolean auth(Token token, String path) {
        List<Role> roles = token.getRoles();
        for (Role role : roles) {
            Integer roleId = role.getRoleId();
            auth(roleId, path);
        }
        return false;
    }
}
