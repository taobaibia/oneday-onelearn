package com.shenzhijie.loguserinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * 手动配置多数据源,则需要去除boot的自动装配
 */
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@EnableOpenApi
@MapperScan(basePackages = "com.shenzhijie.loguserinfo.web.mapper")
public class LoguserinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoguserinfoApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
