package org.zxb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(value = "org.zxb.dao")
public class MybatisApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

}
