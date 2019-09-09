package com.example.module_client1.config;

import com.example.module_client1.utils.AccessTokenUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("myLogoutSuccessHandler")
public class MyLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("登出成功");
        String token = AccessTokenUtils.getRandomToken();
        System.out.println("randomToken = " + token);
        response.sendRedirect("http://localhost:8894/auth/exit?access_token=" + token);
//        super.onLogoutSuccess(request, response, authentication);
    }
}
