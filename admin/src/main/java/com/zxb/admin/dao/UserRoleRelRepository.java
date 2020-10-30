package com.zxb.admin.dao;

import com.zxb.admin.entity.Role;
import com.zxb.admin.entity.UserRoleRel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户角色关系
 *
 * @author zjx
 * @date 2020/7/10 0010 15:04
 */
public interface UserRoleRelRepository extends JpaRepository<UserRoleRel, Integer> {

    /**
     * 通过用户id查找角色
     *
     * @param userId
     * @return {@link List< Role>}
     * @author zjx
     * @date 2020/10/27 16:10
     */
    List<UserRoleRel> findByUserId(String userId);

}
