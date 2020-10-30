package com.zxb.admin.dao;

import com.zxb.admin.entity.Role;
import com.zxb.admin.entity.RoleMenuRel;
import com.zxb.admin.entity.UserRoleRel;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 角色按钮关系
 *
 * @author zjx
 * @date 2020/7/10 0010 15:04
 */
public interface RoleMenuRelRepository extends JpaRepository<RoleMenuRel, Integer> {

    /**
     * 通过角色ID和按钮路径判断是否有权限
     *
     * @param roleId 角色ID
     * @param path   按钮路径
     * @return {@link List< Role>}
     * @author zjx
     * @date 2020/10/27 16:10
     */
    Integer countByRoleIdAndPath(Integer roleId, String path);

}
