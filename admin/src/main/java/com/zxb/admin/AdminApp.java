package com.zxb.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 后台管理系统
 *
 * @author zjx
 * @date 2020/10/27 0027 10:24
 */
@SpringBootApplication
@EnableTransactionManagement
public class AdminApp {

    public static void main(String[] args) {
        SpringApplication.run(AdminApp.class, args);
    }

}
