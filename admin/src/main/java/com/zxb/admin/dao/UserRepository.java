package com.zxb.admin.dao;

import com.zxb.admin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户持久类
 *
 * @author zjx
 * @date 2020-07-09 17:48:56
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 通过昵称和登录密码查找用户
     *
     * @param userName 用户名
     * @param password 密码
     * @return {@link User}
     * @author zjx
     * @date 2020/07/09 17:58
     */
    User findByUserNameAndPassword(String userName, String password);

}
