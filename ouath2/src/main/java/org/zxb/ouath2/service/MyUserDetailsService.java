package org.zxb.ouath2.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @description: 用户认证类
 * @author: zjx
 * @time: 2020/1/12 17:23
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username + "----------------------");

        // 数据库中查找
        String password = "{noop}" + "123456";

        return User.withUsername(username).password(password).authorities("USER").disabled(false).build();
    }
}
