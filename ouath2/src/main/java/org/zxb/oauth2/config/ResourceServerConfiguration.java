/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package org.zxb.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

//    @Autowired
//    private LoginAuthenticationFilter loginAuthenticationFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
//                .addFilterBefore(loginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // Since we want the protected resources to be accessible in the UI as well we need
                // session creation to be allowed (it's disabled by default in 2.0.6)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers().anyRequest()
                .and()
                .anonymous()
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/webjars/**",
                        "/swagger/**",
                        "/v2/api-docs",
                        "/swagger-ui.html",
                        "/ouath/check_token",
                        "/swagger-resources/**" )
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated();//配置所有访问控制，必须认证过后才可以访问
        // @formatter:on
    }


}
