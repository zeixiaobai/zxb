package org.zxb.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.zxb.web.aspect.LogAspect;

/**
 * 自动注入
 *
 * @author: zjx
 * @time: 2020/1/2 21:55
 */
@Import(Swagger2Config.class)
public class ZXBAutoConfig {

    @Value(value = "${spring.messages.basename:i18n/message}")
    private String basename;

    @Bean(name = "messageSource")
    public ResourceBundleMessageSource getMessageResource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        basename = basename + ",i18/zxb";
        String[] bns = basename.split(",");
        messageSource.addBasenames(bns);
        return messageSource;
    }

    @Bean
    public GlobalException getGlobalExeception() {
        return new GlobalException();
    }

    @Bean
    @ConditionalOnMissingBean
    public LogAspect getLogAspect() {
        return new LogAspect();
    }

}
