package com.example.jwt1.config;

import com.example.jwt1.config.encoder.SecurityPasswordEncoder;
import com.example.jwt1.serviceImpl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 自定义登录校验器
 */
@Component
public class MySecurityAuthProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;
    private static UserDetailServiceImpl userDetailService;

    @PostConstruct
    public void setService() {
        userDetailService = userDetailServiceImpl;
    }

    /**
     * 进行登录用户名密码校验
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取用户输入的用户名和密码
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        //获取封装用户信息的对象
        UserDetails userDetails = userDetailService.loadUserByUsername(username);

        //1.测试环境
        SecurityPasswordEncoder passwordEncoder = new SecurityPasswordEncoder();
        //2.生产环境
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //校验
        boolean flag = passwordEncoder.matches(password, userDetails.getPassword());
        //校验通过
        if (flag) {
            //将权限信息也封装进去
            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        }

        //验证失败返回 null
        return null;
    }

    /**
     * 开启自定义支持
     *
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
