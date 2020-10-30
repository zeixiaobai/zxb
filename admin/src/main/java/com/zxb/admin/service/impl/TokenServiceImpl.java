package com.zxb.admin.service.impl;

import com.zxb.admin.entity.Role;
import com.zxb.admin.entity.Token;
import com.zxb.admin.entity.User;
import com.zxb.admin.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * token service impl
 *
 * @author zjx
 * @date 2020/10/27 0027 10:57
 */
@Service
public class TokenServiceImpl implements TokenService {

    private Map<String, Token> tokenMap = new HashMap<>(8);

    /**
     * 初始化token
     *
     * @param user
     * @param roles
     * @return {@link String}
     * @author zjx
     * @date 2020/10/27 11:06
     */
    @Override
    public String generate(User user, List<Role> roles) {
        // 生成token
        String id = UUID.randomUUID().toString();
        // 缓存
        Token token = new Token();
        token.setId(id);
        token.setUser(user);
        token.setRoles(roles);
        // 更新token超时时间
        token.setExpireTime(System.currentTimeMillis());

        tokenMap.put(id, token);
        return id;
    }

    /**
     * 查询
     *
     * @param id
     * @return {@link String}
     * @author zjx
     * @date 2020/10/27 11:14
     */
    @Override
    public Token get(String id) {
        return tokenMap.get(id);
    }

    /**
     * 删掉token
     *
     * @param id
     * @return
     * @author zjx
     * @date 2020/10/27 11:07
     */
    @Override
    public void remove(String id) {
        tokenMap.remove(id);
    }

}
