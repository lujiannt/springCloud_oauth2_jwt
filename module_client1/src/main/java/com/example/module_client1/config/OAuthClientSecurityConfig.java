package com.example.module_client1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 客户端security配置
 */
@EnableOAuth2Sso
@Configuration
public class OAuthClientSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/403")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //配置登出
                .logout()
                .logoutUrl("/logout")
//                .logoutSuccessUrl("http://localhost:8894/auth/exit?access_token=b2cb0b4f-7411-4cf6-87cf-e408ee34d5d6")
                //登出成功处理器
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .and();
    }

}
