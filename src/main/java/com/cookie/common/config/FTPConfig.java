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
@ConfigurationProperties(prefix = "FTP")
@Configuration
public class FTPConfig {

    /**
     * FTP的IP地址
     */
    private String url;

    /**
     * FTP的端口地址
     */
    private Integer port;

    /**
     * FTP的用户名
     */
    private String userName;

    /**
     * FTP的密码
     */
    private String passWord;

    /**
     * FTP上传文件根路径
     */
    private String basePath;

    @Override
    public String toString() {
        return "FTPConfig{" +
                "url='" + url + '\'' +
                ", port=" + port +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", basePath='" + basePath + '\'' +
                '}';
    }
}
