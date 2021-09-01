package com.shenzhijie.loguserinfo.web.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.web.interceptor</p>
 * <p>ClassName:SecurityConfig</p>
 * <p>Description:TODO(请用一句话描述这个类的作用)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/7/6
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

/*    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("mo").password("123456")
                .and()
                .withUser("root").password("123456")
                .and()
                .withUser("guest").password("123456");
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/code/**").permitAll()
                .antMatchers("/log/**").permitAll()
                .antMatchers("/check-code/**").permitAll()
                .antMatchers("/Slider-image/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v3/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                /**
                 * swagger 的一种请求csrf
                 * */
                .antMatchers("/csrf").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated().and().formLogin();
    }
}
