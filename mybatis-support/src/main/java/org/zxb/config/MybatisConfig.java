package org.zxb.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zxb.plun.ExamplePlugin;

/**
 * @description: mybatis配置
 * @author: zjx
 * @time: 2020/1/6 19:54
 */
@Configuration
public class MybatisConfig {

    @Bean
    ExamplePlugin myInterceptor() {
        return new ExamplePlugin();
    }

    @Bean
    public ConfigurationCustomizer getConfigurationCustomizer(){
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
                configuration.addInterceptor(myInterceptor());
            }
        };
    }
}
