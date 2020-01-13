package org.zxb.common;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.zxb.common.utils.ResttemplateUtil;
import org.zxb.common.utils.SpringContextHolder;

/**
 * @description common 配置文件自动注入
 * @author zjx
 * @date 2020/1/13
 */
@Configuration
@ConditionalOnProperty(value="org.zxb.common.enble:true")
public class CommonAutoConfig {

    @Bean
    public SpringContextHolder getSpringContextHolder(){
        return new SpringContextHolder();
    }

    @Bean
    public RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        new ResttemplateUtil().setRestTemplate(restTemplate);
        return restTemplate;
    }

}
