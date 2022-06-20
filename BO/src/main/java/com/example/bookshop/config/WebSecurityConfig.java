package com.example.bookshop.config;/**
 * @Auther: shu'ai
 * @Date: 2021/10/12 14:40
 * @Description:
 */

import com.example.bookshop.handler.AuthenticationEntryPointImpl;
import com.example.bookshop.handler.AuthenticationFailureHandlerImpl;
import com.example.bookshop.handler.AuthenticationSuccessHandlerImpl;
import com.example.bookshop.handler.LogoutSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @ClassName:WebSecurityConfig
 * @Auther: shu'ai
 * @Description: SpringSecurity配置类
 * @Date: 2021/10/12 14:40
 * @Version: v1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;
    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandlerImpl authenticationFailHandler;
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;


    /**
     * 密码加密
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置登录注销路径


        http.formLogin().loginProcessingUrl("/login").successHandler(authenticationSuccessHandler).failureHandler(authenticationFailHandler).and()
                .logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);

        http.authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .csrf().disable().exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint);

    }
}
