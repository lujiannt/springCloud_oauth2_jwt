package com.example.jwt1.controller;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    /**
     * 自定义登录页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "/login";
    }


    /**
     * 登出
     *
     * @param request
     * @param response
     */
    @RequestMapping("/exit")
    public void exit(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("server Logout...");
        // token can be revoked here if needed
        new SecurityContextLogoutHandler().logout(request, null, null);
        try {
            //sending back to client app
            response.sendRedirect(request.getHeader("referer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
