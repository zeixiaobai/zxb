package org.zxb.ouath2.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.stereotype.Component;
import org.zxb.ouath2.handler.AuthFailHandler;
import org.zxb.ouath2.handler.AuthSuccessHandler;

/**
 * @description /oauth/token 认证失败返回处理
 * @author zjx
 * @date 2020/1/15 0015
 */
@Component
public class CCTEFilterBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof ClientCredentialsTokenEndpointFilter){
            ClientCredentialsTokenEndpointFilter cctef = (ClientCredentialsTokenEndpointFilter)bean ;
            cctef.setAuthenticationFailureHandler(new AuthFailHandler());
            cctef.setAuthenticationSuccessHandler(new AuthSuccessHandler());
        }
        return bean;
    }

}
