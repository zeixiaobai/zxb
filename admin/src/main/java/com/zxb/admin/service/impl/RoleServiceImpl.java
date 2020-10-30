package com.zxb.admin.service.impl;

import com.zxb.admin.dao.RoleRepository;
import com.zxb.admin.dao.UserRoleRelRepository;
import com.zxb.admin.entity.Role;
import com.zxb.admin.entity.UserRoleRel;
import com.zxb.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色服务实现
 *
 * @author zjx
 * @date 2020/7/10 0010 15:03
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UserRoleRelRepository userRoleRelRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * 通过用户id查找角色
     *
     * @param userId
     * @return {@link List< Role>}
     * @author zjx
     * @date 2020/10/27 16:10
     */
    @Override
    public List<Role> findByUserId(String userId) {

        List<Role> roleList = new ArrayList<>();

        List<UserRoleRel> urrList = userRoleRelRepository.findByUserId(userId);

        if (!CollectionUtils.isEmpty(urrList)) {
            for (UserRoleRel userRoleRel : urrList) {
                Integer roleId = userRoleRel.getRoleId();
                Role role = roleRepository.findByRoleId(roleId);
                if (role != null) {
                    roleList.add(role);
                }
            }
        }
        return roleList;
    }

}
