package org.zxb.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description swagger 配置类
 * @author zjx
 * @date 2020/1/3
 */
@Data
@ConfigurationProperties(prefix = "swagger")
public class Swagger {

    private String title="用户管理系统";
    private String description="用户管理系统";
    private String termsOfServiceUrl;
    private String license;
    private String licenseUrl;
    private String version = "1.0";
    private String basePackage;
    private boolean enable = false;

}
