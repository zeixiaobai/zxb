package com.zxb.admin.dao;

import com.zxb.admin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 角色持久类
 *
 * @author zjx
 * @date 2020/7/10 0010 15:04
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * 通过角色id查找
     *
     * @param roleId
     * @return {@link Role}
     * @author zjx
     * @date 2020/10/27 16:15
     */
    Role findByRoleId(Integer roleId);

}
