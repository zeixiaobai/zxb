package org.zxb.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zxb.web.config.ZXBAutoConfig;

@SpringBootApplication
public class WebApplicaton {

    public static void main(String[] args) {
        SpringApplication.run(WebApplicaton.class,args);
    }

}
