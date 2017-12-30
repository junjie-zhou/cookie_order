package com.cookie.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by yr on 2017/12/30.
 */
@Data
@PropertySource(value = "classpath:application.yml")
@ConfigurationProperties(prefix = "nginx")
@Configuration
public class NginxConfig {

    /**
     * nginx图片访问地址
     */
    private String url;
}
