package com.example.jwt2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    /**
     * 资源服务器提供的受保护接口
     *
     * @param principal
     * @return
     */
    @RequestMapping("/user")
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }

}
