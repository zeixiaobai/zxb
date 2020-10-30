package com.zxb.admin.service;

import com.zxb.admin.dto.user.LoginDTO;
import com.zxb.admin.vo.user.LoginVO;

/**
 * 用户服务类
 *
 * @author zjx
 * @date 2020-07-09 13:52:28
 */
public interface UserService {

    /**
     * 登录
     *
     * @return {@link LoginDTO}
     * @param dto 登录请求参数
     * @author zjx
     * @date 2020/07/09 13:53
     */
    LoginVO login(LoginDTO dto);
}
