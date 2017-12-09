package com.cookie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
@SpringBootApplication
@MapperScan(basePackages = "com.cookie.dao")
//@ComponentScans({@ComponentScan("com.cookie")})
public class CookieApplication {
    public static void main(String[] args) {
        SpringApplication.run(CookieApplication.class, args);
    }
}
