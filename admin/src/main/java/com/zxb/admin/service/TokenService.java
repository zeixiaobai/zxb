package com.zxb.admin.service;

import com.zxb.admin.entity.Role;
import com.zxb.admin.entity.Token;
import com.zxb.admin.entity.User;

import java.util.List;

/**
 * token service
 *
 * @author zjx
 * @date 2020/10/27 10:55
 */
public interface TokenService {

    /**
     * 初始化token
     *
     * @param user
     * @param roles
     * @return {@link String}
     * @author zjx
     * @date 2020/10/27 11:06
     */
    String generate(User user, List<Role> roles);

    /**
     * 查询
     *
     * @param id
     * @return {@link Token}
     * @author zjx
     * @date 2020/10/27 11:14
     */
    Token get(String id);

    /**
     * 删掉token
     *
     * @param id
     * @return
     * @author zjx
     * @date 2020/10/27 11:07
     */
    void remove(String id);

}
