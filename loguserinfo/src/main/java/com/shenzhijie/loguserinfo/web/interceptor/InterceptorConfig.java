package com.shenzhijie.loguserinfo.web.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @date 2021/1/27
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/api.example.com/v1/",
                        "/v3/**",
//						"/http://localhost:8089/swagger-resources/configuration/ui/",
                        "/doc.html",
                        "/code/**")
                .excludePathPatterns("/log/login")
                .excludePathPatterns("/flux-web/*")
                .excludePathPatterns("/log/Registered")
                .excludePathPatterns("/log/test-verify")
                .excludePathPatterns("/log/edit")
                .excludePathPatterns("/log/test")
                .excludePathPatterns("/log/selectAll")
                .excludePathPatterns("/log/test-plus")
                .excludePathPatterns("/log/**")
                .excludePathPatterns("/code/**")
                .excludePathPatterns("/Slider-image/**")
                .excludePathPatterns("/check-code/**")
                .excludePathPatterns("/code/verify")
                .excludePathPatterns("/code/generator")

        ;
    }

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }


}
