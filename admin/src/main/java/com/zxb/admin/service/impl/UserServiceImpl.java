package com.zxb.admin.service.impl;

import com.zxb.admin.constant.AdminErrorConstant;
import com.zxb.admin.dao.UserRepository;
import com.zxb.admin.dto.user.LoginDTO;
import com.zxb.admin.entity.Role;
import com.zxb.admin.entity.User;
import com.zxb.admin.exceptioin.AdminException;
import com.zxb.admin.service.RoleService;
import com.zxb.admin.service.TokenService;
import com.zxb.admin.service.UserService;
import com.zxb.admin.vo.user.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 用户服务实现
 *
 * @author zjx
 * @date 2020-07-10 15:02:14
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RoleService roleService;

    /**
     * 登录
     *
     * @param dto 登录请求参数
     * @return {@link User}
     * @author zjx
     * @date 2020/07/09 13:53
     */
    @Override
    public LoginVO login(LoginDTO dto) {

        // 调用登录系统
        User user = userRepository.findByUserNameAndPassword(dto.getUserName(), dto.getPassword());
        if (user == null) {
            throw new AdminException(AdminErrorConstant.LOGIN_ERROR, "用户名或者密码错误");
        }
        // 查询用户
        List<Role> roles = roleService.findByUserId(user.getUserId());
        if (CollectionUtils.isEmpty(roles)) {
            throw new AdminException(AdminErrorConstant.LOGIN_ERROR, "该用户目前没有角色，请找管理员添加");
        }

        // 生成token
        String id = tokenService.generate(user, roles);

        // 构建返回对象
        LoginVO loginVO = new LoginVO();
        loginVO.setToken(id);
        loginVO.setUserName(user.getUserName());

        return loginVO;
    }

}
