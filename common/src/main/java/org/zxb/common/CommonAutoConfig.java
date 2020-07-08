package org.zxb.common;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import org.zxb.common.utils.ResttemplateUtil;
import org.zxb.common.utils.SpringContextHolder;

/**
 * @author zjx
 * @description common 配置文件自动注入
 * @date 2020/1/13
 */
@ConditionalOnProperty(prefix = "org.zxb.common.enble", matchIfMissing = true)
@Import(SpringContextHolder.class)
public class CommonAutoConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        new ResttemplateUtil().setRestTemplate(restTemplate);
        return restTemplate;
    }

}
