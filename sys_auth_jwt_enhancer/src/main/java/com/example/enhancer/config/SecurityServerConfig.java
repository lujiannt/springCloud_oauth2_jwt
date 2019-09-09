package com.example.enhancer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * 动态从数据库校验用户
 */

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityServerConfig extends WebSecurityConfigurerAdapter {
    private final static String userExistsSql = "select username from user where username = ?";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/login", "/logout")
                .antMatchers("/oauth/authorize")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                //自定义登录页面
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("auth configure");
        //启用自定义登录处理器，动态从数据库比对用户名密码并返回UserDetails给Security
        auth.authenticationProvider(new MySecurityAuthProvider());
    }

    /**
     * 密码编码
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 注入AuthenticationManager
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }
}
