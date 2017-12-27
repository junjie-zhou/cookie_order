package com.cookie.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Data
@PropertySource(value = "classpath:application.yml")
@ConfigurationProperties(prefix = "projectUrl")
@Configuration
public class ProjectUrlConfig {

    /**
     * 微信公众平台账号授权url
     */
    private String wechatMpAuthorize;

    /**
     * 微信开放平台账号授权url
     */
    private String wechatOpenAuthorize;

    /**
     * 点餐系统
     */
    private String sell;
}
